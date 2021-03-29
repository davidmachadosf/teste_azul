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
import br.com.davidmachadosf.test_azul.model.Endereco;

@Repository
@RepositoryRestController
@RepositoryRestResource(collectionResourceRel = "enderecos", path = "enderecos")
public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, String> {
	
	@RolesAutorizados({VIEW})
	List<Cliente> findByLogradouroContainingIgnoreCaseOrderByLogradouro(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByBairroContainingIgnoreCaseOrderByBairro(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByLocalidadeContainingIgnoreCaseOrderByLocalidade(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByUfIgnoreCase(@Param("uf") String uf);
	@RolesAutorizados({VIEW})
	List<Cliente> findByCep(@Param("cep") String cep);
}