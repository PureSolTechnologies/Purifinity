declare var PurifinityConfiguration: PurifinityConfiguration.Configuration;

declare module "PurifinityConfiguration" {
    export =  PurifinityConfiguration;
}

/**
 * The PurifinityConfiguration modules contains the client site configuration description in form of interfaces.
 */
declare module PurifinityConfiguration {

    /**
     * This interface is the global interface for the client site configuration.
     */
    export interface Configuration {
        /**
        * Contains the server configuration for the client.
        */
        server: Server;

        /**
        * Contains the authentication settings.
        */
        authentication: Authentication;
    }

    export interface Server {
        /**
         * Contains the host name where the Purifinity server can be found.
         */
        host: string;
        /**
         * Contains the port number of the REST service where information for the client can be retrieved.
         */
        port : number;
    }

    export interface Authentication {
        /**
         * This variable contains the default domain of the email address when it is not provided during login.
         * This functionality was added to not have to put the domain into the login box.
         */
        defaultDomain: string;
    }

}
