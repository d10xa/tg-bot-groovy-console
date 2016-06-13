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

package ru.d10xa.groovyconsolebot.client

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesRequest
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesResponse
import spock.lang.Specification

class OkHttpGetUpdatesSpec extends Specification {

    MockWebServer server

    GetUpdatesHttpUrlFactory httpUrlFactory

    def setup() {
        server = new MockWebServer()
        httpUrlFactory = { server.url("/getUpdates") }
    }

    def cleanup() {
        server.shutdown()
    }

    def 'one message from private chat'() {
        given:
        String body = getClass().getClassLoader()
                .getResource("json/getUpdates_1465736691.json").text

        server.enqueue(new MockResponse().setBody(body))
        server.start()

        when:
        GetUpdates getUpdates = new OkHttpGetUpdates(httpUrlFactory, new OkHttpClient(), new Gson())
        GetUpdatesResponse updates = getUpdates.get(new GetUpdatesRequest())

        def update0 = updates.result[0]

        then:
        update0.message.text == "2**3"
        server.getRequestCount() == 1
        server.takeRequest().body.readUtf8() == ""
    }

    def 'telegram nginx bad gateway'() {
        given:
        String body = getClass().getClassLoader()
                .getResource("json/getUpdates_nginx_bad_gateway.html").text
        server.enqueue(new MockResponse()
                .setResponseCode(502)
                .setBody(body))

        server.start()

        when:
        GetUpdates getUpdates = new OkHttpGetUpdates(httpUrlFactory, new OkHttpClient(), new Gson())
        getUpdates.get(new GetUpdatesRequest())

        then:
        IOException e = thrown()
        e.message == "502"
    }

}
