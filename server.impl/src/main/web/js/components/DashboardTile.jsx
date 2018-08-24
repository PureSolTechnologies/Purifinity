import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';
import PropTypes from 'prop-types';

import ChartView from './charts/ChartView';
import TimeSeriesPlot from './charts/plots/TimeSeriesPlot';
import TimeSeriesData from './charts/plots/TimeSeriesData';
import XYTuple from './charts/plots/XYTuple';
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
        data.data.push( new XYTuple( 0, 0 ) );
        data.data.push( new XYTuple( 1, 1 ) );
        data.data.push( new XYTuple( 2, -1 ) );
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
                        <div class="btn-group">
                            <button type="button" className="btn btn-sm btn-outline-secondary" onClick={this.makeSmaller}><Icon name="delete" /></button>
                            <button type="button" className="btn btn-sm btn-outline-secondary" onClick={this.makeWider}><Icon name="plus_circle_frame" /></button>
                            <button type="button" className="btn btn-sm btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Monitor Group
                            </button>
                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <button className="dropdown-item" ><Icon name="processor" /> CPU</button>
                                <button className="dropdown-item" ><Icon name="ddr_memory" /> Memory</button>
                                <button className="dropdown-item" ><Icon name="drive" /> Storage</button>
                                <button className="dropdown-item" ><Icon name="application" /> Processes</button>
                            </div>
                            <button type="button" className="btn btn-sm btn-outline-danger" onClick={this.remove}><Icon name="bin_closed" /></button>
                        </div>
                    </div>
                    <div className="col-md-12">
                        <ChartView plot={this.plot} />
                    </div>
                </div>
            </div>
        );
    }
}
