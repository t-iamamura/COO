import java.io.IOException;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        // --- CARREGAMENTO DOS DADOS ---
        List<Produto> produtos = CarregadorDeProdutos.carregar("produtos.csv");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto foi carregado. Verifique o arquivo produtos.csv.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        FiltroStrategy filtro = null;
        Comparator<Produto> criterio = null;
        AlgoritmoOrdenacao algoritmo = null;

        try {
            // --- MENU 1: ESCOLHA DO FILTRO ---
            System.out.println("Escolha o CRITERIO DE FILTRAGEM:");
            System.out.println("1: Todos os produtos");
            System.out.println("2: Filtrar por Categoria");
            System.out.println("3: Filtrar por Estoque (menor ou igual a)");
            System.out.println("4: Filtrar por Faixa de Preco");
            System.out.println("5: Filtrar por Substring na Descricao");
            System.out.print("Digite sua opcao de filtro: ");

            int opcaoFiltro = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha pendente

            switch (opcaoFiltro) {
                case 1:
                    filtro = new FiltroTodos();
                    break;
                case 2:
                    System.out.print("Digite a categoria: ");
                    String categoria = scanner.nextLine();
                    filtro = new FiltroCategoriaIgualA(categoria);
                    break;
                case 3:
                    System.out.print("Digite a quantidade maxima de estoque: ");
                    int qtd = scanner.nextInt();
                    filtro = new FiltroEstoqueMenorIgualA(qtd);
                    break;
                case 4:
                    System.out.print("Digite o preco minimo: ");
                    double min = scanner.nextDouble();
                    System.out.print("Digite o preco maximo: ");
                    double max = scanner.nextDouble();
                    filtro = new FiltroPrecoDentroDeFaixa(min, max);
                    break;
                case 5:
                    System.out.print("Digite a substring da descricao: ");
                    String sub = scanner.nextLine();
                    filtro = new FiltroDescricaoContemSubstring(sub);
                    break;
                default:
                    System.out.println("Opcao invalida! Usando filtro padrao ('Todos').");
                    filtro = new FiltroTodos();
                    break;
            }

            // --- MENU 2: ESCOLHA DO CRITÉRIO DE ORDENAÇÃO ---
            System.out.println("\nEscolha o CRITERIO DE ORDENACAO:");
            System.out.println("1: Preco (crescente)");
            System.out.println("2: Preco (decrescente)");
            System.out.println("3: Descricao (crescente)");
            System.out.println("4: Descricao (decrescente)");
            System.out.println("5: Estoque (crescente)");
            System.out.println("6: Estoque (decrescente)");
            System.out.print("Digite sua opcao de ordenacao: ");

            int opcaoOrdenacao = scanner.nextInt();

            switch (opcaoOrdenacao) {
                case 1:
                    criterio = new CriterioPrecoCrescente();
                    break;
                case 2:
                    criterio = new CriterioPrecoDecrescente();
                    break;
                case 3:
                    criterio = new CriterioDescricaoCrescente();
                    break;
                case 4:
                    criterio = new CriterioDescricaoDecrescente();
                    break;
                case 5:
                    criterio = new CriterioEstoqueCrescente();
                    break;
                case 6:
                    criterio = new CriterioEstoqueDecrescente();
                    break;
                default:
                    System.out.println("Opcao invalida! Usando ordenacao padrao (Descricao crescente).");
                    criterio = new CriterioDescricaoCrescente();
                    break;
            }

            // --- MENU 3: ESCOLHA DO ALGORITMO DE ORDENAÇÃO ---
            System.out.println("\nEscolha o ALGORITMO DE ORDENACAO:");
            System.out.println("1: QuickSort");
            System.out.println("2: InsertionSort");
            System.out.print("Digite sua opcao de algoritmo: ");

            int opcaoAlgoritmo = scanner.nextInt();

            switch (opcaoAlgoritmo) {
                case 1:
                    algoritmo = new QuickSort();
                    break;
                case 2:
                    algoritmo = new InsertionSort();
                    break;
                default:
                    System.out.println("Opcao invalida! Usando algoritmo padrao (QuickSort).");
                    algoritmo = new QuickSort();
                    break;
            }

        } catch (InputMismatchException e) {
            System.err.println("Entrada invalida! O programa sera encerrado.");
            scanner.close();
            return; // Encerra o programa em caso de erro de digitação
        }

        // --- GERAÇÃO DO RELATÓRIO ---
        try {
            System.out.println("\nGerando relatorio com as opcoes escolhidas...");
            GeradorDeRelatorios gerador = new GeradorDeRelatorios(produtos, algoritmo, criterio, filtro);
            gerador.gerarRelatorio("relatorio.html");
            System.out.println("Relatorio gerado com sucesso em relatorio.html!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o relatorio: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}