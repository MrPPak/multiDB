package com.example.multidb.database;

import lombok.var;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@EnableTransactionManagement // @Transactional 어노테이션을 찾아 트랜잭션 범위를 활성화하는 기능
@MapperScan(
        value = "com.example.multidb.mapper.fenote",
        sqlSessionFactoryRef = "feNoteSqlSessionFactory",
        annotationClass = com.example.multidb.database.FeNoteMapper.class
)
@EnableJpaRepositories(
        basePackages = "com.example.multidb.repository.fenote",
        entityManagerFactoryRef = "feNoteEntityManager",
        transactionManagerRef = "feNoteTransactionManager"
)
@Configuration
public class FeNoteDataConfig {
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    public FeNoteDataConfig(JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }

    @Primary
    @Bean(name = "feNoteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.fenotedb")
    public DataSource feNoteDataSource() {
        return DataSourceBuilder.create().build();
    }

    // MyBatis
    @Primary
    @Bean(name = "feNoteSqlSessionFactory")
    public SqlSessionFactory feNoteSqlSessionFactory(@Qualifier("feNoteDataSource") DataSource dataSource,
                                                     ApplicationContext applicationContext) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/fenote/*.xml"));

        SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }

    @Primary
    @Bean(name = "feNoteSqlSessionFactory")
    public SqlSessionTemplate feNoteSqlSessionTemplate(@Qualifier("feNoteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // JPA
    @Primary
    @Bean(name = "feNoteEntityManager")
    public LocalContainerEntityManagerFactoryBean feNoteEntityManager(EntityManagerFactoryBuilder builder) {
        var properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(feNoteDataSource())
                .properties(properties)
                .packages("com.example.multidb.domain.fenote")
                .persistenceUnit("feNoteEntityManager")
                .build();
    }

    @Primary
    @Bean(name = "feNoteTransactionManager")
    PlatformTransactionManager feNoteTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(feNoteEntityManager(builder).getObject()));
    }

}
