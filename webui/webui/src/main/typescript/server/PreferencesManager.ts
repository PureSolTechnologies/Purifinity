class PreferencesManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getSystemParameters(success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get(
            '/purifinityserver/rest/preferences/system',
            success,
            error
            );
    }

}
