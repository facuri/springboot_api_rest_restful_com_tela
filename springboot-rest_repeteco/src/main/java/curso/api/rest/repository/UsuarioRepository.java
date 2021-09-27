package curso.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//Pesquisar por Nome
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario>bsucarPorNome(String nome);

}
