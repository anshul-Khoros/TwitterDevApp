import React from 'react';

const Tweet = ({tweet, tweetKey}) => {
    if(!tweet) return null;

    let dateOptions = { month: 'short', day: 'numeric' };

    return(
        <div key={tweetKey} className={`tweet ${tweetKey%2 === 0 ? 'even-tweet': 'odd-tweet'}`}>
            <div className="img-container">
                <img className="profile-img" src={tweet.user.profileImageUrl} />
                <span className="user-name">{tweet.user.name}</span>
            </div>

            <div className="msg-container">
                <span className="tweet-date">{new Date(tweet.createdAt).toLocaleDateString('en-US', dateOptions)}</span>
                <a target="_blank" href={tweet.tweetUrl}>{tweet.message}</a>
            </div>
        </div>
    )
}

export default Tweet;