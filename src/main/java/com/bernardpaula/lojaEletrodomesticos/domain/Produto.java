package com.bernardpaula.lojaEletrodomesticos.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String marca;
	
	private String descricao;
	
	private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
			joinColumns = @JoinColumn(name = "Produto_id"),
			inverseJoinColumns = @JoinColumn(name = "Categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private Set<ItemPedido> itens = new HashSet<>();

	
}
