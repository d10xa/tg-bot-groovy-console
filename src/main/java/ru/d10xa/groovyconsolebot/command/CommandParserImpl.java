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
package ru.d10xa.groovyconsolebot.command;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link CommandParser} based on regular expressions.
 */
public class CommandParserImpl implements CommandParser {

    private final static Pattern PATTERN_SHORT = Pattern.compile("^[/]([a-zA-Z0-9_]+)[\\s]?");
    private final static Pattern PATTERN_FULL = Pattern.compile("^[/]([a-zA-Z0-9_]+)@([a-zA-Z0-9_]+)[\\s]?");
    private final String rawText;

    private boolean initialized;
    private String text;
    private String command;

    public CommandParserImpl(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public Optional<String> getText() {
        init();
        return Optional.ofNullable(this.text);
    }

    @Override
    public Optional<String> getCommand() {
        init();
        return Optional.ofNullable(this.command);
    }

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        if (rawText == null || rawText.isEmpty()) {
            initialized = true;
            return;
        }
        if (rawText.startsWith("/")) {
            initWithCommand();
        } else {
            initWithoutCommand();
        }
    }

    private void initWithCommand() {
        Matcher matcher = PATTERN_FULL.matcher(rawText);
        if (matcher.find()) {
            command = matcher.group(1);
            text = rawText.substring(matcher.end());
        } else {
            matcher = PATTERN_SHORT.matcher(rawText);
            if (matcher.find()) {
                command = matcher.group(1);
                text = rawText.substring(matcher.end());
            }
        }
    }

    private void initWithoutCommand() {
        this.text = rawText;
    }
}
