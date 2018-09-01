#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/un.h>
#include <sys/stat.h>
#include <signal.h>
#include <errno.h>
#include <getopt.h>

static int verbose_flag = 0;

#define log(format, ...) if (verbose_flag) printf(format, ##__VA_ARGS__);

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

int
main (int argc, char* argv[])
{
  char *binaryName = argv[0];
  /*
   * getopt_long example: https://www.gnu.org/software/libc/manual/html_node/Getopt-Long-Option-Example.html
   */
  while (1)
    {
      static struct option long_options[] =
	{
	/* These options set a flag. */
	  { "verbose", no_argument, &verbose_flag, 1 },
	/* These options don’t set a flag.
	 We distinguish them by their indices. */
	  { "period", required_argument, 0, 'p' },
	  { 0, 0, 0, 0 } };
      /* getopt_long stores the option index here. */
      int option_index = 0;

      int c = getopt_long (argc, argv, "abc:d:f:", long_options, &option_index);

      /* Detect the end of the options. */
      if (c == -1)
	break;

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
	case 'p':
	  puts ("option -p\n");
	  break;
	case '?':
	  /* getopt_long already printed an error message. */
	  break;

	default:
	  abort ();
	}
    }

  /* Instead of reporting ‘--verbose’
   and ‘--brief’ as they are encountered,
   we report the final status resulting from them. */
  if (verbose_flag)
    puts ("verbose flag is set");

  /* Print any remaining command line arguments (not options). */
  if (optind < argc)
    {
      printf ("non-option ARGV-elements: ");
      while (optind < argc)
	printf ("%s ", argv[optind++]);
      putchar ('\n');
    }

  int pid = start_daemon ();
  // daemonized...
  for (int i = 0; i < 100; i++)
    {
      log("%i\n", i);
      sleep (1);
    }
  log("finished.\n");
}

