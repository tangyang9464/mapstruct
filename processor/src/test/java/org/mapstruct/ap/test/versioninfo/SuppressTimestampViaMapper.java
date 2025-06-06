/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.versioninfo;

import org.mapstruct.Mapper;
import org.mapstruct.ap.test.WithProperties;

/**
 * @author Filip Hrisafov
 */
@Mapper(suppressTimestampInGenerated = true)
public interface SuppressTimestampViaMapper {
    WithProperties toObject(WithProperties object);
}
