import React from 'react';
import { Link } from 'react-router-dom';

import { convertToSizeString } from '../utils/SizeUtils';
import NodesController from '../controller/NodesController';

export default class Nodes extends React.Component {


    constructor( props ) {
        super( props );
        this.state = {
            nodes: []
        }
    }

    componentDidMount() {
        let component = this;
        NodesController.getNodes(
            ( nodes ) => {
                component.setState( { nodes: nodes } );
            },
            ( respone ) => { }
        );
    }

    render() {
        let tableRows = [];
        this.state.nodes.map(( element ) => tableRows.push(
            <tr key={element.name}>
                <th scope="row"><Link to={"/nodes/" + element.name}>{element.name}</Link></th>
                <td>{element.os} {element.osversion}</td>
                <td>{element.architecture}</td>
                <td>{element.cpus}</td>
                <td>{convertToSizeString( element.memTotal )}</td>
                <td>{convertToSizeString( element.swapTotal )}</td>
            </tr> ) );
        return (
            <div>
                <h1>Nodes</h1>
                <div className="table-responsive">
                    <table className="table table-striped table-bordered table-hover table-sm">
                        <caption>List of all monitored nodes.</caption>
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">OS</th>
                                <th scope="col">Architecture</th>
                                <th scope="col">CPUs</th>
                                <th scope="col">MemTotal</th>
                                <th scope="col">SwapTotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            {tableRows}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}
