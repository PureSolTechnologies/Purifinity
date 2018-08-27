import React from 'react';
import { Link } from 'react-router-dom';
import { HomeIcon, FileIcon, FileZipIcon, InfoIcon, SmileyIcon } from 'react-octicons';

import PureSolTechnologies from './components/PureSolTechnologies';


export default function Menu() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <Link className="navbar-brand" to="/">ToolShed</Link>
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
                        <Link className="nav-link" to="/about"><InfoIcon /> About</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/download"><FileZipIcon /> Download</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/documentation"><FileIcon /> Documentation</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="/contribute"><SmileyIcon /> Contribute</Link>
                    </li>
                </ul>
            </div>
            <PureSolTechnologies />
        </nav>

    );
}
