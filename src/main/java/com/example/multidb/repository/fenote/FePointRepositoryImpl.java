package com.example.multidb.repository.fenote;

import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.domain.fenote.QFePoint;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class FePointRepositoryImpl implements FePointRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QFePoint fePoint = QFePoint.fePoint;

    public FePointRepositoryImpl(JPAQueryFactory feNoteQueryFactory) {
        this.queryFactory = feNoteQueryFactory;
    }

    @Override
    public List<FePoint> findAllLimit10() {
        return queryFactory.selectFrom(fePoint)
                .limit(10)
                .fetch();
    }
}
