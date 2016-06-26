import {LevelOfMeasurement} from '../math/LevelOfMeasurement';
export class Parameter {

    constructor(public name: string,
        public unit: string,
        public description: string,
        public levelOfMeasurement: LevelOfMeasurement,
        public variableType: string) { }

}