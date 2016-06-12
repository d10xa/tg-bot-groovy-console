package ru.d10xa.groovyconsolebot.botapi

import com.google.gson.Gson
import spock.lang.Specification

class SendMessageJsonSerializeSpec extends Specification {

    String json

    def setup() {
        json = getClass().getClassLoader().getResource("json/sendMessage_1465736691.json").text
    }

    def 'serialize sendMessage request'() {
        given:
        SendMessageRequest expectedRequest = new SendMessageRequest(
                "chat_id": "5556660",
                "text": "8",
                "reply_to_message_id": 585
        )

        when:
        SendMessageRequest request = new Gson().fromJson(json, SendMessageRequest)

        then:
        request.chat_id == expectedRequest.chat_id
        request.reply_to_message_id == expectedRequest.reply_to_message_id
        request.text == expectedRequest.text
        request == expectedRequest
    }
}
