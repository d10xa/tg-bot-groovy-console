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

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesRequest;
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesResponse;

import java.io.IOException;

public class OkHttpGetUpdates implements GetUpdates {

    private final GetUpdatesHttpUrlFactory httpUrlFactory;

    private final OkHttpClient client;

    private final Gson gson;

    public OkHttpGetUpdates(
            GetUpdatesHttpUrlFactory httpUrlFactory,
            OkHttpClient client,
            Gson gson) {
        this.httpUrlFactory = httpUrlFactory;
        this.client = client;
        this.gson = gson;
    }

    @Override
    public GetUpdatesResponse get(GetUpdatesRequest getUpdatesRequest) throws IOException {
        Request okHttpRequest = new Request.Builder()
                .url(httpUrlFactory.build(getUpdatesRequest))
                .build();
        Response response = client.newCall(okHttpRequest).execute();
        handleErrors(response);
        return gson.fromJson(response.body().string(), GetUpdatesResponse.class);
    }

    private void handleErrors(Response response) throws IOException {
        if (!response.isSuccessful()) {
            System.out.println(response);
            throw new IOException(String.valueOf(response.code()));
        }
    }

}
