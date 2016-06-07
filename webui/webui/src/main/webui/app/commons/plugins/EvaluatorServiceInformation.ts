import {Version} from '../domain/Version';
import {ConfigurationParameter} from '../configuration/ConfigurationParameter';

export class EvaluatorServiceInformation {

    id: string;
    name: string;
    type: string;
    pluginVersion: Version;
    jndiName: string;
    description: string;
    configurationParameters: ConfigurationParameter[];
    serviceURLPath: string;
    projectURLPath: string;
    runURLPath: string;
    qualityCharacteristics: any;

}
