import { createBrowserHistory } from 'history';

import ServerConfiguration from '../config/ServerConfiguration';

export class RESTController {

    baseURL;
    server;

    constructor() {
        this.server = new ServerConfiguration( serverConfiguration.host, serverConfiguration.port );
        this.baseURL = "http://" + this.server.host + ":" + this.server.port + "/rest";
    }

    createRequest( type, path, headers, successCallback, errorCallback ) {
        const client = new XMLHttpRequest();
        let url = this.baseURL + path;
        client.open( type, url );
        for ( var key in headers ) {
            if ( headers.hasOwnProperty( key ) ) {
                client.setRequestHeader( key, headers[key] );
            }
        }
        client.onreadystatechange = function() {
            if ( this.readyState == this.DONE ) {
                let headers = client.getAllResponseHeaders();
                let status = client.status;
                if ( ( status >= 200 ) && ( status < 300 ) ) {
                    successCallback( client );
                } else {
                    errorCallback( client );
                }
            }
        };
        return client;
    }

    GET( path, headers, successCallback, errorCallback ) {
        var request = this.createRequest( 'GET', path, headers, successCallback, errorCallback );
        request.send();
    }

    PUT( path, headers, entity, successCallback, errorCallback ) {
        var request = this.createRequest( 'PUT', path, headers, successCallback, errorCallback );
        request.send( JSON.stringify( entity ) );
    }

    POST( path, headers, entity, successCallback, errorCallback ) {
        var request = this.createRequest( 'POST', path, headers, successCallback, errorCallback );
        request.send( JSON.stringify( entity ) );
    }

    DELETE( path, headers, successCallback, errorCallback ) {
        var request = this.createRequest( 'DELETE', path, headers, successCallback, errorCallback );
        request.send();
    }

    HEAD( path, headers, successCallback, errorCallback ) {
        var request = this.createRequest( 'HEAD', path, headers, successCallback, errorCallback );
        request.send();
    }

    OPTIONS( path, headers, successCallback, errorCallback ) {
        var request = this.createRequest( 'OPTIONS', path, headers, successCallback, errorCallback );
        request.send();
    }

}

const restController = new RESTController();
export default restController;