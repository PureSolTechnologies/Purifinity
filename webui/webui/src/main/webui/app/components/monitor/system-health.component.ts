import {Component, NgZone} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '../../commons/panel.component';

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
    templateUrl: '../../../html/components/monitor/system-health.html'
})
export class SystemHealthComponent {

    connection: string = "Not Connected.";
    error: string = undefined;
    status: SystemHealth;
    websocket: WebSocket;
    zone: NgZone;

    constructor(zone: NgZone) {
        this.zone = zone;
        var server: any = PurifinityConfiguration.server;
        this.websocket = new WebSocket("ws://" + server.host + ":" + server.port + "/purifinityserver/socket/status");
        var websocket: WebSocket = this.websocket;
        this.websocket.onopen = function(event) {
            zone.run(() => {
                this.connection = "Connected.";
                websocket.send("sendStatus");
            });
        }
        this.websocket.onclose = function(event) {
            zone.run(() => {
                this.connection = "Connection closed.";
            });
        }
        this.websocket.onmessage = function(event) {
            zone.run(() => {
                this.status = JSON.parse(event.data);
            });
        }
        this.websocket.onerror = function(event) {
            zone.run(() => {
                this.error = event;
            });
        }
    }

    getUptimeString() {
        if (!this.status) {
            return "";
        }
        var milliseconds = this.status.uptime;
        if (milliseconds < 1000) {
            return milliseconds + "ms";
        }
        var seconds = Math.floor(milliseconds / 1000);
        milliseconds = milliseconds % 1000;
        if (seconds < 60) {
            return seconds + "s";
        }
        var minutes = Math.floor(seconds / 60);
        seconds = seconds % 60;
        if (minutes < 60) {
            return minutes + "min " + seconds + "s";
        }
        var hours = Math.floor(minutes / 60);
        minutes = minutes % 60;
        if (hours < 24) {
            return hours + "hr " + minutes + "min " + seconds + "s";
        }
        var days = Math.floor(hours / 24);
        hours = hours % 24;
        return days + "days " + hours + "hr " + minutes + "min " + seconds + "s";
    }

    getMemorySeverity() {
        if (!this.status || !this.status.usedMemory || !this.status.maxMemory) {
            return "";
        }
        var usage = this.status.usedMemory / this.status.maxMemory;
        if (usage < 0.75) {
            return "progress-bar-success";
        }
        if (usage < 0.9) {
            return "progress-bar-warning";
        }
        return "progress-bar-danger";
    }

    getCPUSeverity() {
        var usage = this.getCPUUsage();
        if (usage === 0.0) {
            return "";
        }
        if (usage < 0.75) {
            return "progress-bar-success";
        }
        if (usage < 0.9) {
            return "progress-bar-warning";
        }
        return "progress-bar-danger";
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
