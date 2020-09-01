import React from 'react';
import Tweets from './Tweets.jsx';
import { getTimeline } from '../apis/apiRequest'



class ShowTimeline extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tweets: [],
            userTweets: [],
            selectedTab: 'home'
        }
    }

    componentDidMount() {
        this.fetchTweets();
    }

    fetchTweets() {
        const url = 'http://localhost:8080/api/1.0/twitter/timeline';
        // axios api call to fetch timline
        getTimeline()
            .then((res) => {
                this.setState({
                    tweets: res.data
                })
            })
            .catch(e => {
                console.log("error", e);
            })
    }

    handleTab(selectedTab) {
        switch (selectedTab) {
            case 'home':
                this.fetchTweets();
                break;
            case 'user_timeline':
                break;
            default:
                return null;
        }
        this.setState({
            selectedTab
        });
    }

    getTabs() {
        const tabs = [{ label: "Home", value: 'home' }, { label: "User Timeline", value: 'user_timeline' }];
        let { selectedTab } = this.state;

        return (tabs.map((eachTab, tabKey) => {
            return (
                <div
                    key={eachTab.value}
                    className={`tab ${eachTab.value === selectedTab ? 'active-tab' : ''}`}
                    onClick={() => { this.handleTab(eachTab.value) }}
                >
                    {eachTab.label}
                </div>
            )
        })

        )
    }

    getTabsContent() {
        let { selectedTab, userTweets, tweets } = this.state;
        switch (selectedTab) {
            case 'home':
                return (
                    <Tweets
                        tweets={tweets}
                    />
                )
            case 'user_timeline':
                return (
                    <Tweets tweets={userTweets} />
                )
            default:
                return null;
        }
    }

    render() {

        return (
            <div>
                <div className="tab-container">
                    {this.getTabs()}
                </div>
                <div className="tab-content">
                    {this.getTabsContent()}
                </div>


            </div>
        )
    }
}

export default ShowTimeline;