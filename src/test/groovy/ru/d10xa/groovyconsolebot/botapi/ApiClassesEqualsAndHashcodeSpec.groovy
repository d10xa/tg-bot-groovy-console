package ru.d10xa.groovyconsolebot.botapi

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification
import spock.lang.Unroll

import static nl.jqno.equalsverifier.Warning.*

@Unroll
class ApiClassesEqualsAndHashcodeSpec extends Specification {

    @Unroll
    def '#clazz.simpleName'() {
        when:
        EqualsVerifier
                .forClass(clazz)
                .suppress(NONFINAL_FIELDS, NULL_FIELDS, STRICT_HASHCODE, STRICT_INHERITANCE)
                .verify()

        then:
        noExceptionThrown()

        where:
        clazz << [Chat, GetUpdatesResponse, Message, SendMessageRequest, Update, User]
    }

}
