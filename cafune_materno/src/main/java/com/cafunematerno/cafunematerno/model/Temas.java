package com.cafunematerno.cafunematerno.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_temas")
public class Temas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTema;
	
	@NotBlank
	@Size(min = 5, max = 45, message = "Entre 5 e 45 caracteres")
	private String nomeTema;
	

	
	@OneToMany(mappedBy = "temaPertencente", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"temaPertencente", "usuario"})
	private List<Postagens> post;
	
	public Temas() {
		
	}

	public List<Postagens> getPost() {
		return post;
	}

	public void setPost(List<Postagens> post) {
		this.post = post;
	}

	public Long getIdTema() {
		return idTema;
	}

	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}

	public String getNomeTema() {
		return nomeTema;
	}

	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}
	
}
