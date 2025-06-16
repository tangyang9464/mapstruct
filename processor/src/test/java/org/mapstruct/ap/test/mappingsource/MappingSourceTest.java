/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.mappingsource;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.ap.testutil.IssueKey;
import org.mapstruct.ap.testutil.ProcessorTest;
import org.mapstruct.ap.testutil.WithClasses;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试多源映射中的@MappingSource注解功能
 */
@IssueKey("2559")
public class MappingSourceTest {

    @ProcessorTest
    @WithClasses( MultiSourceMapMapper.class )
    public void testMultiSourceWithImplicitMapping() {
        Map<String, String> mapSource = new HashMap<>();
        mapSource.put("mapId", "1");
        mapSource.put("mapName", "MapTest");
        
        MultiSourceMapMapper.BeanSource beanSource = new MultiSourceMapMapper.BeanSource();
        beanSource.setBeanId(2);
        beanSource.setBeanName("BeanTest");

        MultiSourceMapMapper.MapTarget mapTarget = MultiSourceMapMapper.INSTANCE.mapWithImplicitMap(mapSource, null);

        assertThat(mapTarget).isNotNull();
        assertThat(mapTarget.getMapId()).isEqualTo("1");
        assertThat(mapTarget.getMapName()).isEqualTo("MapTest");

        MultiSourceMapMapper.BeanTarget beanTarget = MultiSourceMapMapper.INSTANCE.mapWithImplicitBean(beanSource, null);
        assertThat(beanTarget).isNotNull();
        assertThat(beanTarget.getBeanId()).isNull();
        assertThat(beanTarget.getBeanName()).isNull();
    }
} 