package br.com.thais.TabelaFipe.model;

public class MarcaModeloAno {
	private String codigo;
	private String nome;
	
	public MarcaModeloAno(String codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Código: " + codigo + ", Nome: " + nome;
	}	
}
