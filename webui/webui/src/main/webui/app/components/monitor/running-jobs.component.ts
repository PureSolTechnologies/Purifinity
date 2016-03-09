import {Component, NgZone} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '../../commons/panel.component';
import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';

@Component({
    selector: 'running-jobs',
    directives: [
        ROUTER_DIRECTIVES,
        PanelComponent
    ],
    pipes: [
        DefaultDatePipe
    ],
    templateUrl: '../../../html/components/monitor/running-jobs.html'
})
export class RunningJobsComponent {

    connection: string = "Not Connected.";
    error: any;
    jobs: any;
    websocket: WebSocket;
    zone: NgZone;

    constructor(zone: NgZone/*, purifinityServerConnector: PurifinityServerConnector*/) {
        this.zone = zone;
        var server = PurifinityConfiguration.server;
        this.websocket = new WebSocket("ws://" + server.host + ":" + server.port + "/purifinityserver/socket/jobs");
        var rjc = this;
        this.websocket.onopen = function(event) {
            zone.run(() => {
                rjc.connection = "Connected.";
                rjc.websocket.send("sendJobStates");
            });
        };
        this.websocket.onclose = function(event) {
            zone.run(() => {
                rjc.connection = "Connection closed.";
            });
        };
        this.websocket.onmessage = function(event) {
            zone.run(() => {
                rjc.jobs = JSON.parse(event.data);
            });
        };
        this.websocket.onerror = function(event) {
            zone.run(() => {
                rjc.error = event;
            });
        };
    }

    abortRun(projectId, jobId) {
        /*        purifinityServerConnector.put("/purifinityserver/rest/analysis/projects/" + projectId + "/abort/" + jobId, "",
                    function(data, status) { },
                    function(data, status, error) { }
                );*/
    };
}
