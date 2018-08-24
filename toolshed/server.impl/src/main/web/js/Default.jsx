import React from 'react';

import Menu from './Menu';
import Footer from './Footer';

export default function Default( {children}) {
    return (
        <div className="container">
            <Header />      
            <Menu />
            {children}
            <Footer />
        </div>
    );
}
