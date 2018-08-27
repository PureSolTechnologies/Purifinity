import React from 'react';

export default class GooglePlusAddOneButton extends React.Component {
    
    componentDidMount() {
        if (typeof gapi !== 'undefined' && gapi != null) {
            gapi.plusone.go();
          }
    }
    
    render() {
        return (<div className="g-plusone"></div>);
    }
}
