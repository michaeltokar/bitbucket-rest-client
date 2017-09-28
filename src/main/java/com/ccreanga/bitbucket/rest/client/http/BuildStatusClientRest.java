/*
 *
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.ccreanga.bitbucket.rest.client.http;

import com.ccreanga.bitbucket.rest.client.BuildStatusClient;
import com.ccreanga.bitbucket.rest.client.model.BuildStatsSummary;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.ccreanga.bitbucket.rest.client.http.HttpMethod.POST;
import static com.ccreanga.bitbucket.rest.client.http.responseparsers.Parsers.buildStatsParser;

class BuildStatusClientRest extends BitBucketClient implements BuildStatusClient {

    public static final int DEFAULT_LIMIT = 100;

    public BuildStatusClientRest(BitBucketHttpExecutor bitBucketHttpExecutor) {
        super(bitBucketHttpExecutor);
    }

    @Override
    public Map<String, BuildStatsSummary> getBuildSummaries(List<String> commits) {
        String requestUrl = "/rest/build-status/latest/commits/stats";
        try {
            final String requestJson = new Gson().toJson(commits);
            JsonElement jsonElement = execute(requestUrl, POST, requestJson).get();
            return buildStatsParser().apply(jsonElement);
        }catch (ResourceNotFoundException e){
            return Collections.emptyMap();
        }
    }

}
