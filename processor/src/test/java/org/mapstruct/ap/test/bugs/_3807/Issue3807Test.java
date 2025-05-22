/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.bugs._3807;

import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;

@WithClasses({Issue3807Mapper.class, Response.class, WithStatus.class})
public class Issue3807Test {

    @ProcessorTest
    public void shouldGenerateCorrectCode() {
    }
}
