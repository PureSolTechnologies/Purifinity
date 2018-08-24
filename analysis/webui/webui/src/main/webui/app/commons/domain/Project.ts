import {ProjectInformation} from './ProjectInformation';
import {ProjectSettings} from './ProjectSettings';

/**
 * Keeps the project information and settings.
 */
export class Project {

    constructor(
        public information: ProjectInformation,
        public settings: ProjectSettings) { }

}
