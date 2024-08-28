package br.com.thais.TabelaFipe.principal;

import java.util.Scanner;
import java.util.stream.Collectors;
import br.com.thais.TabelaFipe.model.Dados;
import br.com.thais.TabelaFipe.model.DadosInfoVeiculo;
import br.com.thais.TabelaFipe.model.DadosMarca;
import br.com.thais.TabelaFipe.model.MarcaModeloAno;
import br.com.thais.TabelaFipe.model.Veiculo;
import br.com.thais.TabelaFipe.services.ConsumoApi;
import br.com.thais.TabelaFipe.services.ConverteDados;

public class Principal {
	Scanner leitura = new Scanner(System.in);
	private ConsumoApi consumoApi = new ConsumoApi();
	private ConverteDados conversor = new ConverteDados();
	private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

	public void exibeMenu() {
		try {
			var menu = """
					*** OPÇÕES ***
					Carro
					Moto
					Caminhão

					Digite uma das opções para consultar:
					""";
			System.out.println(menu);
			var opcao = leitura.nextLine();
			String endereco;

			if (opcao.toLowerCase().contains("carr")) {
				endereco = URL_BASE + "carros/marcas";
			} else if (opcao.toLowerCase().contains("mot")) {
				endereco = URL_BASE + "motos/marcas";
			} else {
				endereco = URL_BASE + "caminhoes/marcas";
			}

			var json = consumoApi.obterDados(endereco);
			var dadosConvertidos = conversor.obterLista(json, Dados.class);
			var listaMarcas = dadosConvertidos.stream().map(m -> new MarcaModeloAno(m.codigo(), m.nome())).toList();

			System.out.println("Marcas encontradas:");
			listaMarcas.forEach(System.out::println);

			menu = "Digite o código de uma das marcas para consultar os modelos: ";
			System.out.println("\n" + menu);
			opcao = leitura.nextLine();

			endereco = endereco + "/" + opcao + "/modelos";
			json = consumoApi.obterDados(endereco);
			var dadosModelosConvertidos = conversor.obterDados(json, DadosMarca.class);
			var listaModelos = dadosModelosConvertidos.modelos().stream()
					.map(m -> new MarcaModeloAno(m.codigo(), m.nome())).toList();

			if (listaModelos != null && !listaModelos.isEmpty()) {
				System.out.println("Modelos encontrados:");
				listaModelos.forEach(System.out::println);
			} else {
				System.out.println("Nenhum modelo encontrado.");
			}

			menu = "Digite um trecho do nome do modelo para filtrar: ";
			System.out.println("\n" + menu);
			var trecho = leitura.nextLine();

			var modelosFiltrados = listaModelos.stream()
					.filter(m -> m.getNome().toLowerCase().contains(trecho.toLowerCase())).collect(Collectors.toList());

			if (modelosFiltrados != null && !modelosFiltrados.isEmpty()) {
				System.out.println("Modelos filtrados:");
				modelosFiltrados.forEach(System.out::println);
			} else {
				System.out.println("Nenhum modelo encontrado por este filtro.");
			}

			menu = "Digite o código de um dos modelos para consultar os anos: ";
			System.out.println("\n" + menu);
			opcao = leitura.nextLine();

			var enderecoAnos = endereco + "/" + opcao + "/anos";
			json = consumoApi.obterDados(enderecoAnos);
			var dadosAnosConvertidos = conversor.obterLista(json, Dados.class);
			var listaAnos = dadosAnosConvertidos.stream().map(m -> new MarcaModeloAno(m.codigo(), m.nome())).toList();

			if (listaAnos != null && !listaAnos.isEmpty()) {
				System.out.println("Anos encontrados:");
				listaAnos.forEach(System.out::println);
			} else {
				System.out.println("Nenhum ano encontrado.");
			}

			menu = "Digite o código de um ano para consultar informações do veículo ou 'todos' para trazer todos: ";
			System.out.println("\n" + menu);
			opcao = leitura.nextLine();

			if (opcao.equalsIgnoreCase("todos")) {
				for (int i = 0; i < listaAnos.size(); i++) {
					var enderecoAnoEspecifico = enderecoAnos + "/" + listaAnos.get(i).getCodigo();
					imprimeInfoVeiculo(enderecoAnoEspecifico);
				}
			} else {
				var enderecoAnoEspecifico = enderecoAnos + "/" + opcao;
				imprimeInfoVeiculo(enderecoAnoEspecifico);
			}
		} catch (Exception e) {
			System.out.println("Você digitou um dado inválido. Busca encerrada.");
		}
	}

	private void imprimeInfoVeiculo(String endereco) {
		var json = consumoApi.obterDados(endereco);
		var infoVeiculoConvertida = conversor.obterDados(json, DadosInfoVeiculo.class);

		if (infoVeiculoConvertida != null) {
			var veiculo = new Veiculo(infoVeiculoConvertida.tipoVeiculo(), infoVeiculoConvertida.valor(),
					infoVeiculoConvertida.marca(), infoVeiculoConvertida.modelo(), infoVeiculoConvertida.anoModelo(),
					infoVeiculoConvertida.combustivel(), infoVeiculoConvertida.codigoFipe(),
					infoVeiculoConvertida.mesReferencia(), infoVeiculoConvertida.siglaCombustivel());
			System.out.println("\n" + veiculo);
		} else {
			System.out.println("Nenhum veículo encontrado.");
		}
	}
}
