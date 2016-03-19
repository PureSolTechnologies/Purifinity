import {Version} from '../domain/Version';
import {ConfigurationParameter} from '../configuration/ConfigurationParameter';

export class AnalyzerServiceInformation {

    id: string;
    name: string;
    version: string;
    pluginVersion: Version;
    jndiName: string;
    description: string;
    configurationParameters: ConfigurationParameter;
    serviceURLPath: string;
    projectURLPath: string;
    runURLPath: string;

}
