package services;

import models.TwitterPost;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.TweetUtility;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TwitterUtilityService {

    Logger logger = LoggerFactory.getLogger(TweetUtility.class);


    public Response getTimelineService(Twitter twitter) throws TwitterException {
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            List<TwitterPost> twitterPosts = new ArrayList<>();
            logger.info("Fetched timeline form twitter");
            for (Status status : statuses) {
                TwitterPost obj = new TwitterPost();
                User userObj = new User();
                obj.setMessage(status.getText());
                obj.setCreatedAt(status.getCreatedAt());

                userObj.setName(status.getUser().getName());
                userObj.setProfileImageUrl(status.getUser().getProfileImageURL());
                userObj.setTwitterHandle(status.getUser().getId());

                obj.setUser(userObj);
                twitterPosts.add(obj);
            }
            return Response.status(Response.Status.OK).entity(twitterPosts).build();
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

    public Response getTimelineWithFilter(String keyword, Twitter twitter) {

        try {
            List<Status> statuses = twitter.getHomeTimeline();
            List<TwitterPost> twitterPosts = new ArrayList<>();
            logger.info("Fetched timeline form twitter");
            for (Status status : statuses) {
                TwitterPost obj = new TwitterPost();
                User userObj = new User();
                obj.setMessage(status.getText());
                obj.setCreatedAt(status.getCreatedAt());

                userObj.setName(status.getUser().getName());
                userObj.setProfileImageUrl(status.getUser().getProfileImageURL());
                userObj.setTwitterHandle(status.getUser().getId());

                obj.setUser(userObj);
                twitterPosts.add(obj);
            }
            Stream<TwitterPost> twitterPostStream = twitterPosts.stream();
            Stream<TwitterPost> twitterPostStreamWithFilter = twitterPostStream.filter(tweet -> tweet.getMessage().contains(keyword));


            return Response.status(Response.Status.OK).entity(twitterPostStreamWithFilter.toArray()).build();
        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }
}
