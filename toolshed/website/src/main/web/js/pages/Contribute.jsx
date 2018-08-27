import React from 'react';

export default class About extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-md-12">
                        <h1>Contribute</h1>
                        <p>Any help from any side is appreciated. There are multiple ways
                                to contribute to ToolShed and Purifinity.</p>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <h2>Fork and Develop ToolShed and Purifinity Code</h2>
                        <p>
                            ToolShed is hosted on
                                <ref url="https://github.com/PureSolTechnologies/Purifinity">GitHub</ref>
                            together with Purifinity. Anyone can fork Purifinity there, develop code and create a pull
                                request to get the code merged into official ToolShed and Purifinity.
                        </p>
                        <h2>File Bug Reports or Feature Requests</h2>
                        <p>
                            If bugs and issues are found, bug reports can be filed in the offical
                                <ref url="https://bugs.puresol-technologies.net/projects/tool-shed">ToolShed
                                Bug Tracker</ref>
                            . Ideas for features can be filed there as well.
                        </p>
                    </div>
                    <div className="col-md-6">
                        <h2>Write Documentation</h2>
                        <p>This website is also hosted in GitHub together with the
                                Purifinity source code. Documentation writers can work on the same
                                repository as well.</p>
                    </div>
                </div>


            </div>
        );
    }
}
