package com.twitter.service;

import com.twitter.model.Twit;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myuzkollar on 29/10/15.
 */
@Component
public class TwitterServiceImpl implements TwitterService{


    public Integer getPercent(String userName, String userName2, int quantity) {
        Integer count = new Integer(0);
        List<String> tags = getTags(userName, quantity);
        List<String> tags1 = getTags(userName2, quantity);
        for (String tag : tags) {
            if (tags1.contains(tag)) {
                count=count+1;
            }
        }
        return count*10;
    }

    private Twitter getTwitterLogin() {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("7bLMHAS5ffl5eIln7cEFYDIOO", "JvxBOlF5bx514wB74cMyfIQflRHX1RZgWCbrSlNfINO8tUddH7");
        twitter.setOAuthAccessToken(new AccessToken("1095934970-PDWR8DFc6fhed67tzP6nWQMHeWO2R9orw5H3f1l", "4dbJVzIFFMrG4jtV0LP2YRESpea3nmMPIizkBpSdtZIFh"));
        return twitter;
    }

    public Twit getTag(String twit) {
        if (twit.contains("#")) {
            Twit twit1 = new Twit();
            String text = twit.substring(twit.indexOf("#"));
            if (text.indexOf(" ") != -1) {
                System.out.println(text.substring(0, text.indexOf(" ")));
                System.out.println(text.substring(text.indexOf(" ")));
                String ic = text.substring(text.indexOf(" ") + 1);
                if (ic.contains("#")) {
                    twit1.setText(ic);
                }
                twit1.setTag(text.substring(0, text.indexOf(" ")));
                return twit1;
            } else {
                System.out.println(text);
                twit1.setTag(text);
                return twit1;
            }
        }
        return null;
    }

    public List<String> getTags(String userName, int quantity) {
        Twitter twitter = getTwitterLogin();
        List<String> userTags = new ArrayList<String>();
        try {
            ResponseList<Status> a = twitter.getUserTimeline(userName, new Paging(1, quantity));
            for (Status b : a) {
                Twit twit = getTag(b.getText());
                if (twit != null) {
                    if (null != twit.getTag()) {
                        userTags.add(twit.getTag());
                    }
                    while (twit.getText() != null) {
                        twit = getTag(twit.getText());
                        if (null != twit.getTag()) {
                            userTags.add(twit.getTag());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userTags;
    }


}