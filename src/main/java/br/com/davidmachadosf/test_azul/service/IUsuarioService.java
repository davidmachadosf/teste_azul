package br.com.davidmachadosf.test_azul.service;

import java.util.List;

import br.com.davidmachadosf.test_azul.model.Usuario;

public interface IUsuarioService {

    List<Usuario> findAll();
}