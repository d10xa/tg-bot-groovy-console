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

package ru.d10xa.groovyconsolebot.security

import spock.lang.Specification

class StrictlyProhibitedSecurityManagerSpec extends Specification {

    def 'System.exit forbidden'() {
        given:
        System.setSecurityManager(new StrictlyProhibitedSecurityManager())

        when:
        System.exit(42)

        then:
        SecurityException e = thrown()
        e.message == "Use of System.exit() is forbidden!"

        cleanup:
        System.setSecurityManager(null)
    }

    def 'inability to create tmp file'() {
        given:
        System.setSecurityManager(new StrictlyProhibitedSecurityManager())

        when:
        File.createTempFile("abc", ".txt")

        then:
        SecurityException e = thrown()
        e.message == "Unable to create temporary file"

        cleanup:
        System.setSecurityManager(null)
    }

    def 'file write forbidden'() {
        given:
        System.setSecurityManager(new StrictlyProhibitedSecurityManager())

        when:
        new File('tmp.txt').text = 'hello'

        then:
        SecurityException e = thrown()
        e.message == "Writing file is forbidden!"

        cleanup:
        System.setSecurityManager(null)
    }

    def 'check parent security manager permission'() {
        given:
        def mockSecurityManager = Mock(SecurityManager)
        PropertyPermission permittedPermission = new PropertyPermission('java.version', 'read')

        when:
        def strictlyProhibitedSecurityManager = new StrictlyProhibitedSecurityManager(mockSecurityManager)
        strictlyProhibitedSecurityManager.checkPermission(permittedPermission)

        then:
        noExceptionThrown()
        1 * mockSecurityManager.checkPermission({ it.name == 'java.version' && it.actions == 'read' })
    }

}
