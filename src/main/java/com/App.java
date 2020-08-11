package com;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import resources.TweetUtility;
import twitter4j.TwitterException;


public class App extends Service<Configuration> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.addResource(new TweetUtility());
    }
}
