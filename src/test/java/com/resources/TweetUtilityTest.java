package com.resources;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.TweetUtility;
import twitter4j.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TweetUtilityTest {

    @Mock
    ResponseList<Status> status;
    @Mock
    Status s;
    @Mock
    Twitter twitter;
    @InjectMocks
    TweetUtility tweetUtility = new TweetUtility(twitter);


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("testTimeline")
    public void testTimeline() throws TwitterException {
        when(twitter.getHomeTimeline()).thenReturn(status);
        assertEquals(200, tweetUtility.showTimeline().getStatus());
        verify(twitter).getHomeTimeline();
    }

    @Test
    @DisplayName("testTimelineWhenTwitterApiDown")
    public void testTimelineWhenTwitterApiDown() throws TwitterException {
        when(twitter.getHomeTimeline()).thenThrow(new TwitterException("Service down"));
        assertEquals(500, tweetUtility.showTimeline().getStatus());
        verify(twitter).getHomeTimeline();
    }

    @Test
    @DisplayName("testPostTweet")
    public void testPostTweet() throws TwitterException {
        when(twitter.updateStatus("Test tweet")).thenReturn(s);
        assertEquals(200, tweetUtility.postTweet("Test tweet").getStatus());
        verify(twitter).updateStatus("Test tweet");
    }

    @Test
    @DisplayName("testPostTweetWhenTwitterApiDown")
    public void testPostTweetWhenTwitterApiDown() throws TwitterException {
        when(twitter.updateStatus("Test tweet")).thenThrow(new TwitterException("Service Down"));
        assertEquals(500, tweetUtility.postTweet("Test tweet").getStatus());
        verify(twitter).updateStatus("Test tweet");
    }

}