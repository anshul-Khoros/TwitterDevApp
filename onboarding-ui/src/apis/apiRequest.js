import axios from 'axios';

const getTimeline = () => {
    const getTimelineUrl = 'http://localhost:8080/api/1.0/twitter/timeline';
    return axios(getTimelineUrl)
}


export { getTimeline }