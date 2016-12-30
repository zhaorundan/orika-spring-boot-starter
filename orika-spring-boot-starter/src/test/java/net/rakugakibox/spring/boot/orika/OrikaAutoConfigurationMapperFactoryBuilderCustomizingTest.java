package net.rakugakibox.spring.boot.orika;

import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The {@link OrikaAutoConfiguration}'s test cases when customize {@link MapperFactoryBuilder}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrikaAutoConfigurationMapperFactoryBuilderCustomizingTest {

    /**
     * The {@link MapperFactoryBuilder}.
     */
    @Autowired
    protected MapperFactoryBuilder<?, ?> mapperFactoryBuilder;

    /**
     * The {@link MapperFactoryBuilder}'s configuration 1.
     */
    @Autowired
    @Qualifier("orikaMapperFactoryBuilderConfiguration1")
    protected MapperFactoryBuilderConfiguration mapperFactoryBuilderConfiguration1;

    /**
     * The {@link MapperFactoryBuilder}'s configuration 2.
     */
    @Autowired
    @Qualifier("orikaMapperFactoryBuilderConfiguration2")
    protected MapperFactoryBuilderConfiguration mapperFactoryBuilderConfiguration2;

    /**
     * Tests the {@link OrikaAutoConfiguration#orikaMapperFactoryBuilder()}.
     */
    @Test
    public void orikaMapperFactoryBuilder_configurerShouldBeCalled() {
        assertThat(mapperFactoryBuilder)
                .isSameAs(mapperFactoryBuilderConfiguration1.mapperFactoryBuilder)
                .isSameAs(mapperFactoryBuilderConfiguration2.mapperFactoryBuilder);
    }

    /**
     * The context configuration.
     */
    @Configuration
    @EnableAutoConfiguration
    public static class ContextConfiguration {

        /**
         * Creates a {@link MapperFactoryBuilder}'s configuration 1.
         *
         * @return a {@link MapperFactoryBuilder}'s configuration 1.
         */
        @Bean
        public MapperFactoryBuilderConfiguration orikaMapperFactoryBuilderConfiguration1() {
            return new MapperFactoryBuilderConfiguration();
        }

        /**
         * Creates a {@link MapperFactoryBuilder}'s configuration 2.
         *
         * @return a {@link MapperFactoryBuilder}'s configuration 2.
         */
        @Bean
        public MapperFactoryBuilderConfiguration orikaMapperFactoryBuilderConfiguration2() {
            return new MapperFactoryBuilderConfiguration();
        }

    }

    /**
     * The {@link MapperFactoryBuilder}'s configuration.
     */
    public static class MapperFactoryBuilderConfiguration implements OrikaMapperFactoryBuilderConfigurer {

        /**
         * The passed {@link MapperFactoryBuilder}.
         */
        private MapperFactoryBuilder<?, ?> mapperFactoryBuilder;

        /** {@inheritDoc} */
        @Override
        public void configure(MapperFactoryBuilder<?, ?> mapperFactoryBuilder) {
            this.mapperFactoryBuilder = mapperFactoryBuilder;
        }

    }

}