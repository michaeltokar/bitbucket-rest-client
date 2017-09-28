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
import com.ccreanga.bitbucket.rest.client.ProjectClient;
import com.ccreanga.bitbucket.rest.client.SshClient;

public class BitBucketClientFactory {

    private ProjectClient projectClient;
    private BuildStatusClient buildStatusClient;
    private SshClient sshClient;
    private BitBucketHttpExecutor bitBucketHttpExecutor;

    public BitBucketClientFactory(String baseUrl, BitBucketCredentials credentials) {
        bitBucketHttpExecutor = new BitBucketHttpExecutor(baseUrl, credentials);
        projectClient = new ProjectClientRest(bitBucketHttpExecutor);
        buildStatusClient = new BuildStatusClientRest(bitBucketHttpExecutor);
        sshClient = new SshClientRest(bitBucketHttpExecutor);
    }

    public void shutdown(){
        bitBucketHttpExecutor.shutdown();
    }

    public ProjectClient getProjectClient() {
        return projectClient;
    }

    public BuildStatusClient getBuildStatusClient() {
        return buildStatusClient;
    }

    public SshClient getSshClient() {
        return sshClient;
    }
}
