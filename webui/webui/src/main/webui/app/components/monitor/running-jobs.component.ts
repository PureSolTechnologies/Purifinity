import {Component, NgZone, Inject} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {PanelComponent} from '../../components/panel.component';
import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';
import {PurifinityServerConnector} from '../../commons/purifinity/PurifinityServerConnector';

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

    private zone: NgZone;
    private purifinityServerConnector: PurifinityServerConnector;
    private connection: string = "Not Connected.";
    private error: any;
    private jobs: any;
    private websocket: WebSocket;

    constructor(zone: NgZone, purifinityServerConnector: PurifinityServerConnector) {
        this.zone = zone;
        this.purifinityServerConnector = purifinityServerConnector;
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
        this.purifinityServerConnector.put("/purifinityserver/rest/analysis/projects/" + projectId + "/abort/" + jobId, "",
            function(response: Response) { },
            function(response: Response) { }
        );
    };

    getJobStates(): any[] {
        if (this.jobs && this.jobs.jobStates) {
            return this.jobs.jobStates;
        }
        return [];
    }

    hasJobStates(): boolean {
        if (this.jobs && this.jobs.jobStates) {
            return this.jobs.jobStates.length > 0;
        }
        return false;
    }
}
