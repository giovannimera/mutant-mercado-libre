package com.mercadolibre.mutant.dna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.mutant.dna.model.dto.DnaStatisticsDTO;
import com.mercadolibre.mutant.dna.service.contract.DnaStatistic;

@RestController
public class DnaStatisticsController {
	
	@Autowired
	private DnaStatistic dnaStatistics;
	
	@GetMapping("/stats")
	public DnaStatisticsDTO obtainDnaStatistics() {
		return dnaStatistics.calculateStatistics();
	}

}
