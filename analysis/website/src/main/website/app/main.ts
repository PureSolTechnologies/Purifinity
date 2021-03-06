///<reference path="../node_modules/angular2/typings/browser.d.ts"/>

import {provide} from 'angular2/core';
import {bootstrap}    from 'angular2/platform/browser'
import {AppComponent} from './app.component'
import {SiteConstants} from './site-constants'
import {
    ROUTER_PROVIDERS,
    PathLocationStrategy ,
    LocationStrategy
} from 'angular2/router';

bootstrap(AppComponent, [
    ROUTER_PROVIDERS,
    provide(LocationStrategy, {useClass: PathLocationStrategy}),
    provide(SiteConstants, {useClass: SiteConstants})
]);
