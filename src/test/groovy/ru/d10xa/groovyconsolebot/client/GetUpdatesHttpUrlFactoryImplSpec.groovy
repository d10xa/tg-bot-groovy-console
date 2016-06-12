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

import okhttp3.HttpUrl
import ru.d10xa.groovyconsolebot.botapi.GetUpdatesRequest
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class GetUpdatesHttpUrlFactoryImplSpec extends Specification {

    @Unroll
    def 'httpUrl builder #expectedUrl'() {
        given:
        String botToken = "12345"
        GetUpdatesHttpUrlFactory factory = new GetUpdatesHttpUrlFactoryImpl(botToken)

        when:
        HttpUrl httpUrl = factory.build(new GetUpdatesRequest(
                timeout: timeout,
                limit: limit,
                offset: offset
        ))

        String url = "https://api.telegram.org/bot12345/getUpdates?$expectedUrl"

        then:
        httpUrl.toString() == url

        where:
        timeout | offset | limit || expectedUrl
        1       | null   | null  || "timeout=1"
        null    | 2      | null  || "offset=2"
        null    | null   | 3     || "limit=3"
        4       | null   | 5     || "timeout=4&limit=5"
        6       | 7      | null  || "timeout=6&offset=7"
        8       | 9      | 10    || "timeout=8&offset=9&limit=10"

    }

}
