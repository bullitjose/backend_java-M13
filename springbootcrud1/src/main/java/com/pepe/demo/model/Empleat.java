package com.pepe.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Empleat {
	
	public enum Feina{
		DIRECTORA(50000),MANAGER(45000),ENGINYER(40000),SECRETARI(35000),TECNIC(30000),OPERARI(28000);
		@Column
		int value;
		Feina(int value) {
			this.value=value;
		}
		public int getValue() {
			return value;
		}
	
	}
	
	
	
    @Id
    @GeneratedValue
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    Long id;
    @Column
    String nom;
    @Column
    Feina feina;
	int sou;
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Feina getFeina() {
		return feina;
	}
	public void setFeina(Feina feina) {
		this.feina = feina;
	}

	public int getSou() {
		return this.feina.value;
	}

	public void setSou(int sou) {
		this.sou = sou;
	}

	

}
