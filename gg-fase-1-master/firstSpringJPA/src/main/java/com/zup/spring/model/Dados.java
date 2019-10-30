package com.zup.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dados {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String chave;
	


	private String valor;
	public Dados() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dados other = (Dados) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave)) {
			return false;
		}
		return true;
	}
	public Dados(String args) {
		this.valor = args;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}

	@Override
	public String toString() {
		return "Dados [id=" + id + ", chave=" + chave + ", valor=" + valor + "]";
	}
	
	

}
