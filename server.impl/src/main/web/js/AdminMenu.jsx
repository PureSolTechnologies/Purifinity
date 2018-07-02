import React from 'react';
import { Link } from 'react-router-dom';
import { HomeIcon, OrganizationIcon, GearIcon } from 'react-octicons';

import LoginControl from './components/LoginControl';
import PureSolTechnologies from './components/PureSolTechnologies';

export default function Menu() {
    return (
        <nav className="navbar navbar-toggleable-md navbar-light bg-faded">
            <button className="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <Link className="navbar-brand" to="/admin">Famility Administration</Link>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <Link className="nav-link" to="/home"><HomeIcon /> Dashboard</Link>
                    </li>
                    <li className="nav-item"><Link className="nav-link" to="/admin/accounts"><OrganizationIcon /> Accounts</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/admin/system"><GearIcon /> System</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/admin/metrics"><GearIcon /> Metrics</Link></li>
                </ul>
                <div className="form-inline my-2 my-lg-0">
                    <LoginControl />
                </div>
            </div>
        </nav>
    );
}
