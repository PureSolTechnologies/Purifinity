import {Injectable} from 'angular2/core';

/**
 * This class can be used to inject site wide constants.
 */
@Injectable()
export class SiteConstants {

    getServerHost(): string {
        return PurifinityConfiguration.server.host;
    }

    getServerPort(): number {
        return PurifinityConfiguration.server.port;
    }

    getAuthenticationDefaultDomain(): string {
        return PurifinityConfiguration.authentication.defaultDomain;
    }

}
