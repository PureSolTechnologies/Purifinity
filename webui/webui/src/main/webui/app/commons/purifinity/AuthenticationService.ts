import {Injectable} from 'angular2/core';
import {Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {HTTPRequests} from './HTTPRequests';
import {AuthenticationData} from './AuthenticationData';

@Injectable()
export class AuthenticationService {

    public authData: AuthenticationData;
    public redirect: string;
    private loginURL: string;
    private logoutURL: string;

    constructor(private router: Router,
        private httpRequests: HTTPRequests) {
        this.authData = this.loadAuthData();
        this.loginURL = "/purifinityserver/rest/auth/login";
        this.logoutURL = "/purifinityserver/rest/auth/logout";
    }

    /* Login functionality */
    login(email: string, password: string): boolean {
        var authenticated = false;
        var data = {
            email: email,
            password: password
        };
        var authService: AuthenticationService = this;
        this.httpRequests.POST(this.loginURL, data, "", "",
            function(response: Response) {
                authService.authData = response.json();
                authService.authData.authId = email;
                authenticated = true;
                authService.storeAuthData(authService.authData);
                var redirect = sessionStorage.getItem("redirect.after.login");
                if (redirect) {
                    authService.router.navigateByUrl(redirect);
                } else {
                    authService.router.navigate(['route']);
                }
            }, //
            function(response: Response) {
                authService.authData = undefined;
                authenticated = false;
                authService.removeAuthData();
                // Error handling
            });
        return authenticated;
    }

    /* Logout functionality */
    logout() {
        var data = {
            authId: this.authData.authId,
            token: this.authData.authToken
        };
        this.authData = undefined;
        this.removeAuthData();
        this.httpRequests.POST(this.logoutURL, data, "", "",
            function(response: Response) {
            },
            function(response: Response) {
                // Error handling
            });
    }

    /* Check for login functionality */
    isLoggedIn(): boolean {
        if (this.authData) {
            if (this.authData.authToken && this.authData.authToken.length > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function reads authentication data from storage.
     */
    loadAuthData(): AuthenticationData {
        var data = localStorage.getItem("purifinity-authentication");
        if (!data) {
            data = sessionStorage.getItem("purifinity-authentication");
        }
        if (data && data !== "undefined") {
            var authData = JSON.parse(data);
            return new AuthenticationData(authData.authId, authData.authToken);
        }
        return undefined;
    }

    /**
     * This function write authentication data to storage.
     */
    storeAuthData(authData: AuthenticationData) {
        localStorage.setItem("purifinity-authentication", JSON.stringify(authData));
    }

    removeAuthData() {
        localStorage.removeItem("purifinity-authentication");
    }

}
