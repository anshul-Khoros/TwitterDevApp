import React from 'react';
import Tweets from './Tweets.jsx';



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
        fetch(url)
        .then(res=> res.json())
        .then((res)=>{
           this.setState({
                tweets:res
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