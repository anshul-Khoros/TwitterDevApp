package resources;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/api/1.0/twitter")
public class TweetUtility {

    Twitter twitter;


    public TweetUtility(Twitter twitter){
        this.twitter = twitter;
    }

    @GET
    @Path("/timeline")
    @Produces("application/json")
    public Response showTimeline() throws TwitterException {
        try {
            List<Status> statuses = this.twitter.getHomeTimeline();
            return Response.status(Response.Status.OK).entity(statuses).build();
        }
        catch (Exception ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("/tweet/{tweet}")
    public Response postTweet(@PathParam("tweet") String tweet) throws TwitterException {
        if(tweet == "") {
            return Response.serverError().entity("No tweet provided").build();
        }
        try {
            this.twitter.updateStatus(tweet);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }
}
