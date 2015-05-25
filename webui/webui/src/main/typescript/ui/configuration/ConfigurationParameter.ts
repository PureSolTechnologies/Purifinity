/**
 * This class represents a single configuration parameter. The whole definition is defined here.
 */
class ConfigurationParameter {

    /**
     * This method is used to convert a single configuration parameter into a 
     * ConfigurationParameter object.
     */
    public static fromJSON(
        preferencesGroup: PreferencesGroup,
        groupIdentifier: string,
        configurationParameter: any) {
        return new ConfigurationParameter(preferencesGroup,
            groupIdentifier,
            configurationParameter.name,
            configurationParameter.unit,
            configurationParameter.levelOfMeasurement,
            configurationParameter.description,
            configurationParameter.type,
            configurationParameter.propertyKey,
            configurationParameter.path,
            configurationParameter.defaultValue,
            String(configurationParameter.valueRepresentation));
    }

    constructor(
        public preferencesGroup: PreferencesGroup,
        public groupIdentifier: string,
        public name: string,
        public unit: string,
        public levelOfMeasurement: LevelOfMeasurement,
        public description: string,
        public type: string,
        public propertyKey: string,
        public path: string,
        public defaultValue: any,
        public valueRepresentation: string) {
    }

}

