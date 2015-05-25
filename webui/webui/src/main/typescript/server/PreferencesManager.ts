class PreferencesManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    setParameter(parameter: ConfigurationParameter,
        value: string,
        success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        switch (parameter.preferencesGroup) {
            case PreferencesGroup.SYSTEM:
                this.setSystemParameter(parameter.propertyKey, value, success, error);
                break;
            case PreferencesGroup.PLUGIN_DEFAULT:
                this.setPluginDefaultParameter(parameter.groupIdentifier, parameter.propertyKey, value, success, error);
                break;
        }
    }

    getParameter(parameter: ConfigurationParameter,
        success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        switch (parameter.preferencesGroup) {
            case PreferencesGroup.SYSTEM:
                return this.getSystemParameter(parameter.propertyKey, success, error);
            case PreferencesGroup.PLUGIN_DEFAULT:
                return this.getPluginDefaultParameter(parameter.groupIdentifier, parameter.propertyKey, success, error);
        }
    }

    getSystemParameters(success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.get(
            '/purifinityserver/rest/preferences/system',
            success,
            error
            );
    }

    getSystemParameter(propertyKey: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.get_text(
            '/purifinityserver/rest/preferences/system/' + propertyKey,
            success,
            error
            );
    }

    setSystemParameter(propertyKey: string, value: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.put_text('/purifinityserver/rest/preferences/system/' + propertyKey,
            String(value), success,
            error);
    }

    getPluginDefaultParameters(pluginId: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.get(
            '/purifinityserver/rest/preferences/plugins/' + pluginId,
            success,
            error
            );
    }

    getPluginDefaultParameter(pluginId: string, propertyKey: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.get_text(
            '/purifinityserver/rest/preferences/plugins/' + pluginId + '/' + propertyKey,
            success,
            error
            );
    }

    setPluginDefaultParameter(pluginId: string, propertyKey: string, value: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void): angular.IHttpPromise<any> {
        return this.purifinityServerConnector.put_text('/purifinityserver/rest/preferences/plugins/' + pluginId + '/' + propertyKey,
            String(value), success,
            error);
    }
}
