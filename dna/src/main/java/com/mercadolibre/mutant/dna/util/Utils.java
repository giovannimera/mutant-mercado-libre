package com.mercadolibre.mutant.dna.util;

import java.util.Optional;

import com.mercadolibre.mutant.dna.model.dto.DnaDTO;

public class Utils {
	
	public static boolean isValidPosition(String word, int posX, int wordLength) {
		return posX + wordLength < word.length();
	}
	
	public static boolean isValidPosition(String word, int posX) {
		return posX < word.length();
	}
	
	public static boolean isValidPositionWithVariant(String word, int posX, int wordLength, int variant) {
		return posX + wordLength - variant < word.length();
	}
	
	public static boolean equalCharacters(String word, int posX0, int posX1) {
		return word.charAt(posX0) == word.charAt(posX1);
	}
	
	public static boolean verifyCharacterOccurences(String word, char characterToVerify, int occurrences) {
		return word
				.toLowerCase()
				.chars()
				.filter(ch -> ch == characterToVerify)
				.count() >= occurrences;
	}
	
	public static String concatStringVector(DnaDTO dna) {
		StringBuilder strBuilder = new StringBuilder();
		for(String str : dna.getDna()) {
			strBuilder.append(str);
		}
		return strBuilder.toString();
	}
	
	public static boolean commonNullValidation(DnaDTO dna) {
		Optional<DnaDTO> optDna = Optional.ofNullable(dna);
		if(optDna.isPresent()) {
			Optional<String[]> optWordArray = Optional.ofNullable(dna.getDna());
			if(optWordArray.isPresent()) {
				Optional<String> optFirstWord = Optional.ofNullable(dna.getDna()[0]);
				if(optFirstWord.isPresent()) {
					return !optFirstWord.get().isEmpty();
				}
			}
		}
		return false;
	}
	
}
