#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/un.h>
#include <sys/stat.h>
#include <signal.h> // signal processing
#include <errno.h> // error handling
#include <getopt.h> // command line option parsing

#include <dirent.h> // directory handling
#include <fcntl.h> // file handling

#define PCRE2_CODE_UNIT_WIDTH 8
#include <pcre2.h>

static char* binary_path = 0;
static int verbose_flag = 0;
static int help_flag = 0;
static char* output_file = 0;
static char* comm_regex = 0;
static int daemonize = 0;
static int period = 1000;
static pcre2_code *comm_regex_pattern = 0;

#define log(format, ...) if (verbose_flag) printf(format, ##__VA_ARGS__);

void
parse_commandline_options (int argc, char* argv[])
{
  const static struct option long_options[] =
    {
    /* These options set a flag. */
      { "verbose", no_argument, &verbose_flag, 1 },
      { "help", no_argument, &help_flag, 1 },
    /* These options don’t set a flag.
     We distinguish them by their indices. */
      { "daemonize", no_argument, 0, 'd' },
      { "output", required_argument, 0, 'o' },
      { "period", required_argument, 0, 'p' },
      { "comm-regex", required_argument, 0, 'r' },
      { 0, 0, 0, 0 } };
  /* getopt_long stores the option index here. */
  int option_index = 0;
  /*
   * getopt_long example: https://www.gnu.org/software/libc/manual/html_node/Getopt-Long-Option-Example.html
   */
  while (1)
    {
      int c = getopt_long (argc, argv, "do:p:r:", long_options, &option_index);
      /* Detect the end of the options. */
      if (c == -1)
	{
	  break;
	}
      switch (c)
	{
	case 0:
	  /* If this option set a flag, do nothing else now. */
	  if (long_options[option_index].flag != 0)
	    break;
	  printf ("option %s", long_options[option_index].name);
	  if (optarg)
	    printf (" with arg %s", optarg);
	  printf ("\n");
	  break;
	case 'd':
	  daemonize = 1;
	  break;
	case 'p':
	  period = atoi (optarg);
	  break;
	case 'o':
	  output_file = optarg;
	  break;
	case 'r':
	  comm_regex = optarg;
	  break;
	case '?':
	  /* getopt_long already printed an error message. */
	  break;

	default:
	  abort ();
	}
    }
  /* Print any remaining command line arguments (not options). */
  if (optind < argc)
    {
      printf ("non-option ARGV-elements: ");
      while (optind < argc)
	{
	  printf ("%s ", argv[optind++]);
	}
      putchar ('\n');
    }
}

void
print_help ()
{
  puts ("Usage: saked [OPTION]...");
  puts ("Start saked to capture process telemetry");
  puts ("Example: sake -o process.dat -r 'processname' -p 1000");
  puts ("");
  //   "123456789012345678901234567890123456789012345678901234567890123456789012345678"
  puts ("      --help                show this help");
  puts ("      --verbose             run verbose");
  puts ("  -d, --daemonize           run saked as daemon");
  puts ("  -o, --output-file         file path to output the measurements to");
  puts ("  -p, --period              period between measurements in milli-");
  puts ("                            seconds");
  puts ("  -r, --comm-regex          regular expression for /proc/<pid>/comm");
  puts ("                            to match for process selection");
}

/***************************************************************************
 start_daemon is the main function which is to start at the beginning of the
 execution. The daemon is created and started here.
 ***************************************************************************/
