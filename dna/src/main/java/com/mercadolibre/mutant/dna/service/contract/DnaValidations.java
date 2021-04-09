package com.mercadolibre.mutant.dna.service.contract;

import com.mercadolibre.mutant.dna.model.dto.DnaDTO;

public interface DnaValidations {

	boolean shouldWordsHaveSameLength(DnaDTO dna);
	
}
