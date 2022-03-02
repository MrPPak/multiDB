package com.example.multidb.jpa;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.repository.femarket.PdtStaticRepository;
import com.example.multidb.repository.fenote.FePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class JpaTransactionTest {
    @Autowired
    PdtStaticRepository pdtStaticRepository;

    @Autowired
    FePointRepository fePointRepository;

    @BeforeEach
    void setUp() {
        PdtStatic pdtStatic = PdtStatic.builder()
                .shopid("testShopId")
                .pdtCode("testPdtCode")
                .order3daysCnt(3)
                .build();
        pdtStaticRepository.save(pdtStatic);

        FePoint fePoint = FePoint.builder()
                .userId("testUserId")
                .pointNo(0)
                .orderid("testOrderId")
                .build();
        fePointRepository.save(fePoint);
    }

    @Test
    @Transactional("feMarketTransactionManager")
    @Commit
    void pdtStaticTest() {
        // given
        PdtStatic.PdtStaticId pdtStaticId = new PdtStatic.PdtStaticId("testShopId", "testPdtCode");
        PdtStatic pdtStatic = pdtStaticRepository.findById(pdtStaticId)
                .orElseThrow(RuntimeException::new);

        // when
        pdtStatic.setRegtm(LocalDateTime.now());

        // then
    }

    @Test
    @Transactional("feNoteTransactionManager")
    @Commit
    void fePointTest() {
        FePoint.FePointId fePointId = new FePoint.FePointId("2111011548146502", 13);
        FePoint fePoint = fePointRepository.findById(fePointId).get();

        fePoint.setRegtm(LocalDateTime.now());
    }

}
