                       import React from 'react';
                       import { postTweet } from '../apis/apiRequest'

                       const maxCharLimit = 280;
                       class PostTweets extends React.Component {
                           constructor(props) {
                               super(props);
                               this.state = {
                                   tweetText: '',
                                   errorMsg: ''
                               }
                           }

                           handleChangeText(val) {
                               if (val.length > maxCharLimit) {
                                   this.setState({
                                       errorMsg: 'Tweet can not be more than ' + maxCharLimit + ' characters'
                                   })
                                   return;
                               }
                               this.setState({
                                   tweetText: val,
                                   errorMsg: ''
                               })
                           }

                           handleSubmit(e) {
                               e.preventDefault();
                               let { tweetText } = this.state;
                               if (!tweetText) {
                                   this.setState({ errorMsg: 'Tweet cannot be empty' });
                                   return;
                               }

                               postTweet(tweetText)
                                   .then((res) => {
                       //                 console.log("got res", res);
                                       this.setState({ tweetText: '', successMsg: 'Tweet posted successfully' })
                                   })
                                   .catch(e => {
                                       console.log("error", e);
                                       this.setState({ errorMsg: 'Could not post tweet! please try again later.' })
                                   })
                           }

                           render() {
                               let { tweetText, errorMsg, successMsg } = this.state;
                               return (
                                   <div id="post-tweets">
                                       <form id="post-tweets-form" onSubmit={(e) => this.handleSubmit(e)}>
                                           <textarea className="input-textarea" rows="5" placeholder="Type here..." value={tweetText}
                                               onChange={(e) => this.handleChangeText(e.target.value)} />
                                           {tweetText.length > 0 ? <span> Character count: { tweetText.length} </span> : null}
                                           <span className="error-msg">{errorMsg}</span>
                                           <input className="post-tweet-submit" type="submit" value="Post Tweet" />
                                       </form>
                                       <span className="success-msg">{successMsg}</span>

                                   </div>
                               )
                           }
                       }

                       export default PostTweets;