class AuthenticationService {

    public authData: AuthenticationData;
    public redirect: string;
    private loginURL: string;
    private logoutURL: string;

    constructor(private $location: angular.ILocationService,
        private httpRequests: HTTPRequests,
        private baseURL: string) {
        this.authData = this.loadAuthData();
        this.redirect = "/";
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
            function(data, status) {
                authService.authData = data;
                authService.authData.authId = email;
                authenticated = true;
                authService.storeAuthData(authService.authData);
                if (authService.redirect) {
                    authService.$location.path(authService.redirect);
                } else {
                    authService.$location.path("/");
                }
            }, //
            function(data, status, error) {
                authService.authData = undefined;
                authenticated = false;
                authService.removeAuthData();
                // Error handling
            }
            );
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
            function(data, status) {
            },
            function(data, status, error) {
                // Error handling
            }
            );
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
