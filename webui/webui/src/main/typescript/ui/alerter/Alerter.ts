class Alerter {

    public alerts: Alert[] = [];

    /*
     * Type: info, danger, success, warning
     */
    addAlert(severity, message) {
        if (severity && message) {
            this.alerts.push(new Alert(severity, message));
        }
    }

    closeAlert(index) {
        this.alerts.splice(index, 1);
    }

    clear() {
        this.alerts.splice(0, this.alerts.length);
    }

    hasAlerts() {
        if (this.alerts === undefined) {
            return false;
        }
        return this.alerts.length > 0;
    }
}
