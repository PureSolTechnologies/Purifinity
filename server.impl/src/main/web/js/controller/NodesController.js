import restController from './RESTController';

export default class NodesController {

    static getNodes( successfulCallback, errorCallback ) {
        restController.GET( '/nodes',
            null,
            function( response ) {
                var nodes = JSON.parse( response.response );
                successfulCallback( nodes );
            },
            errorCallback
        );
    }

    static getNode( nodeName, successfulCallback, errorCallback ) {
        restController.GET( '/nodes/' + nodeName,
            null,
            function( response ) {
                var node = JSON.parse( response.response );
                successfulCallback( node );
            },
            errorCallback
        );
    }

}
