import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { DashIcon, PlusIcon } from 'react-octicons';

import DashboardTile from './DashboardTile';

export default class DashboardComponent extends React.Component {

    tileKey = 0;

    constructor( props ) {
        super( props );
        this.state = {
            tiles: []
        };
        this.addTile = this.addTile.bind( this );
    }

    addTile() {
        let tiles = this.state.tiles;
        tiles.push( <DashboardTile key={this.tileKey} /> );
        this.tileKey++;
        this.setState( { tiles: tiles } );
    }

    render() {
        let tiles = this.state.tiles;
        return (
            <div className="row">
                <div className="col-md-12 border">
                    <button type="button" className="btn btn-outline-secondary" onClick={this.addTile}> <PlusIcon /> Add new tile</button>
                    <button type="button" className="btn btn-outline-primary">Save</button>
                </div>
                <div className="col-md-12 border">
                    <div className="row">
                        {tiles}
                    </div>
                </div>
            </div>
        );
    }
}
