#include <stdio.h>

/***************************************************************************
 main is the main function which is to start at the beginning of the
 execution. The daemon is created and started here.
 ***************************************************************************/
int main(int argc, char* argv[])
{
        char *binaryName = argv[0];
        printf("%s\n", binaryName);
}
