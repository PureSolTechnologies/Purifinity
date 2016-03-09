import {Component, NgZone} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '../../commons/panel.component';
import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';
import {DurationPipe} from '../../commons/pipes/duration.pipe';

class SystemHealth {

    startTime: string;
    uptime: number;
    usedMemory: number;
    maxMemory: number;
    averageLoad: number;
    availableCPUs: number;

}

@Component({
    selector: 'system-health',
    directives: [
        ROUTER_DIRECTIVES,
        PanelComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe
    ],
    templateUrl: '../../../html/components/monitor/system-health.html'
})
export class SystemHealthComponent {

    connection: string = "Not Connected.";
    error: any;
    status: SystemHealth;
    websocket: WebSocket;
    zone: NgZone;

    constructor(zone: NgZone) {
        this.zone = zone;
        var server: any = PurifinityConfiguration.server;
        this.websocket = new WebSocket("ws://" + server.host + ":" + server.port + "/purifinityserver/socket/status");
        var shc = this;
        this.websocket.onopen = function(event) {
            zone.run(() => {
                shc.connection = "Connected.";
                shc.websocket.send("sendStatus");
            });
        }
        this.websocket.onclose = function(event) {
            zone.run(() => {
                shc.connection = "Connection closed.";
            });
        }
        this.websocket.onmessage = function(event) {
            zone.run(() => {
                shc.status = JSON.parse(event.data);
            });
        }
        this.websocket.onerror = function(event) {
            zone.run(() => {
                shc.error = event;
            });
        }
    }

    getUptime(): number {
        if ((this.status) && (this.status.uptime)) {
            return this.status.uptime;
        }
        return 0.0;
    }

    getMemorySeverity() {
        if (!this.status || !this.status.usedMemory || !this.status.maxMemory) {
            return "";
        }
        var usage = this.status.usedMemory / this.status.maxMemory;
        if (usage < 0.75) {
            return "progress-success";
        }
        if (usage < 0.9) {
            return "progress-warning";
        }
        return "progress-danger";
    }

    getCPUSeverity() {
        var usage = this.getCPUUsage();
        if (usage === 0.0) {
            return "";
        }
        if (usage < 0.75) {
            return "progress-success";
        }
        if (usage < 0.9) {
            return "progress-warning";
        }
        return "progress-danger";
    }

    getCPUUsage() {
        if (!this.status || !this.status.averageLoad || (this.status.averageLoad < 0) || !this.status.availableCPUs) {
            return 0.0;
        }
        return this.status.averageLoad / this.status.availableCPUs;
    }

    getStartTime(): string {
        if ((this.status) && (this.status.startTime)) {
            return this.status.startTime;
        }
        return "";
    }

    getUsedMemory(): number {
        if ((this.status) && (this.status.usedMemory)) {
            return this.status.usedMemory;
        }
        return 0.0;
    }

    getMaxMemory(): number {
        if ((this.status) && (this.status.maxMemory)) {
            return this.status.maxMemory;
        }
        return 0.0;
    }

    getMemoryProgressWidth(): number {
        if ((this.status) && (this.status.usedMemory) && (this.status.maxMemory)) {
            return this.status.usedMemory / this.status.maxMemory * 100
        }
        return 0.0;
    }

    getAvailableCPUs(): number {
        if ((this.status) && (this.status.availableCPUs)) {
            return this.status.availableCPUs;
        }
        return 0.0;
    }
}
