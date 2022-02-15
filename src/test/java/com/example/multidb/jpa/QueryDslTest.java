package com.example.multidb.jpa;

import com.example.multidb.repository.femarket.PdtStaticRepository;
import com.example.multidb.repository.fenote.FePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    PdtStaticRepository pdtStaticRepository;

    @Autowired
    FePointRepository fePointRepository;

    @Test
    void multiFindTest() {
        pdtStaticRepository.findAllLimit10();
        fePointRepository.findAllLimit10();
    }

}
