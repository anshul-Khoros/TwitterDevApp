import React from 'react';
import Tweets from './Tweets.jsx';
import { getTimeline } from '../apis/apiRequest'



class ShowTimeline extends React.Component{
    constructor(props){
        super(props);
        this.state={
            tweets:[]
        }
    }

    componentDidMount(){
        this.fetchTweets();
    }

    fetchTweets(){
        const url = 'http://localhost:8080/api/1.0/twitter/timeline';
        // axios api call to fetch tweets
        getTimeline()
        .then((res)=>{
           this.setState({
                tweets:res.data
           })
        })
        .catch(e=>{
            console.log("error", e);
        })
    }

    render(){
        return(
            <div>
               <Tweets
                tweets={this.state.tweets}
               />
            </div>
        )
    }
}

export default ShowTimeline;