package curso.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@GetMapping(value = "listatodos")
	@ResponseBody //retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listaUsuario(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);//Retona a lista em Json
		
	}
	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "delete")
	 @ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long idUser ){
		
		usuarioRepository.deleteById(idUser);
		
		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
		
	}
	
	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?>atualizar(@RequestBody Usuario usuario){
		
		if(usuario.getId() == null)
			
			return new ResponseEntity<String>("O ID não foi informado para a atualização", HttpStatus.OK);
	   
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	
	}
	
	@GetMapping(value = "buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario>buscaruserid(@RequestParam(name ="iduser") Long iduser){
		
		Usuario usuario = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>>buscarPorNome(@RequestParam(name = "name") String name){
		
		List<Usuario> usuario = usuarioRepository.bsucarPorNome(name.trim().toUpperCase());
		
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
		
	}

}
