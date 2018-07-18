import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';
import PropTypes from 'prop-types';

export default class DashboardTile extends React.Component {

    static propTypes = {
        plot: PropTypes.object.isRequired
    };

    width = 1920;
    height = 1080;

    constructor( props ) {
        super( props );
    }

    componentDidUpdate( prevProps, prevState, prevContext ) {
        this.updateCanvas();
    }

    updateCanvas() {
        const ctx = this.refs.canvas.getContext( '2d' );
        const canvas = ctx.canvas;
        this.width = canvas.clientWidth;
        this.height = canvas.clientHeight;
        canvas.width = this.width;
        canvas.height = this.height;
        this.renderTitle( ctx );
        ctx.rect( 0, 20, this.width, this.height );
        ctx.clip();
        ctx.translate( 0, 20 );
        this.props.plot.render( ctx, 0, 0, this.width, this.height - 20 );
    }

    renderTitle( ctx ) {
        const canvas = ctx.canvas;
        const width = canvas.width;
        const height = canvas.height;
        let fontSize = height * 0.1;
        if ( fontSize > 20 ) {
            fontSize = 20;
        }
        ctx.textAlign = "center";
        ctx.textBaseline = "top";
        ctx.font = "bolder " + fontSize + "px sans-serif"
        ctx.fillText( "Title", width / 2, 0 );
    }

    render() {
        return (
            <canvas ref="canvas" width={this.width} height={this.height} style={{ width: "100%", height: "100%", position: "relative" }}>Canvas not supported by browser.</canvas>
        );
    }
}
