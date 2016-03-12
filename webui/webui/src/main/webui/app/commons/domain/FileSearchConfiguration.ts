/**
 * This class represents the file search configuration for projects.
 */
export class FileSearchConfiguration {

    constructor(
        public locationIncludes: string[],
        public locationExcludes: string[],
        public fileIncludes: string[],
        public fileExcludes: string[],
        public ignoreHidden: boolean) { }

}
