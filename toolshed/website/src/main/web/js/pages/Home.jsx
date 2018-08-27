import React from 'react';
import { Link } from 'react-router-dom';

import TwitterTimeline from '../components/social/TwitterTimeline';

export default class Home extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-6">
                    <h1>Introduction</h1>
                    <p>ToolShed is part of Purifinity and provides a collection of tools, agents and metrics servers to evaluate software products and servers.</p>
                    <p>
                        For details have a look to <Link to="/about">About</Link>.
                        </p>
                    <h1>Features</h1>
                    <ul>
                        <li>Metrics servers to evaluate single servers or server clusters</li>
                        <li>Profiler agent for Java</li>
                        <li>Client application to evaluate Java profiles</li>
                    </ul>
                    <h1>Get it</h1>
                    <p>
                        Binary distribution(s) can be found on the <Link to="/download">Download page</Link>.
                        </p>
                    <p>
                        ToolShed is hosted on
                                <a href="https://github.com/PureSolTechnologies/Purifinity" target="purifinity-github">GitHub</a>
                        as part of Purifinity to provide it and to enable it to get worked on. <Link className="nav-link"
                            to="/contribute">Read more...</Link>
                    </p >
                    <h1>License</h1>
                    <p>
                        ToolShed was published under <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache
                                License Version 2.0</a>.
                        </p>
                </div >
                <div className="col-md-6">
                    <h2>News</h2>
                    <TwitterTimeline />
                </div>
            </div >
        );
    }
}
