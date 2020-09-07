import React from 'react';
import Tweets from './Tweets.jsx';
import { getTimeline } from '../apis/apiRequest'
import PostTweets from './PostTweets.jsx';



class ShowTimeline extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tweets: [],
            userTweets: [],
            selectedTab: 'post_tweets',
            filterText: '',
            filterClicked: false
        }
    }

    componentDidMount() {
//         this.fetchTweets();
    }

    fetchTweets() {
        let { filterClicked, filterText } = this.state;
        let url = 'http://localhost:8080/api/1.0/twitter/timeline';
        if (filterText) {
            url = 'http://localhost:8080/api/1.0/twitter/timeline/filter/' + filterText;
        }

        // axios api call to fetch timline
        getTimeline(url)
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
            case 'post_tweets':
                break;
            default:
                return null;
        }
        this.setState({
            selectedTab
        });
    }

    getTabs() {
        const tabs = [{ label: "Home", value: 'home' }, { label: "User Timeline", value: 'user_timeline' }, {label:'Post Tweet', value:'post_tweets'}];
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
            case 'post_tweets':
                return <PostTweets />
                break;
            default:
                return null;
        }
    }

    handleInputChange(filterText) { this.setState({ filterText }) }

    handleSubmit(e) {
        e.preventDefault();
        let { selectedTab } = this.state;
        switch (selectedTab) {
            case 'home':
                this.fetchTweets();
                break;
            case 'user_timeline':
                break;
            default:
                return null;
        }

    }

    getFilterForm(){
            let { filterText, selectedTab } = this.state;
            if( selectedTab === 'post_tweets') return <div />;

        return(
        <form onSubmit={(e) => this.handleSubmit(e)}>
                            <input className="input" type="text" placeholder="Filter tweets" value={filterText}
                                onChange={(e) => this.handleInputChange(e.target.value)}  />
                            <input className="submit" type="submit" value="Apply Filter" />
                        </form>
        )}

    render() {

        return (
            <div >
                <div className="timeline-actions">
                    {this.getFilterForm()}
                <div className="tab-container">
                    {this.getTabs()}
                </div>

               </div>
               <div className="tab-content">
                                   {this.getTabsContent()}
                               </div>

            </div>
        )
    }
}

export default ShowTimeline;