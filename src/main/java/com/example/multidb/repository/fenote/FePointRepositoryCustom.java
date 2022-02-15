package com.example.multidb.repository.fenote;

import com.example.multidb.domain.fenote.FePoint;

import java.util.List;

public interface FePointRepositoryCustom {
    List<FePoint> findAllLimit10();
}
