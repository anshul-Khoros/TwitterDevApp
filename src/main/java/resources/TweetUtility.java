package resources;

import io.dropwizard.jersey.caching.CacheControl;
import org.springframework.stereotype.Component;
import services.TwitterUtilityService;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;


@Component
@Path("/api/1.0/twitter")
public class TweetUtility implements TweetUtilityI {

    Twitter twitter;
    TwitterUtilityService twitterService;

    public TweetUtility(){
        this.twitterService = new TwitterUtilityService();
    }

    public void setTwitter(Twitter twitter){
        this.twitter = twitter;
    }

    @GET
    @Path("/timeline/filter/{keyword}")
    @Produces("application/json")
    public Response getTimelineWithFilter(@PathParam("keyword") String keyword){
         return twitterService.getTimelineWithFilterService(keyword, this.twitter);
    }

    @GET
    @Path("/timeline")
    @Produces("application/json")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public Response showTimeline() throws TwitterException {
        return twitterService.getTimelineService(twitter);
    }

    @POST
    @Path("/tweet/{tweet}")
    public Response postTweet(@PathParam("tweet") String tweet) throws TwitterException {
        return twitterService.postTweetService(tweet, twitter);
    }
}

