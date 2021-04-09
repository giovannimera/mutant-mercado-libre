package com.mercadolibre.mutant.dna.service.contract;

import com.mercadolibre.mutant.dna.model.dto.DnaDTO;

public interface DnaProcess {
	
	boolean horizontalRowProcess(DnaDTO dna);
	
	boolean verifyHorizontalWordLetters(String word, int x0, int x1, int count);
	
	int shouldStartHorizontalWordVerification(String word);
	
	boolean verticalRowProcess(String row, int wordLength);

	boolean verifyVerticalWordLetters(String word, int posX0, int posX1, int wordLength, int cont);

	boolean diagonalRowProcess(String row, int wordLength, int wordCount);
	
	boolean verifyDiagonalWordLetters(String word, int posX0, int posX1, int wordLength, int cont, int diagonalVariant);
	

}
