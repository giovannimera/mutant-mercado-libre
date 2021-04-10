package com.mercadolibre.mutant.dna.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.mutant.dna.entity.DnaEntity;
import com.mercadolibre.mutant.dna.model.dto.DnaDTO;
import com.mercadolibre.mutant.dna.repository.DnaRepository;
import com.mercadolibre.mutant.dna.service.contract.DnaDetection;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DnaFirstDetectionApproachTest {

	@Autowired
	private DnaDetection approach;
	
	@MockBean
	private DnaRepository repository;
	
	@Test
	public void isNotMutantTest() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(new String[]{"atcgatcg","gctagcta", "atcgatcg", "ctgactga", "atctatcg", "ctgactga"});
		
		when(repository.save(any())).thenReturn(new DnaEntity("a", "b"));
		
		boolean response = approach.analizeDna(dna);
		assertFalse(response);
	}
	
	@Test
	public void isMutantHorizontalVerificationTest() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(new String[]{"atcgatcg","gctagcta", "atcgatcg", "ctgactga", "atctatcg", "ctccccga"});
		
		when(repository.save(any())).thenReturn(new DnaEntity("a", "b"));
		
		boolean response = approach.analizeDna(dna);
		assertTrue(response);
	}
	
	@Test
	public void isMutantVerticalVerificationTest() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(new String[]{"atcgatcg","gctagctg", "atcgatcg", "ctgactgg", "atctatcg", "ctgactgg"});
		
		when(repository.save(any())).thenReturn(new DnaEntity("a", "b"));
		
		boolean response = approach.analizeDna(dna);
		assertTrue(response);
	}
	
	@Test
	public void isMutantDiagonalVerificationTest() {
		DnaDTO dna = new DnaDTO();
		dna.setDna(new String[]{"atcgatcg","gatagcta", "atagatcg", "ctgactga", "atctatcg", "ctgacaga"});
		
		when(repository.save(any())).thenReturn(new DnaEntity("a", "b"));
		
		boolean response = approach.analizeDna(dna);
		assertTrue(response);
	}
	
}
