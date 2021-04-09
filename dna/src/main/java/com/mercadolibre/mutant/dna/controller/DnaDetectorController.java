package com.mercadolibre.mutant.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> detectMutantDna(@RequestBody DnaDTO dnaToAnalize) {
		if(dnaDetection.analizeDna(dnaToAnalize)) {
			return new ResponseEntity<>("Is a mutant!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Is a human!", HttpStatus.FORBIDDEN);
		}
	}

}
