package ru.d10xa.groovyconsolebot.botapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class SendMessageRequest {

    /**
     * Unique identifier for the target chat or username
     * of the target channel (in the format @channelusername)
     */
    String chat_id

    String text

    String reply_to_message_id
}
