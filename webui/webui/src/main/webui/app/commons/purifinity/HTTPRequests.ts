import {Injectable} from 'angular2/core';
import {Router, Location} from 'angular2/router';
import {Http, Headers, Response} from 'angular2/http';

import {Alerter} from '../alerter/Alerter';

@Injectable()
export class HTTPRequests {

    constructor(
        private http: Http,
        private router: Router,
        private location: Location,
        private alerter: Alerter) {
    }

    getBaseURL(): string {
        return "http://" + PurifinityConfiguration.server.host + ":" + PurifinityConfiguration.server.port;
    }

    GET(url: string,
        authId: string,
        authToken: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.get(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data),
            err => {
                if (err.status === 401) {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err);
            });
    }

    GET_TEXT(url: string,
        authId: string,
        authToken: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.get(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data),
            err => {
                if (err.status === 401) {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err);
            });
    }

    POST(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        headers.append("Content-type", "application/json"); 
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.post(url, JSON.stringify(data),
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data),
            err => {
                if (err.status === 401) {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err);
            });
    }

    PUT(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        headers.append("Content-type", "application/json"); 
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.put(url, JSON.stringify(data),
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data),
            err => {
                if (err.status === 401) {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err);
            });
    }

    DELETE(url: string,
        authId: string,
        authToken: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var headers = this.createAuthHeaders(authId, authToken);
        var alerter: Alerter = this.alerter;
        var location: Location = this.location;
        var $requests = this;
        return this.http.delete(url,
            {
                headers: headers
            })
            .subscribe(
            data => successCallback(data),
            err => {
                if (err.status === 401) {
                    $requests.handle401(err);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + err);
                errorCallback(err);
            });
    }

    handle401(data: any) {
        this.alerter.addAlert("info", data);
        sessionStorage.setItem("redirect.after.login", this.location.path());
        this.router.navigate(['Login']);
    }

    createAuthHeaders(authId: string, authToken: string): Headers {
        var headers = new Headers();
        headers.append("auth-id", authId);
        headers.append("auth-token", authToken);
        return headers;
    }
}
