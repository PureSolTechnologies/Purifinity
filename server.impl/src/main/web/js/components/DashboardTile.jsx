import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';
import PropTypes from 'prop-types';

import ChartView from './charts/ChartView';
import TimeSeriesPlot from './charts/plots/TimeSeriesPlot';
import TimeSeriesData from './charts/plots/TimeSeriesData';
import Icon from './Icon';

export default class DashboardTile extends React.Component {

    static propTypes = {
        dashboard: PropTypes.object.isRequired,
        id: PropTypes.number.isRequired
    };

    plot;

    constructor( props ) {
        super( props );
        this.state = {
            name: "",
            width: 4
        }
        this.makeSmaller = this.makeSmaller.bind( this );
        this.makeWider = this.makeWider.bind( this );
        this.remove = this.remove.bind( this );
        let data = new TimeSeriesData();
        data.xAxisTitle = "Time";
        data.yAxisTitle = "Memory Usage (%)";
        this.plot = new TimeSeriesPlot( data );
    }

    componentDidMount() {
        this.setState( { name: "name" } );
    }

    makeSmaller() {
        if ( this.state.width > 2 ) {
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
                        <ChartView plot={this.plot} />
                    </div>
                </div>
            </div>
        );
    }
}
