import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Menu from './Menu';

import MainDashboard from './pages/MainDashboard';
import Plugins from './pages/Plugins';
import Copyright from './pages/Copyright';
import LoginPage from './pages/LoginPage';
import Contacts from './pages/Contacts';

export default class Calendar extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div>
                <Menu />
                <Switch>
                    <Route path="/home" component={MainDashboard} />
                    <Route path="/plugins" component={Plugins} />
                    <Route path="/copyright" component={Copyright} />
                    <Route path="/login" component={LoginPage} />
                    <Route path="/contacts" component={Contacts} />
                </Switch>
            </div>
        );
    }
}
