class HTTPRequests {

    constructor(
        private $http: angular.IHttpService,
        private $location: angular.ILocationService,
        private $window: angular.IWindowService,
        private alerter: Alerter) {
    }

    GET(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "GET",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken
                }
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            }
            )
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    GET_TEXT(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "GET",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken,
                },
                transformResponse: function(value) {
                    return value;
                }
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            }
            )
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    POST(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "POST",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken
                },
                data: data
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            })
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    PUT(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "PUT",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken
                },
                data: data
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            }
            )
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    PUT_TEXT(url: string,
        data: any,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "PUT",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken,
                    "Content-Type": "text/plain"
                },
                data: data
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            }
            )
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    DELETE(url: string,
        authId: string,
        authToken: string,
        successCallback: (data: any, status: number) => void,
        errorCallback: (data: any, status: number, error: string) => void) {
        var alerter: Alerter = this.alerter;
        var $location: angular.ILocationService = this.$location;
        var $requests = this;
        return this.$http(
            {
                method: "DELETE",
                url: url,
                headers: {
                    "auth-id": authId,
                    "auth-token": authToken
                }
            }
            )
            .success(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                successCallback(data, status);
            }
            )
            .error(
            function(data: any,
                status: number,
                headers: angular.IHttpHeadersGetter,
                config: angular.IRequestConfig) {
                if (status === 401) {
                    $requests.handle401(data);
                    return;
                }
                alerter.addAlert("error", "HTTP Status: " + status + "\n" + data);
                errorCallback(data, status, "");
            }
            );
    }

    handle401(data: any) {
        this.alerter.addAlert("info", data);
        window.location.href = "/#/login";
        sessionStorage.setItem("redirect.after.login", this.$location.absUrl());
    }
}
