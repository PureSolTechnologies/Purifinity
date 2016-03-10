import {Component} from 'angular2/core';

@Component({
    selector: 'alerter',
    directives: [],
    templateUrl: '../../../html/commons/alerter/alerter.html'
})
export class AlerterComponent {

    private alerter: Alerter;

    constructor(alerter: Alerter) {
        this.alerter = alerter;
    }

    /*
     * Type: info, danger, success, warning
     */
    addAlert(severity: string, message: string) {
        if (severity && message) {
            this.alerter.addAlert(severity, message);
        }
    }

    closeAlert(index) {
        this.alerter.closeAlert(index);
    }

    clearAlerts() {
        this.alerter.clear();
    }

    hasAlerts() {
        return this.alerter.hasAlerts();
    }
}
