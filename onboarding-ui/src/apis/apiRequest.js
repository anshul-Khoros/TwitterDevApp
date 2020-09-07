import axios from 'axios';

const getTimeline = (url) => {
    let getTimelineUrl = 'http://localhost:8080/api/1.0/twitter/timeline';
    if(url){
        getTimelineUrl = url;
    }
    return axios(getTimelineUrl)
}


const postTweet = (tweet) => {
    let postTweetUrl = 'http://localhost:8080/api/1.0/twitter/tweet/' + tweet;
    return axios.post(postTweetUrl, {});
}

export { getTimeline, postTweet }