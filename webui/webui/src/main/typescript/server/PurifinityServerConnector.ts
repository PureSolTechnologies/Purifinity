/**
 * This class is the central class to connect to the Purifinity server. All 
 * communication to the server shall be done with this class. 
 */
class PurifinityServerConnector {

    constructor(private authService: AuthenticationService,
        private httpRequests: HTTPRequests,
        private baseURL: string) {
    }

    get(serviceURL: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.GET(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }

    get_text(serviceURL: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.GET_TEXT(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }

    put(serviceURL: string,
        data: any,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.PUT(this.baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
    }

    put_text(serviceURL: string,
        data: any,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.PUT_TEXT(this.baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
    }

    post(serviceURL: string,
        data: any,
        successCallback: ( data: any, status: number) => void,
        errorCallback: ( data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.POST(this.baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
    }

    del(serviceURL: string,
        successCallback: ( data: any, status: number) => void,
        errorCallback: ( data: any, status: number, error: string) => void) {
        var authId = '';
        var authToken = '';
        if (this.authService.authData) {
            authId = this.authService.authData.authId;
            authToken = this.authService.authData.authToken;
        }
        return this.httpRequests.DELETE(this.baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
    }
}