/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020-2021 Andres Almiray.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jreleaser.sdk.twitter;

import org.jreleaser.util.Logger;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import static java.util.Objects.requireNonNull;
import static org.jreleaser.util.StringUtils.requireNonBlank;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public class Twitter {
    private final Logger logger;
    private final twitter4j.Twitter twitter;
    private final boolean dryRun;

    public Twitter(Logger logger, String consumerKey, String consumerToken,
                   String accessToken, String accessTokenSecret, boolean dryRun) {
        requireNonNull(logger, "'logger' must not be blank");
        requireNonBlank(consumerKey, "'consumerKey' must not be blank");
        requireNonBlank(consumerToken, "'consumerToken' must not be blank");
        requireNonBlank(accessToken, "'accessToken' must not be blank");
        requireNonBlank(accessTokenSecret, "'accessTokenSecret' must not be blank");

        this.logger = logger;
        this.dryRun = dryRun;
        this.twitter = TwitterFactory.getSingleton();
        this.twitter.setOAuthConsumer(consumerKey, consumerToken);
        this.twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        this.logger.info("Twitter dryRun set to {}", dryRun);
    }

    public void updateStatus(String status) throws TwitterException {
        wrap(() -> twitter.updateStatus(status));
    }


    private void wrap(TwitterOperation op) throws TwitterException {
        try {
            if (!dryRun) op.execute();
        } catch (twitter4j.TwitterException e) {
            throw new TwitterException("Twitter operation failed", e);
        }
    }

    private interface TwitterOperation {
        void execute() throws twitter4j.TwitterException;
    }
}
