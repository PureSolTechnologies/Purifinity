import React from 'react';

export default class GooglePlusShareButton extends React.Component {
    
    componentDidMount() {
        if (typeof gapi !== 'undefined' && gapi != null) {
            gapi.plus.go();
          }
    }

    render() {
        return (<div className="g-plus" data-action="share" data-annotation="bubble"></div>);
    }
}