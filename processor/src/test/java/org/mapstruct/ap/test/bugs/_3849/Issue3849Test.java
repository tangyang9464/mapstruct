/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package org.mapstruct.ap.test.bugs._3849;

import org.mapstruct.ap.testutil.IssueKey;
import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;

import static org.assertj.core.api.Assertions.assertThat;

@IssueKey("3849")
@WithClasses(Issue3849Mapper.class)
public class Issue3849Test {

    @ProcessorTest
    void afterMappingOverloadOnlyChildCalled() {
        Issue3849Mapper.Child child = new Issue3849Mapper.Child();
        Issue3849Mapper.Parent parent = new Issue3849Mapper.Parent();

        Issue3849Mapper.ParentDto parentDto = Issue3849Mapper.INSTANCE.mapParent(parent);
        assertThat( parentDto.getValue() ).isEqualTo( "ParentTarget" );
        assertThat( parentDto.getCallTimes() ).isEqualTo( 1 );
        assertThat( parent.getValue() ).isEqualTo( "ParentSource" );
        assertThat( parent.getSourceCallTimes() ).isEqualTo( 1 );

        Issue3849Mapper.ParentDto childDto = Issue3849Mapper.INSTANCE.mapChild(child);
        assertThat( childDto.getValue() ).isEqualTo( "ChildTarget" );
        assertThat( childDto.getCallTimes() ).isEqualTo( 1 );
        assertThat( child.getValue() ).isEqualTo( "ChildSource" );
        assertThat( child.getSourceCallTimes() ).isEqualTo( 1 );
    }

}
