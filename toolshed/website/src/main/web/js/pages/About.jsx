import React from 'react';

export default class About extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-12">
                    <h1>About</h1>
                    ToolShed is part of Purifinity and provides a collection of agents, metrics servers and tools to evaluate software products and servers...
                    </div>
            </div>
        );
    }
}