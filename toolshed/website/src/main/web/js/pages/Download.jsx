import React from 'react';

export default class About extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-12">
                    <h1>Download</h1>
                    <p>The latest version is ToolShed 0.1.0-SNAPSHOT.</p>
                    <p>Available distributions:</p>
                    <ul>
                        <li><a href="downloads/0.1.0-SNAPSHOT/toolshed-0.1.0-SNAPSHOT.zip">Distribution Full</a></li>
                    </ul>
                    <p>
                        The source code can be found on <a href="https://github.com/PureSolTechnologies/Purifinity">Github</a>.
                        </p>
                </div>
            </div>
        );
    }
}
