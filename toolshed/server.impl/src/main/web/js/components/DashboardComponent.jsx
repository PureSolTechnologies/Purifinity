import * as React from 'react';
import { findDOMNode } from 'react-dom';
import { PlusIcon } from 'react-octicons';

import DashboardTile from './DashboardTile';
import Icon from './Icon';

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
        tiles.push( <DashboardTile id={this.tileKey} dashboard={this} key={this.tileKey} /> );
        this.tileKey++;
        this.setState( { tiles: tiles } );
    }

    remove( tileId ) {
        let tiles = [];
        this.state.tiles.map( element => {
            if ( element.props.id !== tileId ) {
                tiles.push( element );
            }
        } );
        this.setState( { tiles: tiles } );
    }

    render() {
        let tiles = this.state.tiles;
        return (
            [
                <div key={0} className="row">
                    <div className="col-md-12 border">
                        <button type="button" className="btn btn-outline-secondary" onClick={this.addTile}> <Icon name="add" /> Add new tile</button>
                        <button type="button" className="btn btn-outline-primary">Save</button>
                    </div>
                </div>,
                <div key={1} className="row">
                    {tiles}
                </div>
            ]
        );
    }
}
