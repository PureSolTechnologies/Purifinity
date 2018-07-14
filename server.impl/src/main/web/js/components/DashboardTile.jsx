import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';
import PropTypes from 'prop-types';

import Icon from './Icon';

export default class DashboardTile extends React.Component {

    static propTypes = {
        dashboard: PropTypes.object.isRequired,
        id: PropTypes.number.isRequired
    };

    constructor( props ) {
        super( props );
        this.state = {
            name: "",
            width: 4
        }
        this.makeSmaller = this.makeSmaller.bind( this );
        this.makeWider = this.makeWider.bind( this );
        this.remove = this.remove.bind( this );
    }

    componentDidMount() {
        this.setState( { name: "name" } );
        this.updateCanvas();
    }

    updateCanvas() {
        const ctx = this.refs.canvas.getContext( '2d' );
        ctx.fillRect( 0, 0, 100, 100 );
    }

    makeSmaller() {
        if ( this.state.width > 1 ) {
            this.setState( { width: this.state.width - 1 } );
        }
    }

    makeWider() {
        if ( this.state.width < 12 ) {
            this.setState( { width: this.state.width + 1 } );
        }
    }

    remove() {
        this.props.dashboard.remove( this.props.id );
    }

    render() {
        return (
            <div className={'col-md-' + this.state.width + ' border'}>
                <div className="row">
                    <div className="col-md-12">
                        <button type="button" className="btn btn-outline-secondary" onClick={this.makeSmaller}><Icon name="delete" /></button>
                        <button type="button" className="btn btn-outline-secondary" onClick={this.makeWider}><Icon name="plus_circle_frame" /></button>
                        <button type="button" className="btn btn-outline-secondary" onClick={this.remove}><Icon name="bin_closed" /></button>
                    </div>
                    <div className="col-md-12">
                        <canvas ref="canvas" width={1024} heigth={768}>Canvas not supported by browser.</canvas>
                    </div>
                </div>
            </div>
        );
    }
}
