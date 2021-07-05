package com.cafunematerno.cafunematerno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafunematerno.cafunematerno.model.Temas;


@Repository
public interface TemaRepository extends JpaRepository<Temas, Long> {
	
	public Optional<Object> findByNomeTema(String nomeTema);
	
	public List<Temas> findAllByNomeTemaContaining(String nomeTema);
	
	//@Query(value= "SELECT * FROM db_cafune_materno.tb_grupos WHERE id_grupo = :id", nativeQuery = true)
	//public List<Grupos> pegarOqueEuQuiser(@Param("id") Long id);
}
