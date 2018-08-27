import React from 'react';

export default class GooglePlusFollowButton extends React.Component {
    
    componentDidMount() {
        if (typeof gapi !== 'undefined' && gapi != null) {
            gapi.follow.go();
          }
    }
    
    render() {
        return (
                <div className="g-follow" data-annotation="bubble" data-height="20" data-href="//plus.google.com/u/0/109089016580730153277" data-rel="publisher"></div>
        );
    }
}
