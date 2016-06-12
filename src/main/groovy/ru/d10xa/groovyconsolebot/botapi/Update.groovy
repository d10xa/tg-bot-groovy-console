package ru.d10xa.groovyconsolebot.botapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class Update {
    Long update_id
    Message message
}
