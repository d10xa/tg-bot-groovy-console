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
 * Use this method to receive incoming updates using long polling
 * @see <a href="https://core.telegram.org/bots/api#getupdates">https://core.telegram.org/bots/api#getupdates</a>
 */
@CompileStatic
@EqualsAndHashCode
class GetUpdatesRequest {

    /**
     * Identifier of the first update to be returned.
     * Must be greater by one than the highest among
     * the identifiers of previously received updates.
     * By default, updates starting with the earliest
     * unconfirmed update are returned.
     * An update is considered confirmed as soon
     * as getUpdates is called with an offset higher
     * than its update_id. The negative offset can be
     * specified to retrieve updates starting from -offset
     * update from the end of the updates queue.
     * All previous updates will forgotten.
     */
    Long offset

    /**
     * Limits the number of updates to be retrieved.
     * Values between 1—100 are accepted. Defaults to 100.
     */
    Long limit

    /**
     * Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
     */
    Long timeout

}
