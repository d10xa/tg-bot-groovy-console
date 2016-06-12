package ru.d10xa.groovyconsolebot.botapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Message {
    Long message_id
    User from
    Chat chat
    Long date
    String text
}
