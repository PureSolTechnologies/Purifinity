import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '..//panel.component';
import {ConfigurationParameterComponent} from './configuration-parameter.component';
import {ConfigurationComponentData} from '../../commons/configuration/ConfigurationComponentData';
import {ConfigurationComponentTree} from '../../commons/configuration/ConfigurationComponentTree';

@Component({
    selector: 'configuration-component',
    directives: [
        PanelComponent,
        ConfigurationParameterComponent
    ],
    templateUrl: '../../../html/components/preferences/configuration-component.html'
})
export class ConfigurationComponent {

    @Input()
    private configurationTreeData: ConfigurationComponentData;

    private path: ConfigurationComponentTree[] = [];
    private currentFolder: ConfigurationComponentTree;

    constructor(){}
    
    ngOnInit() {
        this.path = [];
        this.path.push(this.configurationTreeData.root);
        this.currentFolder = this.configurationTreeData.root;
    }

    chdir(dir: string): void {
        if (dir === "..") {
            if (this.path.length > 1) {
                this.path.pop();
                this.currentFolder = this.path[this.path.length - 1];
            }
            return;
        }
        for (var key in this.currentFolder.children) {
            if (this.currentFolder.children[key].name === dir) {
                var newFolder = this.currentFolder.children[key];
                this.path.push(newFolder);
                this.currentFolder = newFolder;
                return;
            }
        }
    }

    setDir(dir: string): void {
        while ((this.path.length > 1)
            && (this.path[this.path.length - 1].name !== dir)) {
            this.path.pop();
        }
        this.currentFolder = this.path[this.path.length - 1];
    }

}
