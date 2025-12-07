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

public class TypeInfo {
    public final String name;
    public boolean isNullable;
    public final Type type;

    public enum Type {
        Class,
        Method,
        Reference,
        Primitive,
        Constructor,
        Array,
        Unknown;
    }
    public TypeInfo(String name, boolean isNullable, Type type) {
        this.name = name;
        this.isNullable = isNullable;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public Type getType() {
        return type;
    }

    public static TypeInfo copyOf(TypeInfo src) {
        return TypeInfo.builder()
                .name(src.name)
                .isNullable(src.isNullable)
                .type((src.type))
                .build();
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "name='" + name + '\'' +
                ", isNullable=" + isNullable +
                ", type=" + type +
                '}';
    }

    public static class Builder {
        private String name;
        private boolean isNullable;
        private Type type;

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

        public TypeInfo build() {
            return new TypeInfo(name, isNullable, type);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
