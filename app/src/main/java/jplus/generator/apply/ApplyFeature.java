/*
 * Copyright 2025 Cheol Jeon <nieuwmijnleven@outlook.com>
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

package jplus.generator.apply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyFeature {
    private final String action;
    private final List<String> argumentList;

    public ApplyFeature(String action) {
        this.action = action;
        this.argumentList = new ArrayList<>();
    }

    public ApplyFeature(String action, List<String> argumentList) {
        this(action);
        this.argumentList.addAll(argumentList);
    }

    public void addArgument(String argument) {
        argumentList.add(argument);
    }

    public String getAction() {
        return action;
    }

    public List<String> getArgumentList() {
        return Collections.unmodifiableList(argumentList);
    }

    @Override
    public String toString() {
        return "ApplyFeature{" +
                "action='" + action + '\'' +
                ", argumentList=" + argumentList +
                '}';
    }
}
