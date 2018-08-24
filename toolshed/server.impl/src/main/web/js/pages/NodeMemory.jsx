import React from 'react';
import { Link } from 'react-router-dom';

import { convertToSizeString } from '../utils/SizeUtils';
import NodesController from '../controller/NodesController';

export default class NodeMemory extends React.Component {

    constructor( props ) {
        super( props );
        this.state = {
            node: {}
        }
    }

    componentDidMount() {
        let component = this;
        NodesController.getNode( this.props.match.params.nodeName,
            ( node ) => {
                component.setState( { node: node } );
            },
            ( respone ) => { }
        );
    }

    render() {
        return (
            <div>
                <h1>{this.props.match.params.nodeName} Memory</h1>
            </div>
        );
    }
}
