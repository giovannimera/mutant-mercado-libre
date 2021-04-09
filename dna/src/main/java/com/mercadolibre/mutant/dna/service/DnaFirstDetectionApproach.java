package com.mercadolibre.mutant.dna.service;

import static com.mercadolibre.mutant.dna.util.Utils.commonNullValidation;
import static com.mercadolibre.mutant.dna.util.Utils.concatStringVector;
import static com.mercadolibre.mutant.dna.util.Utils.equalCharacters;
import static com.mercadolibre.mutant.dna.util.Utils.isValidPosition;
import static com.mercadolibre.mutant.dna.util.Utils.isValidPositionWithVariant;
import static com.mercadolibre.mutant.dna.util.Utils.verifyCharacterOccurences;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant.dna.entity.DnaEntity;
import com.mercadolibre.mutant.dna.model.dto.DnaDTO;
import com.mercadolibre.mutant.dna.repository.DnaRepository;
import com.mercadolibre.mutant.dna.service.contract.DnaDetection;
import com.mercadolibre.mutant.dna.service.contract.DnaProcess;
import com.mercadolibre.mutant.dna.service.contract.DnaValidations;

@Service
public class DnaFirstDetectionApproach implements DnaDetection, DnaProcess, DnaValidations{
	
	@Autowired
	private DnaRepository dnaRepository;
	
	@Override
	public boolean analizeDna(DnaDTO dna) {
		boolean result = runDetectionProcess(dna);
		dnaRepository.save(new DnaEntity(Arrays.toString(dna.getDna()), result ? "Mutant" : "Human"));
		return result;
	}
	
	@Override
	public boolean runDetectionProcess(DnaDTO dna) {
		boolean horizontalResult = false;
		boolean verticalResult = false;
		boolean diagonalResult = false;
		
		if(!shouldWordsHaveSameLength(dna)) {
			return false;
		}
		
		String completeWord = concatStringVector(dna);
		
		horizontalResult = horizontalRowProcess(dna);
		if(!horizontalResult) {
			verticalResult = verticalRowProcess(completeWord, dna.getDna()[0].length());
		}else {
			return true;
		}
		if(!verticalResult) {
			diagonalResult = diagonalRowProcess(completeWord, dna.getDna()[0].length(), dna.getDna().length);
		}else {
			return true;
		}
		return diagonalResult;
	}

	@Override
	public boolean horizontalRowProcess(DnaDTO dna) {
		boolean result = true;
		for(String word : dna.getDna()) {
			int count = 0;
			int initialPosition = shouldStartHorizontalWordVerification(word);
			if(word.length() < 4 || initialPosition == -1) {
				result = false;
			} else if(verifyHorizontalWordLetters(word, initialPosition, initialPosition + 1, count)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean verifyHorizontalWordLetters(String word, int x0, int x1, int count) {
		if(count >= 3) {
			return true;
		}
		if(x0 < word.length() &&
				x1 < word.length() &&
				equalCharacters(word, x0, x1)) {
			count++;
			return verifyHorizontalWordLetters(word, x0 + 1, x1 + 1, count);
		} else if(x0 < word.length() &&
				x1 < word.length()) {
			count = 0;
			return verifyHorizontalWordLetters(word, x0 + 1, x1 + 1, count);
		}
		else {
			return false;
		}
	}

	@Override
	public int shouldStartHorizontalWordVerification(String word) {
		if(verifyCharacterOccurences(word, 'a', 4)) {
			return word.indexOf('a');
		}
		if(verifyCharacterOccurences(word, 't', 4)) {
			return word.indexOf('t');
		}
		if(verifyCharacterOccurences(word, 'c', 4)) {
			return word.indexOf('c');
		}
		if(verifyCharacterOccurences(word, 'g', 4)) {
			return word.indexOf('g');
		}
		return -1;
	}

	@Override
	public 	boolean verticalRowProcess(String fullRow, int wordLength) {
		int cont = 0;
		boolean result = false;
		for(int i = 0; i < wordLength; i++) {
			result = verifyVerticalWordLetters(fullRow, i, wordLength + i, wordLength, cont);
			if(result) {
				break;
			}
		}
		return result;
	}
	
	@Override
	public boolean verifyVerticalWordLetters(String word, int posX0, int posX1, int wordLength, int cont) {
		if(cont >= 3) {
			return true;
		}
		if(isValidPosition(word, posX0, wordLength)
				&& isValidPosition(word, posX1, wordLength)
				&& equalCharacters(word, posX0, posX1)) {
			cont++;
			return verifyVerticalWordLetters(word, posX0 + wordLength, posX1 + wordLength, wordLength, cont);
		}else if(isValidPosition(word, posX0) 
				&& isValidPosition(word, posX1)
				&& isValidPosition(word, posX0, wordLength)
				&& isValidPosition(word, posX1, wordLength)){
			cont = 0;
			return verifyVerticalWordLetters(word, posX0 + wordLength, posX1 + wordLength, wordLength, cont);
		}else {
			return false;
		}
	}
	
	@Override
	public boolean diagonalRowProcess(String fullRow, int wordLength, int wordCount) {
		int cont = 0;
		boolean resultYDiagonal = false;
		boolean resultXDiagonal = false;
		int diagonalVariant = 1;
		for(int i = 0; i < wordLength; i++) {
			resultYDiagonal = verifyDiagonalWordLetters(fullRow, i, wordLength + i + 1,
					wordLength, cont, diagonalVariant + i);
			if(resultYDiagonal) {
				break;
			}
		}
		if(!resultYDiagonal) {
			for(int i = 1; i < wordCount; i++) {
				resultXDiagonal = verifyDiagonalWordLetters(fullRow, i * wordLength,
						i * wordLength + wordLength + 1, wordLength, cont, diagonalVariant + i);
				if(resultXDiagonal) {
					break;
				}
			}
		}else {
			return true;
		}
		return resultXDiagonal;
	}
	
	@Override
	public boolean verifyDiagonalWordLetters(String word, int posX0, int posX1, int wordLength, int cont, int diagonalVariant) {
		if(cont >= 3) {
			return true;
		}
		if(isValidPosition(word, posX0)
				&& isValidPosition(word, posX1) 
				&& isValidPositionWithVariant(word, posX0, wordLength, diagonalVariant)
				&& isValidPositionWithVariant(word, posX1, wordLength, diagonalVariant)
				&& equalCharacters(word, posX0, posX1)) {
			cont++;
			diagonalVariant++;
			posX0 = posX1;
			posX1 = posX1 + wordLength + 1;
			return verifyDiagonalWordLetters(word, posX0, posX1, wordLength, cont, diagonalVariant);
		}else if(isValidPosition(word, posX0) 
				&& isValidPosition(word, posX1)
				&& isValidPositionWithVariant(word, posX0, wordLength, diagonalVariant)
				&& isValidPositionWithVariant(word, posX1, wordLength, diagonalVariant)){
			cont = 0;
			diagonalVariant++;
			posX0 = posX1;
			posX1 = posX1 + wordLength + 1;
			return verifyDiagonalWordLetters(word, posX0, posX1, wordLength, cont, diagonalVariant);
		}else {
			return false;
		}
	}

	@Override
	public boolean shouldWordsHaveSameLength(DnaDTO dna) {
		if(!commonNullValidation(dna)) {
			return false;
		}
		int firsWordLength = dna.getDna()[0].length();
		for(String word : dna.getDna()) {
			if(word.length() != firsWordLength) {
				return false;
			}
		}
		return true;
	}
	
}
