#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/un.h>
#include <sys/stat.h>
#include <signal.h>
#include <errno.h>

/***************************************************************************
 main is the main function which is to start at the beginning of the
 execution. The daemon is created and started here.
 ***************************************************************************/
int main(int argc, char* argv[])
{
        char *binaryName = argv[0];
        signal(SIGCHLD, SIG_IGN);

        /*
         * We need to fork now to return to the parent process and to continue
         * as daemon...
         */
        int pid;
        if ((pid = fork()) == -1) {
                int errnosave = errno;
                perror("fork");
                printf("error: could fork\n");
                printf("%s\n", strerror(errnosave));
                exit(errnosave);
        }
        /*
         * Setting sighup to ignore...
         */
        if (signal(SIGHUP, SIG_IGN) == SIG_ERR) {
                int errnosave = errno;
                perror("signal");
                printf("error: could set signal SIGHUP to ignore\n");
                printf("%s\n", strerror(errnosave));
                exit(errnosave);
        }
        /*
         * exit parent process and return to console or whatever the caller
         * was...
         */
        if (pid > 0) {
                exit(0);
        }
        printf("Setting umask...\n");
        if (umask(022) < 0) {
                int errnosave = errno;
                perror("umask");
                printf("error: could not change umask\n");
                printf("%s\n", strerror(errnosave));
                exit(errnosave);
        }
        /*
         * Next we need to become session leader of a newly created session
         * without terminal.
         */
        printf("Setting sid...\n");
        if (setsid() == -1) {
                int errnosave = errno;
                perror("setsid");
                printf("error: could not become process group leader\n");
                printf("%s\n", strerror(errnosave));
                exit(errnosave);
        }
        printf("Setting home dir to '/'...\n");
        if (chdir("/") < 0) {
                int errnosave = errno;
                perror("chdir");
                printf("error: could not change directory\n");
                printf("%s\n", strerror(errnosave));
                exit(errnosave);
        }
        // daemonized...
        printf("daemonized...\n");
        for (int i = 0; i < 10; i++) {
                printf("%i\n", i);
                sleep(1);
        }
        printf("finished.\n");
}
