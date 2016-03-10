import {Injectable} from 'angular2/core';
import {Location} from 'angular2/router';
import {Http, Headers} from 'angular2/http';

import {Alerter} from '../alerter/Alerter';

@Injectable()
export class HTTPRequests {

    constructor(
        private http: Http,
        private location: Location,
        private alerter: Alerter) {
    }

    getBaseURL(): string {
        return "http://" + PurifinityConfiguration.server.host + ":" + PurifinityConfiguration.server.port;
    }

    GET(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.get(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    GET_TEXT(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.get(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    POST(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.post(url, data,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    PUT(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.put(url, data,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    PUT_TEXT(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        headers.append("Content-Type", "text/plain");
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.put(url, data,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    DELETE(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: string) => void,
        errorCallback: (data: any, status: string, error: string) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.delete(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data, status),
            err => {
                if (status === "401") {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err, status, "");
            });
    }

    handle401(data: any) {
        this.alerter.addAlert("info", data);
        window.location.href = "/#/login";
        sessionStorage.setItem("redirect.after.login", this.location.path());
    }

    createAuthHeaders(authId: string, authToken: string): Headers {
        var headers = new Headers();
        headers.append("auth-id", authId);
        headers.append("auth-token", authToken);
        return headers;
    }
}
