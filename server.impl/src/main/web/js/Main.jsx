import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Menu from './Menu';

import Home from './pages/Home';
import Nodes from './pages/Nodes';

export default class Calendar extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div>
                <Menu />
                <Switch>
                    <Route path="/home" component={Home} />
                    <Route path="/nodes" component={Nodes} />
                </Switch>
            </div>
        );
    }
}
