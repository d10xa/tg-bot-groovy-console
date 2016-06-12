package ru.d10xa.groovyconsolebot.botapi

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
final class User {

    Long id

    String first_name

    String last_name

    String username

}
