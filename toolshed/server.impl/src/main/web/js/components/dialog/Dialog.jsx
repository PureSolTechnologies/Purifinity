import * as React from 'react';
import PropTypes from 'prop-types';

export default class Dialog extends React.Component {


    static propTypes = {
        title: PropTypes.string.isRequired,
    };

    constructor( props ) {
        super( props );
    }


    render() {
        return <div className="card">
            <h1 className="card-header">{this.props.title}</h1>
            <div className="card-block">
                {this.props.children}
            </div>
        </div>;
    }

}