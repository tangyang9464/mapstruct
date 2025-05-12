/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._3849;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Issue3849Mapper {

    Issue3849Mapper INSTANCE = Mappers.getMapper( Issue3849Mapper.class );

    @Mapping( target = "value", ignore = true)
    @Mapping(target = "callTimes", ignore = true)
    ParentDto mapParent(Parent sourceA);

    @Mapping( target = "value", ignore = true)
    @Mapping(target = "callTimes", ignore = true)
    ChildDto mapChild(Child sourceA);

    @AfterMapping
    default void afterMappingWithMappingTarget(Parent sourceA, @MappingTarget ParentDto target) {
        target.setValue( "ParentTarget" );
        target.setCallTimes( target.getCallTimes() + 1 );
    }

    @AfterMapping
    default void afterMappingWithMappingTarget(Parent sourceA, @MappingTarget ChildDto target) {
        target.setValue( "ChildTarget" );
        target.setCallTimes( target.getCallTimes() + 1 );
    }

    @AfterMapping
    default void afterMappingNoMappingTarget(Parent sourceA) {
        sourceA.setValue( "ParentSource" );
        sourceA.setSourceCallTimes( sourceA.getSourceCallTimes() + 1 );
    }

    @AfterMapping
    default void afterMappingNoMappingTarget(Child sourceAChild) {
        sourceAChild.setValue( "ChildSource" );
        sourceAChild.setSourceCallTimes( sourceAChild.getSourceCallTimes() + 1 );
    }

    class Parent {
        private String value;
        private int sourceCallTimes;

        public Parent() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getSourceCallTimes() {
            return sourceCallTimes;
        }

        public void setSourceCallTimes(int sourceCallTimes) {
            this.sourceCallTimes = sourceCallTimes;
        }
    }

    class Child extends Parent {
        public Child() {
            super( );
        }
    }

    class ParentDto {
        private String value;
        private int callTimes;

        public ParentDto(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCallTimes() {
            return callTimes;
        }

        public void setCallTimes(int callTimes) {
            this.callTimes = callTimes;
        }
    }

    class ChildDto extends ParentDto {
        public ChildDto(String value) {
            super( value );
        }

        public void setValue(String value) {
            super.setValue( value );
        }

        public void setCallTimes(int callTimes) {
            super.setCallTimes( callTimes );
        }
    }

}
