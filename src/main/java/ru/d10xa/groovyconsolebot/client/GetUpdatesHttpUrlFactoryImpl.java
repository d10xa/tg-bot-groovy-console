/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.d10xa.groovyconsolebot.client;

import okhttp3.HttpUrl;
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesRequest;

public class GetUpdatesHttpUrlFactoryImpl implements GetUpdatesHttpUrlFactory {

    private final String botToken;

    public GetUpdatesHttpUrlFactoryImpl(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public HttpUrl build(GetUpdatesRequest request) {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder
                .scheme("https")
                .host("api.telegram.org")
                .addPathSegment(String.join("", "bot", botToken))
                .addPathSegment("getUpdates");
        if (request.getTimeout() != null) {
            builder.addQueryParameter("timeout", String.valueOf(request.getTimeout()));
        }
        if (request.getOffset() != null) {
            builder.addQueryParameter("offset", String.valueOf(request.getOffset()));
        }
        if (request.getLimit() != null) {
            builder.addQueryParameter("limit", String.valueOf(request.getLimit()));
        }

        return builder.build();
    }

}
