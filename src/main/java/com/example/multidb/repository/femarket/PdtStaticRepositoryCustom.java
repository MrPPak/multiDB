package com.example.multidb.repository.femarket;

import com.example.multidb.domain.femarket.PdtStatic;

import java.util.List;

public interface PdtStaticRepositoryCustom {
    List<PdtStatic> findAllLimit10();
}
