package org.mapstruct.ap.test.tmp;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface Issue1234Mapper {

    @Mapping(target = "map", source = "map")
    @Mapping(target = "collection", ignore = true)
    @Mapping(target = "bean", ignore = true)
    Target mapToTarget(Map<String, String> map);

    @Mapping( target = "bean", source = "bean")
    Target mapToTarget(Bean bean, Collection<String> collection, Map<String, String> map);

//    @Mapping( target = "collection", source = "collection")
//    Target collectionToTarget(Collection<String> collection);

    @Mapping(target = "map", ignore = true)
    @Mapping(target = "collection", ignore = true)
    Target beanToTarget(Bean bean);

    class Target {
        private Map<String, String> map;
        private Collection<String> collection;
        private Bean bean;

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public Collection<String> getCollection() {
            return collection;
        }

        public void setCollection(Collection<String> collection) {
            this.collection = collection;
        }

        public Bean getBean() {
            return bean;
        }

        public void setBean(Bean bean) {
            this.bean = bean;
        }
    }

    class Bean {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }


    class Metadata {
        public String a;
        public String b;
    }

    class Thing {
        public String a;
        public String b;
        public Collection<String> foo;
        public Path path;
    }

    Thing create(Metadata meta, Collection<String> foo, Path path);

    class Container {
        public Collection<Metadata> things;
        public Collection<Thing> otherThings;
    }

    @Mapping(target = "things", source = "things")
    Container create(@Context Collection<Thing> things);
}
