import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Menu from './Menu';

import Home from './pages/Home';
import Nodes from './pages/Nodes';
import Node from './pages/Node';
import NodeMemory from './pages/NodeMemory';
import NodePerformance from './pages/NodePerformance';
import NodeProcesses from './pages/NodeProcesses';

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
                <Route exact path="/nodes" component={Nodes} />
                <Route exact path="/nodes/:nodeName" component={Node} />
                <Route path="/nodes/:nodeName/memory" component={NodeMemory} />
                <Route path="/nodes/:nodeName/performance" component={NodePerformance} />
                <Route path="/nodes/:nodeName/processes" component={NodeProcesses} />
                </Switch>
            </div>
        );
    }
}
