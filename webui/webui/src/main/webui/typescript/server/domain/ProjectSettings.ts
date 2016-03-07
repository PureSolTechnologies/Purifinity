class ProjectSettings {

    constructor(
        public name: string,
        public description: string,
        public fileSearchConfiguration: FileSearchConfiguration,
        public repository: { [s: string]: string; }) { }

}