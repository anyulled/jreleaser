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
package org.jreleaser.releaser;

import org.jreleaser.model.JReleaserModel;
import org.jreleaser.model.releaser.spi.ReleaserBuilder;
import org.jreleaser.sdk.github.GithubReleaser;
import org.jreleaser.util.Logger;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public class Releasers {
    public static <RB extends ReleaserBuilder> RB findReleaser(Logger logger, JReleaserModel model) {
        if (null != model.getRelease().getGithub()) {
            return (RB) GithubReleaser.builder(logger);
        }
        // if(null != model.getRelease().getGitlab()) {
        //     return (RB) GitlabReleaser.builder();
        // }
        // if(null != model.getRelease().getGitea()) {
        //     return (RB) GiteaReleaser.builder();
        // }
        throw new IllegalArgumentException("No suitable git releaser has been configured");
    }
}