package com.example.multidb.mybatis;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.mapper.femarket.PdtStaticMapper;
import com.example.multidb.mapper.fenote.FePointMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class MyBatisTest {
    @Autowired
    PdtStaticMapper pdtStaticMapper;

    @Autowired
    FePointMapper fePointMapper;

    @Test
    void multiMyBatisTest() {
        // when
        List<FePoint> fepoints = fePointMapper.findAll();
        List<PdtStatic> pdtStatics = pdtStaticMapper.findAll();

        // then
        assertAll(() -> {
            assertThat(fepoints.size()).isGreaterThan(0);
            assertThat(pdtStatics.size()).isGreaterThan(0);
        });
    }

}
