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

import java.util.ArrayList;
import java.util.List;

public class TypeInfo {
    private final String name;
    private final boolean isNullable;
    private final Type type;
    private final List<TypeInfo> typeParameters;
    private final List<TypeInfo> typeArguments;

    public enum Type {
        Class,
        Method,
        Reference,
        Primitive,
        Constructor,
        Array,
        Unknown,
        TypeParameter,
        TypeArgument
    }

    public TypeInfo(String name, boolean isNullable, Type type, List<TypeInfo> typeParameters, List<TypeInfo> typeArguments) {
        this.name = name;
        this.isNullable = isNullable;
        this.type = type;
        this.typeParameters = typeParameters;
        this.typeArguments = typeArguments;
    }

    public TypeInfo(String name, boolean isNullable, Type type) {
        this(name, isNullable, type, List.of(), List.of());
    }

    public TypeInfo(TypeInfo other) {
        this.name = other.name;
        this.isNullable = other.isNullable;
        this.type = other.type;
        this.typeParameters = other.typeParameters;
        this.typeArguments = other.typeArguments;
    }

    public static TypeInfo from(TypeInfo other) {
        return new TypeInfo(other);
    }

    public Builder toBuilder() {
        return TypeInfo.builder()
                .name(this.name)
                .isNullable(this.isNullable)
                .type(this.type)
                .typeParameters(this.typeParameters)
                .typeArguments(this.typeArguments);
    }

    public String getName() {
        return name;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "name='" + name + '\'' +
                ", isNullable=" + isNullable +
                ", type=" + type +
                ", typeParameters=" + typeParameters +
                ", typeArguments=" + typeArguments +
                '}';
    }

    public static class Builder {
        private String name;
        private boolean isNullable;
        private Type type;
        private List<TypeInfo> typeParameters = List.of();
        private List<TypeInfo> typeArguments = List.of();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder isNullable(boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder typeParameters(List<TypeInfo> typeParameters) {
            this.typeParameters = typeParameters;
            return this;
        }

        public Builder typeArguments(List<TypeInfo> typeArguments) {
            this.typeArguments = typeArguments;
            return this;
        }

        public TypeInfo build() {
            return new TypeInfo(name, isNullable, type, typeParameters, typeArguments);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
