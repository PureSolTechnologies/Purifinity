import React from 'react';
import { Link } from 'react-router-dom';

import DashboardComponent from '../components/DashboardComponent';
import DashboardsController from '../controller/DashboardsController';

export default class Dashboards extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div>
                <h1>{this.props.match.params.dashboardName} Dashboard</h1>
                <DashboardComponent />
            </div>
        );
    }
}
