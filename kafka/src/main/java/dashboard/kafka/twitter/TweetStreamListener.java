package dashboard.kafka.twitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

public class TweetStreamListener implements StreamListener {

    private final Logger log = LoggerFactory.getLogger(getClass());


    private Producer<String, String> producer;

    private ObjectMapper objectMapper;

    public TweetStreamListener(Producer<String, String> producer, ObjectMapper objectMapper) {
        this.producer = producer;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onTweet(Tweet tweet) {

        try {
            String tweetAsJSON = objectMapper.writeValueAsString(tweet);
            producer.send(new KeyedMessage<>("tweets", Long.toString(tweet.getId()), tweetAsJSON));
        } catch (JsonProcessingException e) {
            log.error("error processing tweet: ", e);
        }

    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {
        if (log.isTraceEnabled()) {
            log.trace("onDelete called, but not implemented");
        }
    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {
        if (log.isWarnEnabled()) {
            log.warn("onLimit called, but not implemented");
        }
    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {
        if (log.isWarnEnabled()) {
            log.warn("onWarning called, but not implemented");
        }
    }

}