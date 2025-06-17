package org.mapstruct.ap.test.tmp;

import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;

@WithClasses( Issue1234Mapper.class )
public class Issue1234Test {
    @ProcessorTest
    void shouldCompile () {}
}
