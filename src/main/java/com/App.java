package com;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import resources.TweetUtilityI;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class App extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

    }


    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {


        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

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
