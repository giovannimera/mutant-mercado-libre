package com.mercadolibre.mutant.dna.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DnaEntity {
	
	@Id
	@GeneratedValue
	private BigDecimal id;
	@Column
	private String dna;
	@Column
	private String classification;
	
	public DnaEntity(String dna, String classification) {
		super();
		this.dna = dna;
		this.classification = classification;
	}
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
}
