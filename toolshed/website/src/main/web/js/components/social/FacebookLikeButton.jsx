import React from 'react';

export default function FacebookLikeButton() {
    return (
            <div className="fb-like" data-href={window.location.href} data-layout="standard" data-action="like" data-show-faces="false" data-share="true">
            </div>
    );
}
