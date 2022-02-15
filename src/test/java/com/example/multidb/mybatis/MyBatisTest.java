package com.example.multidb.mybatis;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.mapper.femarket.PdtStaticMapper;
import com.example.multidb.mapper.fenote.FePointMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisTest {
    @Autowired
    PdtStaticMapper pdtStaticMapper;

    @Autowired
    FePointMapper fePointMapper;

    @Test
    void multiMyBatisTest() {
        List<FePoint> fepoints = fePointMapper.findAll();
        List<PdtStatic> pdtStatics = pdtStaticMapper.findAll();
        System.out.println("fepoints.size() = " + fepoints.size());
        System.out.println("pdtStatics.size() = " + pdtStatics.size());
    }

}
