import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router, RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {AdminMenuComponent} from './admin-menu.component';
import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {PluginManager} from '../commons/purifinity/PluginManager';
import {PreferencesManager} from '../commons/purifinity/PreferencesManager';
import {Project} from '../commons/domain/Project';
import {RepositoryType} from '../commons/domain/RepositoryType';
import {ConfigurationParameter} from '../commons/configuration/ConfigurationParameter';

import {PanelComponent} from '../components/panel.component';
import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';
import {ConfigurationComponentData} from '../commons/configuration/ConfigurationComponentData';
import {ConfigurationComponentTree} from '../commons/configuration/ConfigurationComponentTree';
import {AnalyzerServiceInformation} from '../commons/plugins/AnalyzerServiceInformation';
import {EvaluatorServiceInformation} from '../commons/plugins/EvaluatorServiceInformation';
import {RepositoryServiceInformation} from '../commons/plugins/RepositoryServiceInformation';
import {PreferencesGroup} from '../commons/preferences/PreferencesGroup';
import {PreferencesGroupIdentifier} from '../commons/preferences/PreferencesGroupIdentifier';

@Component({
    selector: 'edit-project',
    directives: [
        AdminMenuComponent,
        PanelComponent,
        TabComponent,
        TabSetComponent
    ],
    templateUrl: '../../html/admin/edit-project.html'
})
export class EditProjectComponent {

    private routeParams: RouteParams;
    private projectManager: ProjectManager;
    private pluginManager: PluginManager;
    private preferencesManager: PreferencesManager;

    private projectId: string;
    name: string = "";
    description: string = "";
    preAnalysisScript: string = "";
    repositoryId: string = "";
    repositoryProperties: any = [];
    directoryIncludes: string = "";
    directoryExcludes: string = "";
    fileIncludes: string = "";
    fileExcludes: string = "";
    ignoreHidden: boolean = true;
    repositoryTypes: RepositoryType[] = [];
    pluginSettings: ConfigurationComponentData;
    analyzerPlugins: any = {};
    evaluatorPlugins: any = {};
    repositoryPlugins: any = {};

    constructor(routeParams: RouteParams, projectManager: ProjectManager, pluginManager: PluginManager,
        preferencesManager: PreferencesManager) {

        this.routeParams = routeParams;
        this.projectManager = projectManager;
        this.pluginManager = pluginManager;
        this.preferencesManager = preferencesManager;

        this.projectId = routeParams.get('projectId');

        this.refresh();
        let editProjectComponent = this;
        projectManager.getRepositoryTypes(
            function(repositoryTypes: RepositoryType[]) { editProjectComponent.repositoryTypes = repositoryTypes }, //
            function(response: Response) { });

        this.pluginSettings = new ConfigurationComponentData("Plug-ins", PreferencesGroup.PLUGIN_PROJECT);

        let analyzersNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Analyzers");
        this.pluginSettings.root.addChild(analyzersNode);
        let evaluatorsNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Evaluators");
        this.pluginSettings.root.addChild(evaluatorsNode);
        let repositoriesNode: ConfigurationComponentTree = new ConfigurationComponentTree(this.pluginSettings.root, "Repositories");
        this.pluginSettings.root.addChild(repositoriesNode);
        this.pluginManager.getAnalyzers(
            function(analyzers: AnalyzerServiceInformation[]) {
                editProjectComponent.analyzerPlugins = analyzers;
                for (let i = 0; i < analyzers.length; i++) {
                    let plugin: any = analyzers[i];
                    let analyzerNode: ConfigurationComponentTree = new ConfigurationComponentTree(analyzersNode, plugin.name);
                    analyzersNode.addChild(analyzerNode);
                    editProjectComponent.addPluginConfiguration(analyzerNode, plugin.id);
                }
            },
            function(response: Response) { }
        );
        pluginManager.getEvaluators(
            function(evaluators: EvaluatorServiceInformation[]) {
                editProjectComponent.evaluatorPlugins = evaluators;
                for (let i = 0; i < evaluators.length; i++) {
                    let plugin = evaluators[i];
                    let evaluatorNode: ConfigurationComponentTree = new ConfigurationComponentTree(evaluatorsNode, plugin.name);
                    evaluatorsNode.addChild(evaluatorNode);
                    editProjectComponent.addPluginConfiguration(evaluatorNode, plugin.id);
                }
            },
            function(response: Response) { }
        );
        pluginManager.getRepositoryTypes(
            function(repositories: RepositoryServiceInformation[]) {
                editProjectComponent.repositoryPlugins = repositories;
                for (let i = 0; i < repositories.length; i++) {
                    let plugin = repositories[i];
                    let repositoryNode: ConfigurationComponentTree = new ConfigurationComponentTree(repositoriesNode, plugin.name);
                    repositoriesNode.addChild(repositoryNode);
                    editProjectComponent.addPluginConfiguration(repositoryNode, plugin.id);
                }
            },
            function(response: Response) { }
        );

    }

