package com.bernardpaula.lojaEletrodomesticos.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.bernardpaula.lojaEletrodomesticos.domain.enums.Perfil;
import com.bernardpaula.lojaEletrodomesticos.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(name = "nome")
	@Length(min=1, max=30, message = "O tamanho do nome deve estar entre 1 e 30 caracteres")
	private String nome;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "cpf_ou_cnpj")
	private String cpfOuCnpj;
	
	@Column(name = "tipo_cliente")
	private Integer tipoCliente;
	
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	Set<Integer> perfis = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "telefones")
	private Set<String> telefones = new HashSet<>();
	
	
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		addPerfil(Perfil.CLIENTE);
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = (tipo == null) ? null : tipo.getCod();
	}
	


	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	
	
}
