package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long codigo;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String urlImg;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Produto(Long codigo, String nome, Double valor, String descricao) {
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}

	public Produto() {
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

}
