package com;

import twitter4j.TwitterException;


public class App {
    public static void main(String[] args) throws TwitterException {
        TweetUtility tUtility = new TweetUtility();
        tUtility.postTweet("Hello new");
        tUtility.showTimeline();
    }
}
