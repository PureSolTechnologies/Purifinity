import React from 'react';
import { Link } from 'react-router-dom';

import PureSolTechnologies from './components/PureSolTechnologies';

export default function Footer() {
    return (
        <footer>
            <hr />
            <div className="row">
                <div className="col-md-8">
                    <PureSolTechnologies /><br />
                    <h4>Because software quality begins at the source.</h4>
                </div>
                <div className="col-md-4">
                    <Link to="/copyright">Copyright</Link>
                </div>
            </div >

        </footer>
    );
}
