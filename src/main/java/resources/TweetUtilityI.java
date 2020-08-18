package resources;

import services.TwitterUtilityService;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface TweetUtilityI {

    public Response getTimelineWithFilter(String keyword);
    public Response showTimeline() throws TwitterException;
    public Response postTweet(String tweet) throws TwitterException;
    public void setTwitter(Twitter twitter);
}
