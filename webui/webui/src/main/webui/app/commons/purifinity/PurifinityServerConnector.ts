import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {AuthenticationService} from '../auth/AuthenticationService';
import {HTTPRequests} from './HTTPRequests';

/**
* This class is the central class to connect to the Purifinity server. All 
* communication to the server shall be done with this class. 
*/
@Injectable()
export class PurifinityServerConnector {

    private baseURL: string;

    constructor(
        private authService: AuthenticationService,
        private httpRequests: HTTPRequests) {
        this.baseURL = httpRequests.getBaseURL();
    }

    get(
        serviceURL: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var authId = "";
        var authToken = "";
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.GET(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }

    get_text(
        serviceURL: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var authId = "";
        var authToken = "";
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.GET_TEXT(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }

    put(
        serviceURL: string,
        data: any,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var authId = "";
        var authToken = "";
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.PUT(this.baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
    }

    post(
        serviceURL: string,
        data: any,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var authId = "";
        var authToken = "";
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.POST(this.baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
    }

    del(
        serviceURL: string,
        successCallback: (response: Response) => void,
        errorCallback: (response: Response) => void) {
        var authId = "";
        var authToken = "";
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.DELETE(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }
}
