import React from 'react';

import PureSolTechnologies from './components/PureSolTechnologies';

export default function Header() {
    return (
        <header>
            <div className="row">
                <div className="col-sm-6">
                    <h2>
                        <PureSolTechnologies />
                    </h2>
                </div>
                <div className="col-sm-6" style={{ textAlign: 'right' }}>
                    <h3>Because software quality begins at the source.</h3>
                </div>
            </div>
        </header>
    );
}
