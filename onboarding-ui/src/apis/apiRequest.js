import axios from 'axios';

const getTimeline = (url) => {
    let getTimelineUrl = 'http://localhost:8080/api/1.0/twitter/timeline';
    if(url){
        getTimelineUrl = url;
    }
    return axios(getTimelineUrl)
}


export { getTimeline }