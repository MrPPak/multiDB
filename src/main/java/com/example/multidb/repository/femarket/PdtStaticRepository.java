package com.example.multidb.repository.femarket;

import com.example.multidb.domain.femarket.PdtStatic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdtStaticRepository extends JpaRepository<PdtStatic, PdtStatic.PdtStaticId>, PdtStaticRepositoryCustom {
}
