///<reference path="../node_modules/angular2/typings/browser.d.ts"/>

import {provide} from 'angular2/core';
import {bootstrap}    from 'angular2/platform/browser';
import {
    ROUTER_PROVIDERS,
    HashLocationStrategy,
    LocationStrategy
} from 'angular2/router';
import {HTTP_PROVIDERS} from 'angular2/http';
import {AppComponent} from './app.component';
import {SiteConstants} from './site-constants';
import {PurifinityServerConnector} from './commons/purifinity/PurifinityServerConnector';
import {AuthenticationService} from './commons/auth/AuthenticationService';
import {HTTPRequests} from './commons/purifinity/HTTPRequests';
import {ProjectManager} from './commons/purifinity/ProjectManager';
import {PluginManager} from './commons/purifinity/PluginManager';
import {PreferencesManager} from './commons/purifinity/PreferencesManager';
import {AccountManager} from './commons/purifinity/AccountManager';
import {FileStore} from './commons/purifinity/FileStore';
import {EvaluatorStore} from './commons/purifinity/EvaluatorStore';
import {Alerter} from './commons/alerter/Alerter';

/**
 * Bootstrapping of Purifinity's central application.
 */
bootstrap(AppComponent, [
    ROUTER_PROVIDERS, HTTP_PROVIDERS,
    provide(LocationStrategy, { useClass: HashLocationStrategy }),
    SiteConstants, AuthenticationService,
    PurifinityServerConnector, ProjectManager, PluginManager, PreferencesManager,
    AccountManager, EvaluatorStore, HTTPRequests, FileStore, Alerter
]);
