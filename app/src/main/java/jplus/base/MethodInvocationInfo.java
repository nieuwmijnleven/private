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


package jplus.base;

import java.util.List;

public class MethodInvocationInfo {
    public final String instanceName;
    public final String methodName;
    public final List<String> args;
    public final List<String> argTypes;
    public final List<String> paramTypes;
    public final String returnType;
    public final String source;
    public final int startPos;
    public final int endPos;

    public MethodInvocationInfo(String instanceName, String methodName, List<String> args, List<String> argTypes, List<String> paramTypes, String returnType, String source, int startPos, int endPos) {
        this.instanceName = instanceName;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = argTypes;
        this.paramTypes = paramTypes;
        this.returnType = returnType;
        this.source = source;
        this.startPos = startPos;
        this.endPos = endPos;
    }
    
    public static class Builder {
        private String instanceName;
        private String methodName;
        private List<String> args;
        private List<String> argTypes;
        private List<String> paramTypes;
        private String returnType;
        private String source;
        private int startPos;
        private int endPos;
    
        public Builder instanceName(String instanceName) {
            this.instanceName = instanceName;
            return this;
        }
    
        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }
    
        public Builder args(List<String> args) {
            this.args = args;
            return this;
        }
    
        public Builder argTypes(List<String> argTypes) {
            this.argTypes = argTypes;
            return this;
        }

        public Builder paramTypes(List<String> paramTypes) {
            this.paramTypes = paramTypes;
            return this;
        }
    
        public Builder returnType(String returnType) {
            this.returnType = returnType;
            return this;
        }
    
        public Builder source(String source) {
            this.source = source;
            return this;
        }
    
        public Builder startPos(int startPos) {
            this.startPos = startPos;
            return this;
        }
    
        public Builder endPos(int endPos) {
            this.endPos = endPos;
            return this;
        }
    
        public MethodInvocationInfo build() {
            return new MethodInvocationInfo(instanceName, methodName, args, argTypes, paramTypes, returnType, source, startPos, endPos);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "MethodInvocationInfo{" +
                "instanceName='" + instanceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + args +
                ", argTypes=" + argTypes +
                ", paramTypes=" + paramTypes +
                ", returnType='" + returnType + '\'' +
                ", source ='" + source + '\'' +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                '}';
    }
}