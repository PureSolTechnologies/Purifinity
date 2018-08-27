import React from 'react';

export default class TweetButton extends React.Component {

    constructor() {
        super();
    }

    componentDidMount() {
        var embedCode = "!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');";
        $( '.twitter-follow-button' ).append( embedCode );
        if ( window.twttr.widgets ) {
            window.twttr.widgets.load( document.getElementById( "container" ) );
        }
    }

    render() {
        return (
            <a href="https://twitter.com/puresoltech" className="twitter-follow-button" data-show-count="false">Follow @puresoltech</a>
        );
    }
}
