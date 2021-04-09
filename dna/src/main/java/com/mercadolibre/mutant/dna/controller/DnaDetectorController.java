package com.mercadolibre.mutant.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutant.dna.model.dto.DnaDTO;
import com.mercadolibre.mutant.dna.service.contract.DnaDetection;

@RestController
public class DnaDetectorController {
	
	@Autowired
	private DnaDetection dnaDetection;
	
	@PostMapping("/mutant")
	public boolean detectMutantDna(@RequestBody DnaDTO dnaToAnalize) {
		return dnaDetection.analizeDna(dnaToAnalize);
	}

}
