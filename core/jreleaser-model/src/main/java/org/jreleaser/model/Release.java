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
package org.jreleaser.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Andres Almiray
 * @since 0.1.0
 */
public class Release implements Domain {
    private Github github;
    private Gitlab gitlab;
    private Gitea gitea;

    void setAll(Release release) {
        this.github = release.github;
        this.gitlab = release.gitlab;
        this.gitea = release.gitea;
    }

    public Github getGithub() {
        return github;
    }

    public void setGithub(Github github) {
        this.github = github;
    }

    public Gitlab getGitlab() {
        return gitlab;
    }

    public void setGitlab(Gitlab gitlab) {
        this.gitlab = gitlab;
    }

    public Gitea getGitea() {
        return gitea;
    }

    public void setGitea(Gitea gitea) {
        this.gitea = gitea;
    }

    public GitService getGitService() {
        if (null != github) return github;
        if (null != gitlab) return gitlab;
        return gitea;
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        if (null != github) map.put("github", github.asMap());
        if (null != gitlab) map.put("gitlab", gitlab.asMap());
        if (null != gitea) map.put("gitea", gitea.asMap());
        return map;
    }
}
