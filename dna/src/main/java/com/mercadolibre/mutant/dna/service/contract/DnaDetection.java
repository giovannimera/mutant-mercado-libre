package com.mercadolibre.mutant.dna.service.contract;

import com.mercadolibre.mutant.dna.model.dto.DnaDTO;

public interface DnaDetection {
	
	boolean analizeDna(DnaDTO dna);
	
	boolean runDetectionProcess(DnaDTO dna);

}
