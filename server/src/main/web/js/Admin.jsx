import React from 'react';

import AdminMenu from './AdminMenu';
import Footer from './Footer';

export default function Admin( { children } ) {
    return (
        <div className="container">
            <AdminMenu />
            {children}
        </div>
    );
}
