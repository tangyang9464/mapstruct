/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._3678;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Issue3678Mapper {

    Issue3678Mapper INSTANCE = Mappers.getMapper( Issue3678Mapper.class );

    @Mapping(target = "name", source = "sourceA.name")
    @Mapping(target = "description", source = "sourceB.description")
    Target map(SourceA sourceA, SourceB sourceB, @Context MappingContext context);

    @Mapping(target = "description", constant = "some description")
    Target map(SourceA sourceA, @Context MappingContext context);

    class MappingContext {

        private final List<String> invokedMethods = new ArrayList<>();

        @BeforeMapping
        public void beforeMappingSourceA(SourceA sourceA) {
            invokedMethods.add( "beforeMappingSourceA" );
        }

        @AfterMapping
        public void afterMappingSourceB(SourceA sourceA) {
            invokedMethods.add( "afterMappingSourceA" );
        }

        @BeforeMapping
        public void beforeMappingSourceB(SourceB sourceB) {
            invokedMethods.add( "beforeMappingSourceB" );
        }

        @AfterMapping
        public void afterMappingSourceB(SourceB sourceB) {
            invokedMethods.add( "afterMappingSourceB" );
        }

        @AfterMapping
        public void afterMappingParent(Parent parent) {
            invokedMethods.add( "afterMappingParent" );
        }

        @AfterMapping
        public void afterMappingChild(Child child) {
            invokedMethods.add( "afterMappingChild" );
        }

        public List<String> getInvokedMethods() {
            return invokedMethods;
        }
    }

    class SourceA {

        private final String name;

        public SourceA(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class SourceB {

        private final String description;

        public SourceB(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    final class Target {

        private final String name;
        private final String description;

        private Target(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private String name;
            private String description;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder description(String description) {
                this.description = description;
                return this;
            }

            public Target build() {
                return new Target( this.name, this.description );
            }
        }
    }

    ParentDto map(Parent sourceA);

    ChildDto mapChild(Parent sourceA);

    @AfterMapping
    default void afterMapping(Parent sourceA, @MappingTarget ParentDto target) {
        target.setValue(target.getValue() +"Parent");
    }

    @AfterMapping
    default void afterMapping(Parent sourceA, @MappingTarget ChildDto target) {
        target.setValue(target.getValue() +"Child");
    }

    class Parent {
        private String value;
        public Parent(String value) { this.value = value; }
        public String getValue() { return value; }

        public void setValue(String value) {
            this.value = value;
        }
    }
    class Child extends Parent {
        public Child(String value) { super(value); }
    }

    class ParentDto {
        private String value;
        public ParentDto(String value) { this.value = value; }
        public String getValue() { return value; }
        public void setValue(String value) {
            this.value = value;
        }
    }
    class ChildDto extends ParentDto {
        public ChildDto(String value) { super(value); }
        public void setValue(String value) {
            super.setValue(value);
        }
    }

}
