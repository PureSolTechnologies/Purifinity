import FacebookFollowButton from 'components/social/FacebookFollowButton';
import FacebookLikeButton from 'components/social/FacebookLikeButton';
import GooglePlusAddOneButton from 'components/social/GooglePlusAddOneButton';
import GooglePlusFollowButton from 'components/social/GooglePlusFollowButton';
import GooglePlusShareButton from 'components/social/GooglePlusShareButton';
import GooglePlusPosts from 'components/social/GooglePlusPosts';

import TweetButton from 'components/social/TweetButton';
import TwitterFollowButton from 'components/social/TwitterFollowButton';
import TwitterTimeline from 'components/social/TwitterTimeline';

export default function SocialBar() {
    return (
            <div>
                <TwitterFollowButton/>
                <TweetButton/><br/>
                <GooglePlusAddOneButton/>
                <GooglePlusFollowButton/>
                <GooglePlusShareButton/><br/>
                <FacebookLikeButton/>
                <FacebookFollowButton/>
            </div>
           );
}
