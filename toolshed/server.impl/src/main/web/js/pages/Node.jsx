import React from 'react';
import { Link } from 'react-router-dom';

import { convertToSizeString } from '../utils/SizeUtils';
import NodesController from '../controller/NodesController';

export default class Nodes extends React.Component {

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
                <h1>{this.props.match.params.nodeName}</h1>
                <div className="table-responsive">
                    <table className="table table-striped table-bordered table-hover table-sm">
                        <caption>List of all monitored nodes.</caption>
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">Attribute</th>
                                <th scope="col">Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Name</th>
                                <td>{this.state.node.name}</td>
                            </tr>
                            <tr>
                                <th scope="row"><Link to={"/nodes/" + this.state.node.name + "/processes"}>OS</Link></th>
                                <td>{this.state.node.os} {this.state.node.osversion}</td>
                            </tr>
                            <tr>
                                <th scope="row">Architecture</th>
                                <td>{this.state.node.architecture}</td>
                            </tr>
                            <tr>
                                <th scope="row"><Link to={"/nodes/" + this.state.node.name + "/performance"}>CPUs</Link></th>
                                <td>{this.state.node.cpus}</td>
                            </tr>
                            <tr>
                                <th scope="row"><Link to={"/nodes/" + this.state.node.name + "/memory"}>MemTotal</Link></th>
                                <td>{convertToSizeString( this.state.node.memTotal )}</td>
                            </tr>
                            <tr>
                                <th scope="row"><Link to={"/nodes/" + this.state.node.name + "/memory"}>SwapTotal</Link></th>
                                <td>{convertToSizeString( this.state.node.swapTotal )}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}
