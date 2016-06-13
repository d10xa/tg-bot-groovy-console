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

    def 'parse getUpdates from private chat'() {
        given:
        json = getClass().getClassLoader().getResource("json/getUpdates_1465736691.json").text
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

    def 'parse getUpdates add bot to group'() {
        given:
        json = getClass().getClassLoader().getResource("json/getUpdates_add_to_group_1465799185.json").text
        Chat chat = new Chat(
                id: -101111777,
                title: 'GroovyTest',
                type: 'group'
        )
        User from = new User(
                id: 1111111,
                first_name: 'First',
                last_name: 'Last',
                username: 'd10xa'
        )
        User bot = new User(
                id: 199999111,
                first_name: "Groovy Console",
                username: "TestGroovyConsoleBot"
        )
        Message message = new Message(
                message_id: 586,
                chat: chat,
                from: from,
                date: 1465799185,
                new_chat_member: bot

        )
        when:

        GetUpdatesResponse updates = new Gson().fromJson(json, GetUpdatesResponse)
        def update0 = updates.result[0]

        then:
        updates.ok
        updates.result.size() == 1
        update0.message.chat == chat
        update0.message.from == from
        update0.message.new_chat_member == bot
        update0.message == message

        update0 == new Update(
                update_id: 596105480,
                message: message
        )
    }
}
