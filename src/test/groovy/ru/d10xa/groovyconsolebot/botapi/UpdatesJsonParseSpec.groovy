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

package ru.d10xa.groovyconsolebot.botapi

import com.google.gson.Gson
import spock.lang.Specification

class UpdatesJsonParseSpec extends Specification {

    String json

    def setup(){
        json = getClass().getClassLoader().getResource("json/getUpdates_1465736691.json").text
    }

    def 'parse getUpdates from private chat'() {
        given:
        Chat chat = new Chat(
                id: 5556660,
                first_name: 'First',
                last_name: 'Last',
                username: 'd10xa',
                type: 'private'
        )
        User from = new User(
                id: 1111111,
                first_name: 'First',
                last_name: 'Last',
                username: 'd10xa'
        )

        when:

        GetUpdatesResponse updates = new Gson().fromJson(json, GetUpdatesResponse)
        def update0 = updates.result[0]

        then:
        updates.ok
        updates.result.size() == 1
        update0.message.chat == chat
        update0.message.from == from

        update0 == new Update(
                update_id: 123456789,
                message: new Message(
                        message_id: 585,
                        chat: chat,
                        from: from,
                        date: 1465736691,
                        text: '2**3'

                )
        )
    }
}
