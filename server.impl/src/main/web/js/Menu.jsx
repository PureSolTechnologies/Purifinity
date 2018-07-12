import React from 'react';
import { Link } from 'react-router-dom';
import { SettingsIcon, OrganizationIcon, CalendarIcon, HomeIcon, PlugIcon } from 'react-octicons';

import PureSolTechnologies from './components/PureSolTechnologies';


export default function Menu() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-faded">
            <Link className="navbar-brand" to="/">ToolShed WebUI</Link>
            <button className="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/home"><HomeIcon /> Home</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/nodes">Nodes</Link>
                    </li>
                </ul>
            </div>
        </nav>

    );
}
