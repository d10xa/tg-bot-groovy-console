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

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

/**
 * This object represents a chat.
 * https://core.telegram.org/bots/api#chat
 */
@CompileStatic
@EqualsAndHashCode
final class Chat {

    Long id

    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    String type

    String title

    @SuppressWarnings('PropertyName')
    String first_name

    @SuppressWarnings('PropertyName')
    String last_name

    String username

}
