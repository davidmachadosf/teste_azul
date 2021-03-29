package br.com.davidmachadosf.test_azul.repository;
import static br.com.davidmachadosf.test_azul.model.enums.RoleType.VIEW;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_azul.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_azul.model.Cliente;

@Repository
@RepositoryRestController
@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {
	
	@RolesAutorizados({VIEW})
	Cliente findNomeByCpf(@Param("cpf") String cpf);
	
	@RolesAutorizados({VIEW})
	Cliente findNomeByEmail(@Param("email") String email);
	
	
	@RolesAutorizados({VIEW})
	List<Cliente> findByNomeIgnoreCase(@Param("nome") String nome);
	@RolesAutorizados({VIEW})
	List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(@Param("busca") String busca);
}