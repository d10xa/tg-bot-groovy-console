package ru.d10xa.groovyconsolebot.botapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
final class Chat {

    Long id

    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    String type

    String title

    String first_name

    String last_name

    String username

}
