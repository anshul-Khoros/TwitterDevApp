package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.TweetUtility;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import java.util.List;

public class TwitterUtilityService {

    Logger logger = LoggerFactory.getLogger(TweetUtility.class);


    public Response getTimelineService(Twitter twitter) throws TwitterException {
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            logger.info("Fetched timeline form twitter");
            return Response.status(Response.Status.OK).entity(statuses).build();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }

    public Response postTweetService(String tweet, Twitter twitter) throws TwitterException {
        if(tweet == "") {
            logger.error("Tweet is empty string");
            return Response.serverError().entity("No tweet provided").build();
        }
        try {
            twitter.updateStatus(tweet);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }
}
