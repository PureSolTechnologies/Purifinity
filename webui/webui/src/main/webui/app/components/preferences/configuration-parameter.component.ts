import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {ConfigurationParameter} from '../../commons/configuration/ConfigurationParameter';
import {PreferencesManager} from '../../commons/purifinity/PreferencesManager';
import {PreferencesGroup} from '../../commons/preferences/PreferencesGroup';

enum ValueType { TEXT, NUMBER, BOOLEAN }

class Values {

    valueType: ValueType;
    currentValue: any;
    defaultValue: any;
    overriding: boolean = false;
    booleanInput: boolean = false;
    textInput: string = "";
    numberInput: number = 0;

    constructor(valueType: ValueType, currentValue: any, defaultValue: any, overriding: boolean) {
        this.valueType = valueType;
        this.currentValue = currentValue;
        this.defaultValue = defaultValue;
        this.overriding = overriding;
        this.setDefault();
    }

    isDefault(): boolean {
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

    wasChanged(): boolean {
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

    isBoolean(): boolean {
        return this.valueType === ValueType.BOOLEAN;
    }

    isNumber(): boolean {
        return this.valueType === ValueType.NUMBER;
    }

    isText(): boolean {
        return this.valueType === ValueType.TEXT;
    }
}

@Component({
    selector: 'configuration-parameter',
    directives: [],
    pipes: [],
    templateUrl: '../../../html/components/preferences/configuration-parameter.html'
})
export class ConfigurationParameterComponent {

    @Input() parameter: ConfigurationParameter;

    values: Values;
    preferencesManager: PreferencesManager;

    constructor(preferencesManager: PreferencesManager) {
        this.preferencesManager = preferencesManager;
        this.refresh();
    }

    isDefault(): boolean {
        if (this.isLocked()) {
            return true;
        }
        return this.values.isDefault();
    }

    wasChanged(): boolean {
        if (this.isLocked()) {
            return false;
        }
        return this.values.wasChanged();
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
            this.preferencesManager.setParameter(this.parameter, String(this.values.booleanInput),
                function(response: Response) {
                    configurationParameterComponent.values.currentValue = configurationParameterComponent.values.booleanInput;
                }, function(response: Response) {
                });
        } else if (this.isText()) {
            this.preferencesManager.setParameter(this.parameter, String(this.values.textInput),
                function(response: Response) {
                    configurationParameterComponent.values.currentValue = configurationParameterComponent.values.textInput;
                }, function(response: Response) {
                });
        } else if (this.isNumber()) {
            this.preferencesManager.setParameter(this.parameter, String(this.values.numberInput),
                function(response: Response) {
                    configurationParameterComponent.values.currentValue = configurationParameterComponent.values.numberInput;
                }, function(response: Response) {
                });
        }
    };

    rollback() {
        if (this.isBoolean()) {
            this.values.booleanInput = this.values.currentValue;
        } else if (this.isText()) {
            this.values.textInput = this.values.currentValue;
        } else if (this.isNumber()) {
            this.values.numberInput = this.values.currentValue;
        } else {
            this.values.textInput = this.values.currentValue;
        }
    }

    refresh() {
        this.preferencesManager.getParameter(this.parameter,
            function(response: Response) {
                let data = response.json();
                if (this.isBoolean()) {
                    if (data) {
                        this.values.booleanInput = (data === "true");
                    } else {
                        this.values.booleanInput = this.parameter.defaultValue;
                    }
                    this.values.currentValue = this.values.booleanInput;
                    this.values.defaultValue = this.parameter.defaultValue;
                } else if (this.isText()) {
                    if (data) {
                        this.values.textInput = String(data);
                    } else {
                        this.values.textInput = this.parameter.defaultValue;
                    }
                    this.values.currentValue = this.values.textInput;
                    this.values.defaultValue = String(this.parameter.defaultValue);
                } else if (this.isNumber()) {
                    if (data) {
                        this.values.numberInput = Number(data);
                    } else {
                        this.values.numberInput = this.parameter.defaultValue;
                    }
                    this.values.currentValue = this.values.numberInput;
                    this.values.defaultValue = Number(this.parameter.defaultValue);
                } else {
                    if (data) {
                        this.values.textInput = data;
                    } else {
                        this.values.textInput = this.parameter.defaultValue;
                    }
                    this.values.currentValue = data;
                    this.values.defaultValue = data;
                }
                if (this.values.currentValue || (!this.isOverride())) {
                    this.overriding = true;
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
        if (this.values.booleanInput) {
            return "enabled";
        }
        return "disabled";
    }

    getBooleanButtonClass(): string {
        if (this.values.booleanInput) {
            return "btn-success";
        }
        return "btn-danger";
    }

    toggleBoolean(): void {
        this.values.booleanInput = !this.values.booleanInput;
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
        return this.values.overriding;
    }

    toggleOverriding() {
        if (this.values.overriding) {
            this.preferencesManager.deleteParameter(this.parameter,
                function(response: Response) {
                }, function(response: Response) {
                });
        }
        this.values.overriding = !this.values.overriding;
    }

    getOverrideButtonClass() {
        if (this.isOverriding()) {
            return "btn-success";
        } else {
            return "btn-default";
        }
    }

}
