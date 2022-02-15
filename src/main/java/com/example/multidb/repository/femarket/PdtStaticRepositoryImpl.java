package com.example.multidb.repository.femarket;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.femarket.QPdtStatic;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class PdtStaticRepositoryImpl implements PdtStaticRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QPdtStatic pdtStatic = QPdtStatic.pdtStatic;

    public PdtStaticRepositoryImpl(JPAQueryFactory feMarketQueryFactory) {
        this.queryFactory = feMarketQueryFactory;
    }

    @Override
    public List<PdtStatic> findAllLimit10() {
        return queryFactory.selectFrom(pdtStatic)
                .limit(10)
                .fetch();
    }


}
