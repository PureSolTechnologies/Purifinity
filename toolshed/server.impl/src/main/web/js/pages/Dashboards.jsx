import React from 'react';
import { Link } from 'react-router-dom';
import { SettingsIcon, TrashcanIcon } from 'react-octicons';

import DashboardsController from '../controller/DashboardsController';

export default class Dashboard extends React.Component {

    constructor( props ) {
        super( props );
        this.state = {
            dashboards: []
        }
    }

    componentDidMount() {
        let component = this;
        DashboardsController.getDashboards(
            ( dashboards ) => {
                component.setState( { dashboards: dashboards } );
            },
            ( response ) => { }
        );
    }
    render() {
        let tableRows = [];
        this.state.dashboards.map(( element ) => tableRows.push(
            <tr key={element.id}>
                <th scope="row"><Link to={"/dashboards/" + element.id}>{element.name}</Link></th>
                <td>
                    <button type="button" className="btn btn-outline-secondary"> <SettingsIcon /></button>
                    <button type="button" className="btn btn-outline-danger">  <TrashcanIcon /></button>
                </td>
            </tr> ) );
        return (
            <div>
                <h1>Dashboards</h1>
                <div className="table-responsive">
                    <table className="table table-striped table-bordered table-hover table-sm">
                        <caption>List of available dashboards.</caption>
                        <thead className="thead-light">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Actions</th>
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

