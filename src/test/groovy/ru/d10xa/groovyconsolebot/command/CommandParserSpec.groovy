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
package ru.d10xa.groovyconsolebot.command

import spock.lang.Specification

class CommandParserSpec extends Specification {

    def 'message with command and text'() {
        def cmdParser = new CommandParserImpl("/groovy 2**3")

        expect:
        cmdParser.text.get() == "2**3"
        cmdParser.command.get() == "groovy"
    }

    def 'message with mention and command and text'() {
        def cmdParser = new CommandParserImpl("/groovy@GroovyConsoleBot 2**3")

        expect:
        cmdParser.text.get() == "2**3"
        cmdParser.command.get() == "groovy"
    }

    def 'empty message'() {
        def cmdParser = new CommandParserImpl("")

        expect:
        !cmdParser.text.isPresent()
        !cmdParser.command.isPresent()
    }

    def 'null message'() {
        def cmdParser = new CommandParserImpl(null)

        expect:
        !cmdParser.text.isPresent()
        !cmdParser.command.isPresent()
    }

    def 'only spaces'() {
        def cmdParser = new CommandParserImpl("  ")

        expect:
        !cmdParser.command.isPresent()
        cmdParser.text.get() == "  "
    }

}
