import React from 'react';

export default class Home extends React.Component {

    constructor( props ) {
        super( props );
    }

    render() {
        return (
            <div>
                <h1>Home</h1>
                <div className="row">
                    <div className="col-md-6">
                        Left
                </div>
                    <div className="col-md-6">
                        Right
                </div>
                </div >
            </div>
        );
    }
}
