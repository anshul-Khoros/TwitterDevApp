package com;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TweetUtility {

    Twitter twitter;

    TweetUtility(){
        String consumerKey, consumerSecret, accessToken, accessTokenSecret;
        consumerKey = System.getProperty("ConsumerKey");
        consumerSecret = System.getProperty("ConsumerSecret");
        accessToken = System.getProperty("AccessToken");
        accessTokenSecret = System.getProperty("AccessTokenSecret");


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();

    }



    private Twitter getTwitterInstance(){
        String consumerKey, consumerSecret, accessToken, accessTokenSecret;
        consumerKey = System.getProperty("ConsumerKey");
        consumerSecret = System.getProperty("ConsumerSecret");
        accessToken = System.getProperty("AccessToken");
        accessTokenSecret = System.getProperty("AccessTokenSecret");


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();

        return this.twitter;
    }

    void showTimeline() throws TwitterException {
        List<Status> statuses = this.twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
    }

    void postTweet(String tweet) throws TwitterException {
        this.twitter.updateStatus(tweet);
    }
}
