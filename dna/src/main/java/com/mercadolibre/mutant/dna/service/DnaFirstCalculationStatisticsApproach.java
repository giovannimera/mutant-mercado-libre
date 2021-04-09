package com.mercadolibre.mutant.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant.dna.model.dto.DnaStatisticsDTO;
import com.mercadolibre.mutant.dna.repository.DnaRepository;
import com.mercadolibre.mutant.dna.service.contract.DnaStatistic;

@Service
public class DnaFirstCalculationStatisticsApproach implements DnaStatistic {

	@Autowired
	private DnaRepository dnaRepository;
	
	@Override
	public DnaStatisticsDTO calculateStatistics() {
		double mutantCount = dnaRepository.countByClassification("Mutant");
		double humanCount = dnaRepository.countByClassification("Human");
		double totalCount = mutantCount + humanCount;
		double ratio =  0;
		if(humanCount > 0) {
			ratio = (mutantCount * 100 / totalCount) / 100;
		}else if(mutantCount > 0){
			ratio =  1;
		}
		return new DnaStatisticsDTO(mutantCount, humanCount, ratio);
	}

}
