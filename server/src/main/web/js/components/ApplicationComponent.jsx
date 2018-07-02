import * as React from 'react';
import { findDOMNode } from 'react-dom';

export default class ApplicationComponent extends React.Component {

    rootReference = '';

    constructor( props ) {
        super( props );
    }

    enableTooltips( componentRootReference ) {
        this.rootReference = componentRootReference;
    }

    /**
     * This method is called with a reference id to search for tooltip elements to enable them.
     */
    enableTooltip( referenceId ) {
        let component = findDOMNode( this.refs[referenceId] );
        $( component ).find( '[data-toggle="tooltip"]' ).tooltip();
    }

    /**
     * This method is called with a reference id to search for tooltip elements to enable them.
     */
    hideTooltip( referenceId ) {
        let component = findDOMNode( this.refs[referenceId] );
        $( component ).find( '[data-toggle="tooltip"]' ).tooltip( 'dispose' );
    }

    componentDidUpdate() {
        this.enableTooltip( this.rootReference );
    }

    componentWillUnmount() {
        this.hideTooltip( this.rootReference );
    }

}
