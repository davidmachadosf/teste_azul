package br.com.davidmachadosf.test_azul.model.cv;


public class Curriculum {
	
	String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Curriculum [titulo=" + titulo + "]";
	}

}
