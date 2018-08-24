import {Component} from 'angular2/core';
import {Response} from 'angular2/http';

import {AdminMenuComponent} from './admin-menu.component';
import {Utilities} from '../commons/Utilities';
import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';
import {PreferencesManager} from '../commons/purifinity/PreferencesManager';
import {PluginManager} from '../commons/purifinity/PluginManager';
import {PreferencesGroup} from '../commons/preferences/PreferencesGroup';
import {PreferencesGroupIdentifier} from '../commons/preferences/PreferencesGroupIdentifier';
import {ConfigurationComponentData} from '../commons/configuration/ConfigurationComponentData';
import {ConfigurationParameter} from '../commons/configuration/ConfigurationParameter';
import {ConfigurationComponentTree} from '../commons/configuration/ConfigurationComponentTree';
import {AnalyzerServiceInformation} from '../commons/plugins/AnalyzerServiceInformation';
import {EvaluatorServiceInformation} from '../commons/plugins/EvaluatorServiceInformation';
import {RepositoryServiceInformation} from '../commons/plugins/RepositoryServiceInformation';

import {ConfigurationComponent} from '../components/preferences/configuration.component';

@Component({
    selector: 'settings-admin',
    directives: [
        AdminMenuComponent,
        TabSetComponent,
        TabComponent,
        ConfigurationComponent
    ],
    templateUrl: '../../html/admin/settings-admin.html'
})
export class SettingsAdminComponent {

    systemSettings: ConfigurationComponentData = new ConfigurationComponentData("Purifinity", PreferencesGroup.SYSTEM);
    analyzers: AnalyzerServiceInformation[] = [];
    evaluators: EvaluatorServiceInformation[] = [];
    repositories: RepositoryServiceInformation[] = [];
    analyzerActivation: any = {};
    evaluatorActivation: any = {};
    pluginSettings: ConfigurationComponentData = new ConfigurationComponentData("Plug-ins", PreferencesGroup.PLUGIN_DEFAULT);
    analyzersNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Analyzers");
    evaluatorsNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Evaluators");
    repositoriesNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Repositories");

