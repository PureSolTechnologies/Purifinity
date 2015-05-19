/**
 * This class contains a tree or node of the configuration component.
 */
class ConfigurationComponentTree {

    children: ConfigurationComponentTree[] = [];
    imageUrl: string;
    parameters: ConfigurationParameter[] = [];

    constructor(public parent: ConfigurationComponentTree, public name: string) {
    }

    public addChild(child: ConfigurationComponentTree) {
        this.children.push(child);
        child.parent = this;
    }

    public addConfigurationParameter(parameter: ConfigurationParameter) {
        this.parameters.push(parameter);
    }
}
