import React from 'react';
import Tweet from './Tweet.jsx';


const Tweets = ({tweets}) => {
    if(!tweets) return null;

    return(
        <div id="tweets">
            {tweets.map((tweet, tweetKey) => <Tweet key={tweetKey} tweetKey={tweetKey} tweet={tweet} /> )}
        </div>
    )
}

export default Tweets;