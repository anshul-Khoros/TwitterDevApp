package com;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import resources.TweetUtility;
import resources.TweetUtilityI;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class App extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

    }


    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Twitter twitter = getTwitterInstance(configuration);
        TweetUtilityI tweetUtility = (TweetUtilityI) applicationContext.getBean("tweetUtility");
        tweetUtility.setTwitter(twitter);
        environment.jersey().register(tweetUtility);
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
