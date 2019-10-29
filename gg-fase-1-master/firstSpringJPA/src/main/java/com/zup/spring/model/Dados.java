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
	private String valor;
	public Dados() {
		
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
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
		if (valor != other.getValor())
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else
			if (!valor.equals(other.valor))
				return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dados [id=" + id + ", valor=" + valor + "]";
	}

}
