import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';

export default class DashboardTile extends React.Component {

    constructor( props ) {
        super( props );
        this.state = {
            name: ""
        }
    }

    componentDidMount() {
        this.setState( { name: "name" } );
    }

    render() {
        return (
            <div className="col-md-4">
                <h2>{this.state.name}</h2>
            </div>
        );
    }
}
