import {ConfigurationParameterType} from '../configuration/ConfigurationParameterType';
import {Version} from './Version';

export class RepositoryType {

    constructor(public id: string,
        public name: string,
        public description: string,
        public parameters: ConfigurationParameterType[],
        public jndiName: string,
        public pluginVersion: Version,
        public projectURLPath: string,
        public runURLPath: string,
        public serviceURLPath: string,
        public version: Version) { }

}
