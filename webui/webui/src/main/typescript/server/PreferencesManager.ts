class PreferencesManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getSystemParameters(success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get_text(
            '/purifinityserver/rest/preferences/system',
            success,
            error
            );
    }

    getSystemParameter(propertyKey: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get(
            '/purifinityserver/rest/preferences/system/' + propertyKey,
            success,
            error
            );
    }

    setSystemParameter(propertyKey: string, value: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.put_text('/purifinityserver/rest/preferences/system/' + propertyKey,
            String(value), success,
            error);
    }

}
