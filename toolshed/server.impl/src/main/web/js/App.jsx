import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Footer from './Footer';
import Header from './Header';

import Main from './Main';
import Dialog from './Dialog';

const App = () => (
    <div className="container">
        <Switch>
            <Redirect exact from='/dialog' to="/" />
            <Route path='/dialog' component={Dialog} />

            <Redirect exact from='/' to="/home" />
            <Route path="/" component={Main} />
        </Switch>
        <Footer />
    </div>
)

export default App;
