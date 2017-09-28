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

package com.ccreanga.bitbucket.rest.client.http.responseparsers;

import com.ccreanga.bitbucket.rest.client.model.BuildStatsSummary;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.function.Function;

class BuildStatsParser implements Function<JsonElement, Map<String, BuildStatsSummary>> {
    @Override
    public Map<String, BuildStatsSummary> apply(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        ImmutableMap.Builder<String, BuildStatsSummary> builder = ImmutableMap.builder();
        for (Map.Entry<String, JsonElement> commitEntries : json.entrySet()) {
            BuildStatsSummary.Builder element = BuildStatsSummary.builder();
            for (Map.Entry<String, JsonElement> e: commitEntries.getValue().getAsJsonObject().entrySet()) {
                if ("failed".equals(e.getKey())) {
                    element.setFailed(e.getValue().getAsLong());
                } else if ("inProgress".equals(e.getKey())) {
                    element.setInProgress(e.getValue().getAsLong());
                } else if ("successful".equals(e.getKey())) {
                    element.setSuccessful(e.getValue().getAsLong());
                }
            }
            builder.put(commitEntries.getKey(), element.build());
        }
        return builder.build();
    }
}