int
start_daemon ()
{
  signal (SIGCHLD, SIG_IGN);
  /*
   * We need to fork now to return to the parent process and to continue
   * as daemon...
   */
  int pid;
  if ((pid = fork ()) == -1)
    {
      int errnosave = errno;
      perror ("fork");
      log("error: could fork\n");
      log("%s\n", strerror (errnosave));
      exit (errnosave);
    }
  /*
   * Setting sighup to ignore...
   */
  if (signal (SIGHUP, SIG_IGN) == SIG_ERR)
    {
      int errnosave = errno;
      perror ("signal");
      log("error: could set signal SIGHUP to ignore\n");
      log("%s\n", strerror (errnosave));
      exit (errnosave);
    }
  /*
   * exit parent process and return to console or whatever the caller
   * was...
   */
  if (pid > 0)
    {
      log("daemonized...\n");
      exit (0);
    }
  log("Setting umask...\n");
  if (umask (022) < 0)
    {
      int errnosave = errno;
      perror ("umask");
      log("error: could not change umask\n");
      log("%s\n", strerror (errnosave));
      exit (errnosave);
    }
  /*
   * Next we need to become session leader of a newly created session
   * without terminal.
   */
  log("Setting sid...\n");
  if (setsid () == -1)
    {
      int errnosave = errno;
      perror ("setsid");
      log("error: could not become process group leader\n");
      log("%s\n", strerror (errnosave));
      exit (errnosave);
    }
  log("Setting home dir to '/'...\n");
  if (chdir ("/") < 0)
    {
      int errnosave = errno;
      perror ("chdir");
      log("error: could not change directory\n");
      log("%s\n", strerror (errnosave));
      exit (errnosave);
    }
  return pid;
}

void
measure_process (char* dir_path, pid_t proc_pid)
{
  int comm_path_buf_len = strlen (dir_path) + 6; // '/comm\x00'
  char* comm_path = malloc (comm_path_buf_len);
  strncpy (comm_path, dir_path, comm_path_buf_len);
  strncat (comm_path, "/comm", comm_path_buf_len);
  FILE *comm_file = fopen (comm_path, "r");
  char* name = malloc (256);
  fscanf (comm_file, "%s", name);
  printf ("- %i: %s\n", proc_pid, name);
  free (name);
  fclose (comm_file);
  free (comm_path);
}

void
measure_processes ()
{
  const char* proc_path = "/proc/";
  const int proc_path_len = strlen (proc_path);
  DIR* proc = opendir (proc_path);
  if (proc)
    {
      struct dirent* dir;
      while ((dir = readdir (proc)) != NULL)
	{
	  if (dir->d_type == DT_DIR)
	    {
	      pid_t proc_pid = atoi (dir->d_name);
	      if (proc_pid > 0)
		{
		  int dir_name_len = strlen (dir->d_name);
		  int dir_path_buf_len = proc_path_len + dir_name_len + 1; // +1 for \x00
		  char *dir_path = malloc (dir_path_buf_len);
		  strncpy (dir_path, proc_path, dir_path_buf_len);
		  strncat (dir_path, dir->d_name, dir_path_buf_len);
		  measure_process (dir_path, proc_pid);
		  free (dir_path);
		}
	    }
	}
      closedir (proc);
    }
}

void
run_measurements ()
{
  int errornumber;
  PCRE2_SIZE erroroffset;
  comm_regex_pattern = pcre2_compile (
  comm_regex, PCRE2_ZERO_TERMINATED,0,&errornumber,&erroroffset,NULL);
  /* Compilation failed: print the error message and exit. */

  if (comm_regex_pattern == NULL)
    {
      PCRE2_UCHAR buffer[256];
      pcre2_get_error_message (errornumber, buffer, sizeof(buffer));
      printf ("PCRE2 compilation failed at offset %d: %s\n", (int) erroroffset,
	      buffer);
      exit(1);
    }

  for (int i = 0; i < 10; i++)
    {
      log("run: %i\n", i);
      measure_processes ();
      sleep (1);
    }
  pcre2_code_free (comm_regex_pattern);
}

int
main (int argc, char* argv[])
{
  char *binary_path = argv[0];

  parse_commandline_options (argc, argv);

  if (help_flag)
    {
      print_help ();
      exit (0);
    }

  log("saked\n");
  log("-----\n")
  if (verbose_flag)
    log("verbose flag is set\n");
  log("daemonize: %i\n", daemonize);
  log("output_file: %s\n", output_file);
  log("period: %i\n", period);
  log("comm-regex: %s\n", comm_regex);

  pid_t pid;
  if (daemonize)
    {
      pid = start_daemon ();
    }
  else
    {
      pid = getpid ();
    }
  log("pid: %i\n", pid);
  run_measurements ();
  log("finished.\n");
}

