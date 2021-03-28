package br.com.davidmachadosf.test_azul.service;

import java.util.List;

import br.com.davidmachadosf.test_azul.model.Cliente;

public interface IClienteService {

    List<Cliente> findAll();
}