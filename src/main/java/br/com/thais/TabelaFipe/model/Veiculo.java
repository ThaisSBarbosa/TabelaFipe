package br.com.thais.TabelaFipe.model;

public class Veiculo {
	private Integer tipoVeiculo;
	private String valor;
	private String marca;
	private String modelo;
	private Integer anoModelo;
	private String combustivel;
	private String codigoFipe;
	private String mesReferencia;
	private String siglaCombustivel;
	
	public Veiculo(Integer tipoVeiculo, String valor, String marca, String modelo, Integer anoModelo,
			String combustivel, String codigoFipe, String mesReferencia, String siglaCombustivel) {
		this.tipoVeiculo = tipoVeiculo;
		this.valor = valor;
		this.marca = marca;
		this.modelo = modelo;
		this.anoModelo = anoModelo;
		this.combustivel = combustivel;
		this.codigoFipe = codigoFipe;
		this.mesReferencia = mesReferencia;
		this.siglaCombustivel = siglaCombustivel;
	}
	
	public Integer getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(Integer tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getCodigoFipe() {
		return codigoFipe;
	}
	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}
	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}
	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}
	@Override
	public String toString() {
		return "Tipo Veículo: " + tipoVeiculo + "\nValor: " + valor + "\nMarca: " + marca + "\nModelo: " + modelo
				+ "\nAno do Modelo: " + anoModelo + "\nCombustivel: " + combustivel + "\nCódigo Fipe: " + codigoFipe
				+ "\nMês de Referência: " + mesReferencia + "\nSigla do Combustível: " + siglaCombustivel;
	}
}
