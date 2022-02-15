package com.example.multidb.database;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {
    @PersistenceContext(unitName = "feNoteEntityManager")
    EntityManager feNoteEntityManager;

    @PersistenceContext(unitName = "feMarketEntityManager")
    EntityManager feMarketEntityManager;

    @Bean
    public JPAQueryFactory feNoteQueryFactory() {
        return new JPAQueryFactory(feNoteEntityManager);
    }

    @Bean
    public JPAQueryFactory feMarketQueryFactory() {
        return new JPAQueryFactory(feMarketEntityManager);
    }
}
