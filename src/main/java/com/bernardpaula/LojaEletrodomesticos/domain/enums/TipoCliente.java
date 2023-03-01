package com.bernardpaula.LojaEletrodomesticos.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

	
	PESSOAFISICA(1, "pessoa_fisica"),
	PESSOAJURIDICA(2, "pessoa_juridica");
	
	private Integer cod;
	private String descricao;
	
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
	
}
