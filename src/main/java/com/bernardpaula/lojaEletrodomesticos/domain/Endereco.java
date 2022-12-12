package com.bernardpaula.lojaEletrodomesticos.domain;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "numero")
	@NotEmpty(message = "O preenchimento do campo numero é obrigatório")
	private String numero;
	
	private String complemento;
	
	@Column(name = "bairro")
	@NotEmpty(message = "O preenchimento do campo bairro é obrigatório")
	@Length(min=3, max=40, message = "O campo bairro deve ter entre 3 e 40 caracteres")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
}
