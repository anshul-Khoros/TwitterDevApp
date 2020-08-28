import React from 'react';
import Tweet from './Tweet.jsx';


const Tweets = ({tweets}) => {
    if(!tweets || tweets.length === 0) return (
        <p id="no-tweets">No tweets are available, post a tweet!</p>
    );

    return(
        <div id="tweets">
            {tweets.map((tweet, tweetKey) => <Tweet key={tweetKey} tweetKey={tweetKey} tweet={tweet} /> )}
        </div>
    )
}

export default Tweets;