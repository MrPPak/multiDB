package com.example.multidb.repository.fenote;

import com.example.multidb.domain.fenote.FePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FePointRepository extends JpaRepository<FePoint, FePoint.FePointId>, FePointRepositoryCustom {
}
