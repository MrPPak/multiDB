package com.example.multidb.jpa;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.repository.femarket.PdtStaticRepository;
import com.example.multidb.repository.fenote.FePointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    PdtStaticRepository pdtStaticRepository;

    @Autowired
    FePointRepository fePointRepository;

    @Test
    void multiFindTest() {
        // when
        List<PdtStatic> pdtStatics = pdtStaticRepository.findAllLimit10();
        List<FePoint> fePoints = fePointRepository.findAllLimit10();

        // then
        assertAll(() -> {
            assertThat(pdtStatics).hasSize(10);
            assertThat(fePoints).hasSize(10);
        });
    }

}
