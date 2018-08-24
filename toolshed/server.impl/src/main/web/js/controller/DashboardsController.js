import restController from './RESTController';

export default class DashboardsController {

    static getDashboards( successfulCallback, errorCallback ) {
        restController.GET( '/dashboards',
            null,
            function( response ) {
                var nodes = JSON.parse( response.response );
                successfulCallback( nodes );
            },
            errorCallback
        );
    }

    static getDashboard( dashboardId, successfulCallback, errorCallback ) {
        restController.GET( '/dashboards/' + dashboardId,
            null,
            function( response ) {
                var node = JSON.parse( response.response );
                successfulCallback( node );
            },
            errorCallback
        );
    }

}
