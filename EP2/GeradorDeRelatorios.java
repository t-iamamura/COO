import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class GeradorDeRelatorios {

	private List<Produto> produtos;
	private AlgoritmoOrdenacao algoritmoOrdenacao;
	private Comparator<Produto> criterioOrdenacao;
	private FiltroStrategy filtroStrategy;

	/**
	 * Construtor que recebe a lista de produtos e as estratégias a serem usadas.
	 */
	public GeradorDeRelatorios(List<Produto> produtos, AlgoritmoOrdenacao algoritmo, Comparator<Produto> criterio, FiltroStrategy filtro) {
		this.produtos = produtos;
		this.algoritmoOrdenacao = algoritmo;
		this.criterioOrdenacao = criterio;
		this.filtroStrategy = filtro;
	}

	/**
	 * Orquestra a geração do relatório, aplicando as estratégias e salvando em um arquivo.
	 * @param arquivoSaida O nome do arquivo HTML a ser gerado.
	 */
	public void gerarRelatorio(String arquivoSaida) throws IOException {

		// 1. Filtrar os produtos usando a estratégia de filtro
		List<Produto> produtosFiltrados = new ArrayList<>();
		for (Produto produto : this.produtos) {
			if (filtroStrategy.verificar(produto)) {
				produtosFiltrados.add(produto);
			}
		}

		// 2. Ordenar a lista de produtos filtrados usando as estratégias de ordenação
		algoritmoOrdenacao.ordenar(produtosFiltrados, criterioOrdenacao);

		// 3. Gerar o conteúdo HTML
		String html = gerarHtml(produtosFiltrados);

		// 4. Salvar o HTML em um arquivo
		try (PrintWriter out = new PrintWriter(arquivoSaida)) {
			out.println(html);
		}
	}

	/**
	 * Gera o código HTML a partir de uma lista de produtos.
	 * @param produtosParaRelatorio A lista de produtos a ser incluída no relatório.
	 * @return Uma string contendo o relatório em formato HTML.
	 */

	private String gerarHtml(List<Produto> produtosParaRelatorio) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>Relatorio de Produtos</title></head><body>");
		sb.append("<h1>Relatorio de Produtos</h1>");
		sb.append("<table border=\"1\" style=\"width:100%; border-collapse: collapse;\"><tr>");

		sb.append("<th style=\"padding: 8px; text-align: left;\">Descricao</th>");
		sb.append("<th style=\"padding: 8px; text-align: left;\">Preco (R$)</th>");
		sb.append("<th style=\"padding: 8px; text-align: left;\">Estoque</th>");
		sb.append("<th style=\"padding: 8px; text-align: left;\">Categoria</th>");
		sb.append("</tr>");

		for (Produto p : produtosParaRelatorio) {
			sb.append("<tr>");

			String descricaoFormatada;

			// Verifica se o produto 'p' da lista é um objeto decorado
			if (p instanceof ProdutoDecorator) {
				// Se for, chama o método especial de formatação dos decoradores
				descricaoFormatada = ((ProdutoDecorator) p).formataParaImpressao();
			} else {
				// Se for um ProdutoPadrao simples, apenas pega a descrição normal
				descricaoFormatada = p.getDescricao();
			}

			// Usa a descrição (formatada ou não) na primeira célula
			sb.append("<td style=\"padding: 8px;\">").append(descricaoFormatada).append("</td>");

			// As outras colunas usam os getters normais
			sb.append("<td style=\"padding: 8px;\">").append(String.format("%.2f", p.getPreco())).append("</td>");
			sb.append("<td style=\"padding: 8px;\">").append(p.getQtdEstoque()).append("</td>");
			sb.append("<td style=\"padding: 8px;\">").append(p.getCategoria()).append("</td>");
			sb.append("</tr>");
		}

		sb.append("</table></body></html>");
		return sb.toString();
	}
}