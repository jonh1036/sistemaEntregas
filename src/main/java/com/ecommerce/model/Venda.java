package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venda {
	
	@Id
	@GeneratedValue
	private Integer idVenda;
	
	@Column(nullable = false)
	private String data;
	
	@Column(nullable = false)
	private String nomeComprador;
	
	@Column(nullable = false)
	private String cartaoComprador;
	
	@Column(nullable = false)
	private String codSegurancaComprador;
	
	@Column(nullable = false)
	private Double valor;
	
	private Integer idProduto;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Venda() {
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Venda(Integer idVenda, String data, String nomeComprador, String cartaoComprador,
			String codSegurancaComprador, Double valor, Integer idProduto) {
		this.idVenda = idVenda;
		this.data = data;
		this.nomeComprador = nomeComprador;
		this.cartaoComprador = cartaoComprador;
		this.codSegurancaComprador = codSegurancaComprador;
		this.valor = valor;
		this.idProduto = idProduto;
	}

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

	public String getCartaoComprador() {
		return cartaoComprador;
	}

	public void setCartaoComprador(String cartaoComprador) {
		this.cartaoComprador = cartaoComprador;
	}

	public String getCodSegurancaComprador() {
		return codSegurancaComprador;
	}

	public void setCodSegurancaComprador(String codSegurancaComprador) {
		this.codSegurancaComprador = codSegurancaComprador;
	}

	@Override
	public String toString() {
		return "Venda{" + "idVenda=" + idVenda + ", data=" + data + ", nomeComprador=" + nomeComprador
				+ ", cartaoComprador=" + cartaoComprador + ", codSegurancaComprador=" + codSegurancaComprador
				+ ", valor=" + valor + ", idProduto=" + idProduto + '}';
	}

}
