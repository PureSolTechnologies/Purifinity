/**
 * This class contains the model for the configuration-component directive.
 */
class ConfigurationComponentData {

    root: ConfigurationComponentTree;

    constructor(private rootName: string, public preferencesGroup: PreferencesGroup)  {
        this.root = new ConfigurationComponentTree(null, rootName);
    }

    public static addConfigurationParameter(node: ConfigurationComponentTree, parameter: ConfigurationParameter) {
        var path: string[] = parameter.path.split("/");
        var currentNode: ConfigurationComponentTree = node;
        for (var i = 0; i < path.length; i++) {
            var pathElement = path[i];
            if (pathElement.length > 0) {
                var nextNode = null;
                if (currentNode.children) {
                    for (var childId = 0; childId < currentNode.children.length; childId++) {
                        var child = currentNode.children[childId];
                        if (child.name == pathElement) {
                            nextNode = child;
                            break;
                        }
                    }
                }
                if (nextNode == null) {
                    nextNode = new ConfigurationComponentTree(currentNode, pathElement);
                    currentNode.addChild(nextNode);
                }
                currentNode = nextNode;
            }
        }
        currentNode.addConfigurationParameter(parameter);
    }

}
