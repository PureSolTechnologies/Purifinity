import React from 'react';
import { Link } from 'react-router-dom';
import { SettingsIcon, OrganizationIcon, CalendarIcon, HomeIcon, PlugIcon } from 'react-octicons';

import LoginControl from './components/LoginControl';
import PureSolTechnologies from './components/PureSolTechnologies';

import LoginPage from './pages/LoginPage';

export default function Menu() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-faded">
            <Link className="navbar-brand" to="/">Famility TEST</Link>
            <button className="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/home"><HomeIcon /> Dashboard</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/contacts"><OrganizationIcon /> Contacts</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/calendar"><CalendarIcon /> Calendar</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/finance">$$$ Finance</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/plugins"><PlugIcon /> Plugins</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/admin"><SettingsIcon /> Settings</Link>
                    </li>
                </ul>
                <div className="form-inline my-2 my-lg-0">
                    <LoginControl />
                </div>
            </div>
        </nav>

    );
}
