package com.mercadolibre.mutant.dna.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadolibre.mutant.dna.entity.DnaEntity;

public interface DnaRepository extends JpaRepository<DnaEntity, BigDecimal>{

	long countByClassification(String classification);
	
}
