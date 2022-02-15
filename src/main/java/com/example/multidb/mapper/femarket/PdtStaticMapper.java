package com.example.multidb.mapper.femarket;

import com.example.multidb.database.FeMarketMapper;
import com.example.multidb.domain.femarket.PdtStatic;

import java.util.List;

@FeMarketMapper
public interface PdtStaticMapper {
    List<PdtStatic> findAll();
}
