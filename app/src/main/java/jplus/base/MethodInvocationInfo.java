package jplus.base;

import java.util.List;

//apply builder;

public class MethodInvocationInfo {
    public final String instanceName;
    public final String methodName;
    public final List<String> args;
    public final List<String> argTypes;
    public final String returnType;
    public final String source;
    public final long startPos;
    public final long endPos;    

    public MethodInvocationInfo(String instanceName, String methodName, List<String> args, List<String> argTypes, String returnType, String source, long startPos, long endPos) {
        this.instanceName = instanceName;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = argTypes;
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
        private String returnType;
        private String source;
        private long startPos;
        private long endPos;
    
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
    
        public Builder returnType(String returnType) {
            this.returnType = returnType;
            return this;
        }
    
        public Builder source(String source) {
            this.source = source;
            return this;
        }
    
        public Builder startPos(long startPos) {
            this.startPos = startPos;
            return this;
        }
    
        public Builder endPos(long endPos) {
            this.endPos = endPos;
            return this;
        }
    
        public MethodInvocationInfo build() {
            return new MethodInvocationInfo(instanceName, methodName, args, argTypes, returnType, source, startPos, endPos);
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
                ", returnType='" + returnType + '\'' +
                ", source ='" + source + '\'' +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                '}';
    }
}