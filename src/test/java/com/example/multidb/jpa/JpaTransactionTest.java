package com.example.multidb.jpa;

import com.example.multidb.domain.femarket.PdtStatic;
import com.example.multidb.domain.fenote.FePoint;
import com.example.multidb.repository.femarket.PdtStaticRepository;
import com.example.multidb.repository.fenote.FePointRepository;
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

    @Test
    @Transactional("feMarketTransactionManager")
    @Commit
    void pdtStaticTest() {
        PdtStatic.PdtStaticId pdtStaticId = new PdtStatic.PdtStaticId("20211108151833", "20211108151833-220209-000170");
        PdtStatic pdtStatic = pdtStaticRepository.findById(pdtStaticId).get();

        pdtStatic.setRegtm(LocalDateTime.now());
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
