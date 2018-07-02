import { createBrowserHistory } from 'history';

import store from '../flux/Store';

import ServerConfiguration from '../config/ServerConfiguration';

export class RESTController {

    baseURL;

    server;

    login = null;

    constructor() {
        this.server = new ServerConfiguration( serverConfiguration.host, serverConfiguration.port );
        this.baseURL = "http://" + this.server.host + ":" + this.server.port + "/rest";
        store.subscribe(() => this.update() );
    }

    update() {
        const loginState = store.getState().login;
        if ( ( !this.login ) || ( this.login.name != loginState.name ) ) {
            this.login = loginState;
        }
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
        if ( this.login ) {
            client.setRequestHeader( "auth-id", this.login.authId );
            client.setRequestHeader( "auth-token", this.login.authToken );
        }
        client.onreadystatechange = function() {
            if ( this.readyState == this.DONE ) {
                let headers = client.getAllResponseHeaders();
                let status = client.status;
                if ( ( status >= 200 ) && ( status < 300 ) ) {
                    successCallback( client );
                } else {
                    if ( status === 401 ) {
                        let pathname = window.location.pathname;
                        if ( !pathname.startsWith( "/login" ) ) {
                            let browserHistory = createBrowserHistory();
                            browserHistory.push( '/login?r=' + encodeURI( window.location.pathname ) );
                        }
                    } else {
                        errorCallback( client );
                    }
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

}

const restController = new RESTController();
export default restController;