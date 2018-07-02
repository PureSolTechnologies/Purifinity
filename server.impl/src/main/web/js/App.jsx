import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Footer from './Footer';
import Header from './Header';

import Main from './Main';
import Calendar from './Calendar';
import Dialog from './Dialog';
import Admin from './Admin';

const App = () => (
    <div className="container">
        <Header />
        <Switch>
            <Redirect exact from="/calendar" to="/calendar/year" />
            <Route path='/calendar' component={Calendar} />

            <Redirect exact from='/dialog' to="/" />
            <Route path='/dialog' component={Dialog} />

            <Redirect exact from='/admin' to="/admin/accounts" />
            <Route path='/admin' component={Admin} />

            <Redirect exact from='/' to="/home" />
            <Route path="/" component={Main} />
        </Switch>
        <Footer />
    </div>
)

export default App;
