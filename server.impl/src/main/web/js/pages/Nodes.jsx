import React from 'react';

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
        NodesController.getNodes(( nodes ) => {
            component.setState( { nodes: nodes } );
        },
            ( respone ) => { }
        );
    }

    render() {
        let tableRows = [];
        this.state.nodes.map(( element ) => tableRows.push(
            <tr>
                <th scope="row">{element.name}</th>
                <td>{element.os} {element.osversion}</td>
                <td>{element.architecture}</td>
                <td>{element.cpus}</td>
            </tr> ) );
        return (
            <div>
                <h1>Nodes</h1>
                <div class="table-responsive">
                    <table className="table table-striped table-bordered table-hover table-sm">
                        <caption>List of all monitored nodes.</caption>
                        <thead className="thead-light">
                            <th scope="col">Name</th>
                            <th scope="col">OS</th>
                            <th scope="col">Architecture</th>
                            <th scope="col">CPUs</th>
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
