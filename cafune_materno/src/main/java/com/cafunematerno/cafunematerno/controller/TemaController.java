package com.cafunematerno.cafunematerno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafunematerno.cafunematerno.model.Temas;
import com.cafunematerno.cafunematerno.model.Usuarios;
import com.cafunematerno.cafunematerno.service.TemasService;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemasService serviceTemas;
	

	@GetMapping
	public ResponseEntity<List<Temas>> getAll(){
		return serviceTemas.pegarTodosTemas();
	}
	
	@GetMapping("/id/{id_tema}")
	public ResponseEntity<Temas> buscarTemaPorId(@PathVariable(value = "id_tema") Long idTema) {
		return serviceTemas.procurarIdTemas(idTema);
	}
	
	@PostMapping("/salvar/usuario/{id_usuario}")
	public ResponseEntity<Temas> salvarNovoTema(@PathVariable(value = "id_usuario") Long idUsuario, @RequestBody Temas novoTema) {
		return serviceTemas.salvarTemas(idUsuario, novoTema);
	}
	
	@PutMapping("/atualizar/{id_tema}")
	public ResponseEntity<Temas> alterarTema(@PathVariable (value = "id_tema") Long idTema, @RequestBody Temas TemaAtualizado) {
		return serviceTemas.atualizarTema(idTema, TemaAtualizado);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<String> deletarTemaAtravesDoId(@RequestParam Long idTema) {
		return serviceTemas.deletarIdTema(idTema);
	}
}
