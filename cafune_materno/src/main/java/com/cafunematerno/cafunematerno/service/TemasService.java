package com.cafunematerno.cafunematerno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafunematerno.cafunematerno.model.Temas;
import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.repository.TemaRepository;
import com.cafunematerno.cafunematerno.repository.UsuariosRepository;

@Service
public class TemasService {

	@Autowired
	private TemaRepository repositoryTemas;

	@Autowired
	private UsuariosRepository repositoryUsuarios;
	
	/**
	 * Método utilizado para acessar a lista de temas no BD.
	 * 
	 * @return ResponseEntity com status No Content caso a lista esteja vazia ou
	 * 		ResponseEntity com status Ok, caso existam temas nesta lista.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<List<Temas>> pegarTodosTemas() {
		List<Temas> listaDeTemas = repositoryTemas.findAll();
		if (listaDeTemas.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeTemas);
		}
	}
	
	/**
	 * Método utilizado para acessar um tema no BD a partir de seu Id.
	 * 
	 * @param id
	 * @return ResponseEntity com status Ok, caso exista o Id no BD,
	 * 			e ResponseEntity com status No Content caso não encontre 
	 * 			um Id no BD e esteja vazia.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<Temas> procurarIdTemas(Long id) {
		return repositoryTemas.findById(id).map(existeIdTemas -> ResponseEntity.status(200).body(existeIdTemas))
				.orElse(ResponseEntity.status(204).build());
	}
	
	/**
	 * Método utilizado para cadastrar um novo tema no sistema, validando se
	 * existe um grupo cadastrado no sistema.
	 * 
	 * @param idUsuario
	 * @param novoTema
	 * @return ResponseEntity com status Created com a entidade Tema ou
	 * 			ResponseEntity com status Not Acceptable, caso não exista um tema
	 *         	exista.
	 * @since 1.1
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<Temas> salvarTemas(Long idUsuario, Temas novoTema) {
		Optional<Usuarios> verificaUsuario = repositoryUsuarios.findById(idUsuario);
		Optional<Object> verificaTemas = repositoryTemas.findByNomeTema(novoTema.getNomeTema());
		
		if (verificaUsuario.isPresent() && verificaTemas.isEmpty()) {
			return ResponseEntity.status(201).body(repositoryTemas.save(novoTema));
		} else {
			return ResponseEntity.status(406).build();
		}	
	}
		
	
	/**
	 * Método utilizado para verificar se existe o Id do temas no BD se esse id é existente.
	 * 
	 * @param idTema
	 * @param temaAtualizado
	 * @return ResponseEntity com status Not Modified caso não encontre um Id no BD,
	 * 			ou ResponseEntity com status Accepted, alterando as informações do temas
	 * 			selecionado no BD.
	 * @since 1.0
	 * @author Grupo: Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<Temas> atualizarTema(Long idTema, Temas temaAtualizado) {
		Optional<Temas> idTemaJaExiste = repositoryTemas.findById(idTema);

		if (idTemaJaExiste.isPresent()) {
			idTemaJaExiste.get().setNomeTema(temaAtualizado.getNomeTema());
			return ResponseEntity.status(202).body(repositoryTemas.save(idTemaJaExiste.get()));
		} else {
			return ResponseEntity.status(304).build();
		}
	}
	
	/**
	 * Deleta um tema a partir do Id, caso exista.
	 * @param idTema
	 * @return Retorna um status 400 caso não exista, ou deleta o tema caso exista.
	 * @since 1.0
	 * @author Grupo:Angelo, Ellen, Julio, Luciano e Nathalia.
	 */

	public ResponseEntity<String> deletarIdTema(Long idTema) {
		Optional<Temas> idTemaExistente = repositoryTemas.findById(idTema);

		if (idTemaExistente.isEmpty()) {
			return ResponseEntity.status(400).body("Tema não localizado. Por favor tente outra");
		} else {
			repositoryTemas.deleteById(idTema);
			return ResponseEntity.status(200).body("Tema deletado com sucesso!");
		}
	}
}
