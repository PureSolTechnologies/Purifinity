import restController from './RESTController';

export default class NodesController {

    static getNodes( successfulCallback, errorCallback ) {
        restController.GET( '/nodes',
            null,
            function( response ) {
                var types = JSON.parse( response.response );
                successfulCallback( types );
            },
            errorCallback
        );
    }

}
