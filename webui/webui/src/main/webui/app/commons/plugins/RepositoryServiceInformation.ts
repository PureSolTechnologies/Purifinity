import {Version} from '../domain/Version';
import {ConfigurationParameter} from '../configuration/ConfigurationParameter';

export class RepositoryServiceInformation {

    id: string;
    name: string;
    version: string;
    pluginVersion: Version;
    jndiName: string;
    description: string;
    parameters: any;
    configurationParameters: ConfigurationParameter[];
    serviceURLPath: string;
    projectURLPath: string;
    runURLPath: string;

}
