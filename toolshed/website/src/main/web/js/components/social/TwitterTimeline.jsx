import React from 'react';

export default class TweetButton extends React.Component {

    constructor() {
        super();
    }

    componentDidMount() {
        var embedCode = "!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document,'script','twitter-wjs');";
        $( '.twitter-timeline' ).append( embedCode );
        if ( twttr.widgets ) {
            window.twttr.widgets.load( document.getElementById( "container" ) );
        }
    }

    render() {
        return (
            <div id="twitter-timeline">
                <a className="twitter-timeline" href="https://twitter.com/puresoltech" data-widget-id="696031662912700417">Tweets by @puresoltech</a>
            </div> );
    }
}