    constructor(private preferencesManager: PreferencesManager, private pluginManager: PluginManager) {
        this.pluginSettings.root.addChild(this.analyzersNode);
        this.pluginSettings.root.addChild(this.evaluatorsNode);
        this.pluginSettings.root.addChild(this.repositoriesNode);

        let settingsAdminComponent = this;
        preferencesManager.getSystemParameters(
            function(response: Response) {
                var data = response.json();
                for (var i = 0; i < data.length; i++) {
                    var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.SYSTEM, new PreferencesGroupIdentifier(), data[i]);
                    ConfigurationComponentData.addConfigurationParameter(
                        settingsAdminComponent.systemSettings.root,
                        parameter
                    );
                }
            },
            function(response: Response) { }
        );
        pluginManager.getAnalyzers(
            function(data: AnalyzerServiceInformation[]) {
                settingsAdminComponent.analyzers = data;
                settingsAdminComponent.analyzers.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
                settingsAdminComponent.analyzers.forEach(function(analyzer) {
                    pluginManager.isAnalyzerEnabled(analyzer.id,
                        function(enabled: boolean) {
                            settingsAdminComponent.analyzerActivation[analyzer.id] = enabled;
                        },
                        function(response: Response) { }
                    );
                });
                for (var i = 0; i < data.length; i++) {
                    var plugin = data[i];
                    var analyzerNode: ConfigurationComponentTree = new ConfigurationComponentTree(settingsAdminComponent.analyzersNode, plugin.name);
                    settingsAdminComponent.analyzersNode.addChild(analyzerNode);
                    settingsAdminComponent.addPluginConfiguration(analyzerNode, plugin.id);
                }
            },
            function(response: Response) { }
        );
        pluginManager.getEvaluators(
            function(data: EvaluatorServiceInformation[]) {
                settingsAdminComponent.evaluators = data;
                settingsAdminComponent.evaluators.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
                settingsAdminComponent.evaluators.forEach(function(evaluator) {
                    pluginManager.isEvaluatorEnabled(evaluator.id,
                        function(enabled: boolean) {
                            settingsAdminComponent.evaluatorActivation[evaluator.id] = enabled;
                        },
                        function(response: Response) { }
                    );
                });
                for (var i = 0; i < data.length; i++) {
                    var plugin = data[i];
                    var evaluatorNode: ConfigurationComponentTree = new ConfigurationComponentTree(settingsAdminComponent.evaluatorsNode, plugin.name);
                    settingsAdminComponent.evaluatorsNode.addChild(evaluatorNode);
                    settingsAdminComponent.addPluginConfiguration(evaluatorNode, plugin.id);
                }
            },
            function(response: Response) { }
        );
        pluginManager.getRepositoryTypes(
            function(data: RepositoryServiceInformation[]) {
                settingsAdminComponent.repositories = data;
                settingsAdminComponent.repositories.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
                for (var i = 0; i < data.length; i++) {
                    var plugin = data[i];
                    var repositoryNode: ConfigurationComponentTree = new ConfigurationComponentTree(settingsAdminComponent.repositoriesNode, plugin.name);
                    settingsAdminComponent.repositoriesNode.addChild(repositoryNode);
                    settingsAdminComponent.addPluginConfiguration(repositoryNode, plugin.id);
                }
            },
            function(response: Response) { }
        );
    }

    getAnalyzerButtonClass(pluginId: string): string {
        if (this.analyzerActivation[pluginId]) {
            return "btn-success";
        } else {
            return "btn-danger";
        }
    }

    getAnalyzerButtonText(pluginId: string): string {
        if (this.analyzerActivation[pluginId]) {
            return "enabled";
        } else {
            return "disabled";
        }
    }

    getEvaluatorButtonClass(pluginId: string): string {
        if (this.evaluatorActivation[pluginId]) {
            return "btn-success";
        } else {
            return "btn-danger";
        }
    }

    getEvaluatorButtonText(pluginId: string): string {
        if (this.evaluatorActivation[pluginId]) {
            return "enabled";
        } else {
            return "disabled";
        }
    }

    toggleAnalyzerActivation(pluginId: string): void {
        this.analyzerActivation[pluginId] = !this.analyzerActivation[pluginId];
        if (this.analyzerActivation[pluginId]) {
            this.pluginManager.enableAnalyzer(pluginId,
                function() { },
                function(response: Response) { }
            );
        } else {
            this.pluginManager.disableAnalyzer(pluginId,
                function() { },
                function(response: Response) { }
            );
        }
    }

    toggleEvaluatorActivation(pluginId: string) {
        this.evaluatorActivation[pluginId] = !this.evaluatorActivation[pluginId];
        if (this.evaluatorActivation[pluginId]) {
            this.pluginManager.enableEvaluator(pluginId,
                function() { },
                function(response: Response) { }
            );
        } else {
            this.pluginManager.disableEvaluator(pluginId,
                function() { },
                function(response: Response) { }
            );
        }
    }

    addPluginConfiguration(pluginNode: ConfigurationComponentTree, pluginId: string) {
        this.preferencesManager.getPluginDefaultParameters(pluginId,
            function(response: Response) {
                let data = response.json();
                for (var i = 0; i < data.length; i++) {
                    var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.PLUGIN_DEFAULT, new PreferencesGroupIdentifier(pluginId), data[i]);
                    ConfigurationComponentData.addConfigurationParameter(pluginNode, parameter);
                }
            },
            function(response: Response) { }
        );
    }

    getAnalyzerActivation(analyzerId: string): boolean {
        if ((analyzerId) && (this.analyzerActivation[analyzerId])) {
            return this.analyzerActivation[analyzerId];
        }
        return false;
    }

    getEvaluatorActivation(evaluatorId: string): boolean {
        if ((evaluatorId) && (this.evaluatorActivation[evaluatorId])) {
            return this.evaluatorActivation[evaluatorId];
        }
        return false;
    }
}
