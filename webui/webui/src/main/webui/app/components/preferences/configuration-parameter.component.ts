import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {ConfigurationParameter} from '../../commons/configuration/ConfigurationParameter';
import {PreferencesManager} from '../../commons/purifinity/PreferencesManager';
import {PreferencesGroup} from '../../commons/preferences/PreferencesGroup';

@Component({
    selector: 'configuration-parameter',
    directives: [],
    pipes: [],
    templateUrl: '../../../html/components/preferences/configuration-parameter.html'
})
export class ConfigurationParameterComponent {

    @Input() parameter: ConfigurationParameter;

    currentValue: any;
    defaultValue: any;
    overriding: boolean = false;
    booleanInput: boolean = false;
    textInput: string = "";
    numberInput: number = 0;

    constructor(private preferencesManager: PreferencesManager) {
    }

    ngOnInit() {
        this.refresh();
    }

    isDefault(): boolean {
        if (this.isLocked()) {
            return true;
        }
        if (this.isBoolean()) {
            return this.defaultValue === this.booleanInput;
        } else if (this.isText()) {
            return this.defaultValue === this.textInput;
        } else if (this.isNumber()) {
            return this.defaultValue === this.numberInput;
        } else {
            return this.defaultValue === this.textInput;
        }
    }

    wasChanged(): boolean {
        if (this.isLocked()) {
            return false;
        }
        if (this.isBoolean()) {
            return this.currentValue !== this.booleanInput;
        } else if (this.isText()) {
            return this.currentValue !== this.textInput;
        } else if (this.isNumber()) {
            return this.currentValue !== this.numberInput;
        } else {
            return this.currentValue !== this.textInput;
        }
    }

    isLocked(): boolean {
        if (!this.isOverride()) {
            return false;
        }
        return !this.isOverriding();
    }

    commit() {
        let configurationParameterComponent = this;
        if (this.isBoolean()) {
            this.preferencesManager.setParameter(this.parameter, String(this.booleanInput),
                function(response: Response) {
                    configurationParameterComponent.currentValue = configurationParameterComponent.booleanInput;
                }, function(response: Response) {
                });
        } else if (this.isText()) {
            this.preferencesManager.setParameter(this.parameter, String(this.textInput),
                function(response: Response) {
                    configurationParameterComponent.currentValue = configurationParameterComponent.textInput;
                }, function(response: Response) {
                });
        } else if (this.isNumber()) {
            this.preferencesManager.setParameter(this.parameter, String(this.numberInput),
                function(response: Response) {
                    configurationParameterComponent.currentValue = configurationParameterComponent.numberInput;
                }, function(response: Response) {
                });
        }
    };

    rollback() {
        if (this.isBoolean()) {
            this.booleanInput = this.currentValue;
        } else if (this.isText()) {
            this.textInput = this.currentValue;
        } else if (this.isNumber()) {
            this.numberInput = this.currentValue;
        } else {
            this.textInput = this.currentValue;
        }
    }

    refresh() {
        let configurationParameterComponent = this;
        this.preferencesManager.getParameter(this.parameter,
            function(response: Response) {
                let data = response.text();
                if (configurationParameterComponent.isBoolean()) {
                    if (data) {
                        configurationParameterComponent.booleanInput = (data === "true");
                    } else {
                        configurationParameterComponent.booleanInput = configurationParameterComponent.parameter.defaultValue;
                    }
                    configurationParameterComponent.currentValue = configurationParameterComponent.booleanInput;
                    configurationParameterComponent.defaultValue = configurationParameterComponent.parameter.defaultValue;
                } else if (configurationParameterComponent.isText()) {
                    if (data) {
                        configurationParameterComponent.textInput = String(data);
                    } else {
                        configurationParameterComponent.textInput = configurationParameterComponent.parameter.defaultValue;
                    }
                    configurationParameterComponent.currentValue = configurationParameterComponent.textInput;
                    configurationParameterComponent.defaultValue = String(configurationParameterComponent.parameter.defaultValue);
                } else if (configurationParameterComponent.isNumber()) {
                    if (data) {
                        configurationParameterComponent.numberInput = Number(data);
                    } else {
                        configurationParameterComponent.numberInput = configurationParameterComponent.parameter.defaultValue;
                    }
                    configurationParameterComponent.currentValue = configurationParameterComponent.numberInput;
                    configurationParameterComponent.defaultValue = Number(configurationParameterComponent.parameter.defaultValue);
                } else {
                    if (data) {
                        configurationParameterComponent.textInput = data;
                    } else {
                        configurationParameterComponent.textInput = configurationParameterComponent.parameter.defaultValue;
                    }
                    configurationParameterComponent.currentValue = data;
                    configurationParameterComponent.defaultValue = data;
                }
                if (configurationParameterComponent.currentValue || (!configurationParameterComponent.isOverride())) {
                    configurationParameterComponent.overriding = true;
                }
            }, function(response: Response) {
            });
    }

    isBoolean(): boolean {
        return this.parameter.valueRepresentation === "BOOLEAN";
    }

    isText(): boolean {
        return this.parameter.valueRepresentation === "STRING";
    }

    isNumber(): boolean {
        return (this.parameter.valueRepresentation === "DECIMAL") || (this.parameter.valueRepresentation === "INTEGER");
    }

    getBooleanText(): string {
        if (this.booleanInput) {
            return "enabled";
        }
        return "disabled";
    }

    getBooleanButtonClass(): string {
        if (this.booleanInput) {
            return "btn-success";
        }
        return "btn-danger";
    }

    toggleBoolean(): void {
        this.booleanInput = !this.booleanInput;
    }

    isOverride(): boolean {
        switch (this.parameter.preferencesGroup) {
            case PreferencesGroup.SYSTEM:
            case PreferencesGroup.PLUGIN_DEFAULT:
            case PreferencesGroup.USER_DEFAULT:
                return false;
            case PreferencesGroup.PLUGIN_PROJECT:
            case PreferencesGroup.USER:
                return true;
            default:
                return false;
        }
    }

    isOverriding(): boolean {
        return this.overriding;
    }

    toggleOverriding() {
        if (this.overriding) {
            this.preferencesManager.deleteParameter(this.parameter,
                function(response: Response) {
                }, function(response: Response) {
                });
        }
        this.overriding = !this.overriding;
    }

    getOverrideButtonClass() {
        if (this.isOverriding()) {
            return "btn-success";
        } else {
            return "btn-default";
        }
    }

    setDefault(): void {
        if (this.isBoolean()) {
            this.booleanInput = this.defaultValue;
        } else if (this.isText()) {
            this.textInput = this.defaultValue;
        } else if (this.isNumber()) {
            this.numberInput = this.defaultValue;
        } else {
            this.textInput = this.defaultValue;
        }
    }
}
