package com.example.multidb.jpa;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.repository.femarket.PdtStaticRepository;
import com.example.multidb.repository.fenote.FePointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpaTransactionTest {
    @Autowired
    PdtStaticRepository pdtStaticRepository;

    @Autowired
    FePointRepository fePointRepository;

    @Autowired
    EntityManager feMarketEntityManager;

    @Autowired
    EntityManager feNoteEntityManager;

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
    void pdtStaticTest() {
        // given
        PdtStatic.PdtStaticId pdtStaticId = new PdtStatic.PdtStaticId("testShopId", "testPdtCode");
        PdtStatic pdtStatic = pdtStaticRepository.findById(pdtStaticId)
                .orElseThrow(RuntimeException::new);

        // when
        int order3daysCnt = 4;
        pdtStatic.updateOrder3daysCnt(order3daysCnt);
        feMarketEntityManager.flush();
        feMarketEntityManager.clear();

        // then
        PdtStatic findPdtStatic = pdtStaticRepository.findById(pdtStaticId)
                .orElseThrow(RuntimeException::new);
        assertThat(findPdtStatic.getOrder3daysCnt()).isEqualTo(order3daysCnt);
    }

    @Test
    @Transactional("feNoteTransactionManager")
    void fePointTest() {
        // given
        FePoint.FePointId fePointId = new FePoint.FePointId("testUserId", 0);
        FePoint fePoint = fePointRepository.findById(fePointId)
                .orElseThrow(RuntimeException::new);

        // when
        String orderId = "updateOrderId";
        fePoint.updateOrderId(orderId);
        feNoteEntityManager.flush();
        feNoteEntityManager.clear();

        // then
        FePoint findFePoint = fePointRepository.findById(fePointId)
                .orElseThrow(RuntimeException::new);
        assertThat(findFePoint.getOrderid()).isEqualTo(orderId);
    }

}
