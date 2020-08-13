package com;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import resources.TweetUtility;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class App extends Service<Configuration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        Twitter twitter = getTwitterInstance();
        environment.addResource(new TweetUtility(twitter));
    }

    public Twitter getTwitterInstance(){
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
        return tf.getInstance();

    }
}
