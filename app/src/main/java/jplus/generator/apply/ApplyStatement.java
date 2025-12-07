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

public class ApplyStatement {
    private String qualifiedName;
    private final List<ApplyFeature> featureList;

    public ApplyStatement(String qualifiedName) {
        this.qualifiedName = qualifiedName;
        this.featureList = new ArrayList<>();
    }

    public ApplyStatement(String qualifiedName, List<ApplyFeature> featureList) {
        this(qualifiedName);
        this.featureList.addAll(featureList);
    }

    public void addApplyFeature(ApplyFeature feature) {
        featureList.add(feature);
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public List<ApplyFeature> getFeatureList() {
        return Collections.unmodifiableList(featureList);
    }

    @Override
    public String toString() {
        return "ApplyStatement{" +
                "qualifiedName='" + qualifiedName + '\'' +
                ", featureList=" + featureList +
                '}';
    }
}
