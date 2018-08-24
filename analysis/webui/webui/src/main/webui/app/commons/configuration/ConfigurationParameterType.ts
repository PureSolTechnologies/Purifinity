import {LevelOfMeasurement} from '../math/LevelOfMeasurement';

export class ConfigurationParameterType {
    
    constructor(
        public className: string,
        public description: string,
        public levelOfMeasurement: LevelOfMeasurement,
        public name: string,
        public typeClass: string,
        public unit: string) { }

}