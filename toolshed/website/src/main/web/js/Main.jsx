import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Menu from './Menu';

import Home from './pages/Home';
import About from './pages/About';
import Download from './pages/Download';
import Documentation from './pages/Documentation';
import Contribute from './pages/Contribute';
import Copyright from './pages/Copyright';
import Imprint from './pages/Imprint';

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
                    <Route path="/about" component={About} />
                    <Route path="/download" component={Download} />
                    <Route path="/documentation" component={Documentation} />
                    <Route path="/contribute" component={Contribute} />
                    <Route path="/copyright" component={Copyright} />
                    <Route path="/imprint" component={Imprint} />
                </Switch>
            </div>
        );
    }
}