    refresh(): void {
        let editProjectComponent = this;
        this.projectManager.getProject(this.projectId, function(project: Project) {
            editProjectComponent.projectId = project.information.projectId;
            editProjectComponent.name = project.settings.name;
            editProjectComponent.description = project.settings.description;
            editProjectComponent.preAnalysisScript = project.settings.preAnalysisScript;
            editProjectComponent.repositoryId = project.settings.repository["repository.id"];
            editProjectComponent.fileIncludes = "";
            project.settings.fileSearchConfiguration.fileIncludes.forEach(function(line: string, num: number) {
                if (num > 0) {
                    editProjectComponent.fileIncludes += "\n";
                }
                if (line) {
                    editProjectComponent.fileIncludes += line;
                }
            });
            editProjectComponent.fileExcludes = "";
            project.settings.fileSearchConfiguration.fileExcludes.forEach(function(line: string, num: number) {
                if (num > 0) {
                    editProjectComponent.fileExcludes += "\n";
                }
                if (line) {
                    editProjectComponent.fileExcludes += line;
                }
            });
            editProjectComponent.directoryIncludes = "";
            project.settings.fileSearchConfiguration.locationIncludes.forEach(function(line: string, num: number) {
                if (num > 0) {
                    editProjectComponent.directoryIncludes += "\n";
                }
                if (line) {
                    editProjectComponent.directoryIncludes += line;
                }
            });
            editProjectComponent.directoryExcludes = "";
            project.settings.fileSearchConfiguration.locationExcludes.forEach(function(line: string, num: number) {
                if (num > 0) {
                    editProjectComponent.directoryExcludes += "\n";
                }
                if (line) {
                    editProjectComponent.directoryExcludes += line;
                }
            });
            editProjectComponent.ignoreHidden = project.settings.fileSearchConfiguration.ignoreHidden;
        }, function(response: Response) { });
    };

    ok(): void {
        let projectSettings = {
            "name": this.name,
            "description": this.description,
            "preAnalysisScript": this.preAnalysisScript,
            "fileSearchConfiguration": {
                "locationIncludes": this.directoryIncludes.split("\n"),
                "locationExcludes": this.directoryExcludes.split("\n"),
                "fileIncludes": this.fileIncludes.split("\n"),
                "fileExcludes": this.fileExcludes.split("\n"),
                "ignoreHidden": this.ignoreHidden
            },
            "repository": {
                "repository.id": this.repositoryId
            }
        };
        for (var key in this.repositoryProperties) {
            projectSettings.repository[key] = this.repositoryProperties[key];
        }

        this.projectManager.updateProjectSettings(this.projectId, projectSettings, function(data: Project, status) {
        }, function(response: Response) { });
    };

    cancel(): void {
        this.refresh();
    };

    selectRepository(newRepositoryId: string) {
        this.repositoryId = newRepositoryId;
        for (let key in this.repositoryTypes) {
            let repository = this.repositoryTypes[key];
            if (repository.id === this.repositoryId) {
                this.repositoryProperties = [];
                for (let name in repository.parameters) {
                    let parameter: any = repository.parameters[name];
                    parameter.uiName = name;
                    this.repositoryProperties.push(parameter);
                }
            }
        }
    }

    addPluginConfiguration(pluginNode: ConfigurationComponentTree, pluginId: string) {
        let editProjectComponent = this;
        this.preferencesManager.getPluginProjectParameters(pluginId, this.projectId,
            function(response: Response) {
                let data: any = response.json();
                for (let i = 0; i < data.length; i++) {
                    let parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.PLUGIN_PROJECT, new PreferencesGroupIdentifier(pluginId, editProjectComponent.projectId), data[i]);
                    ConfigurationComponentData.addConfigurationParameter(pluginNode, parameter);
                }
            },
            function(response: Response) { }
        );
    }
}
