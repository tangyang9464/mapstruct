/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.mappingsource;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.MappingSource;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 用于测试多源映射中Map的implicitMapping功能
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MultiSourceMapMapper {

    MultiSourceMapMapper INSTANCE = Mappers.getMapper( MultiSourceMapMapper.class );

    /**
     * 当Map被标记为implicitMapping=true时，它会被用于隐式映射
     */
    MapTarget mapWithImplicitMap(@MappingSource Map<String, String> mapSource, OtherSource otherSource);

    BeanTarget mapWithImplicitBean(@MappingSource(implicitMapping = false) BeanSource beanSource, OtherSource otherSource);

    class MapTarget {
        private String mapId;
        private String mapName;

        public String getMapId() {
            return mapId;
        }

        public void setMapId(String mapId) {
            this.mapId = mapId;
        }

        public String getMapName() {
            return mapName;
        }

        public void setMapName(String mapName) {
            this.mapName = mapName;
        }
    }

    class BeanTarget {
        private Integer beanId;
        private String beanName;

        public Integer getBeanId() {
            return beanId;
        }

        public void setBeanId(Integer beanId) {
            this.beanId = beanId;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }
    }

    class BeanSource {
        private Integer beanId;
        private String beanName;

        public Integer getBeanId() {
            return beanId;
        }

        public void setBeanId(Integer beanId) {
            this.beanId = beanId;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }
    }

    class OtherSource {
        private final Long id;

        public OtherSource(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }
} 