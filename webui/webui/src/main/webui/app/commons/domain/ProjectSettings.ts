import {FileSearchConfiguration} from './FileSearchConfiguration';

/**
 * This class contains all project settings.
 */
export class ProjectSettings {

    constructor(
        public name: string,
        public description: string,
        public preAnalysisScript: string,
        public fileSearchConfiguration: FileSearchConfiguration,
        public repository: { [s: string]: string; }) { }

}