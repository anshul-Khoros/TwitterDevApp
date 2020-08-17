package com;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import resources.TweetUtility;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App extends Service<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        Twitter twitter = getTwitterInstance(configuration);
        environment.addResource(new TweetUtility(twitter));
    }



    public Twitter getTwitterInstance(AppConfiguration configuration){
        String consumerKey, consumerSecret, accessToken, accessTokenSecret;
        consumerKey = configuration.getConsumerKey();
        consumerSecret = configuration.getConsumerSecret();
        accessToken = configuration.getAccessToken();
        accessTokenSecret = configuration.getAccessTokenSecret();


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
