package com.mercadolibre.mutant.dna.model.dto;

public class DnaStatisticsDTO {

	private double mutantCount;
	private double humanCount;
	private double ratio;
	
	public DnaStatisticsDTO(double mutantCount, double humanCount, double ratio) {
		super();
		this.mutantCount = mutantCount;
		this.humanCount = humanCount;
		this.ratio = ratio;
	}
	public double getMutantCount() {
		return mutantCount;
	}
	public void setMutantCount(double mutantCount) {
		this.mutantCount = mutantCount;
	}
	public double getHumanCount() {
		return humanCount;
	}
	public void setHumanCount(double humanCount) {
		this.humanCount = humanCount;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
}
