import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnalisadorHash {

    private static final int[] TAMANHOS_TABELA = {10_000, 100_000, 1_000_000};
    private static final int[] TAMANHOS_DADOS = {1_000_000, 5_000_000, 20_000_000};
    private static final long[] SEEDS = {42L, 84L, 126L};
    private static final int QTD_BUSCAS = 1000;

    public static void main(String[] args) {
        FuncaoHash[] funcoes = {new HashDivisao(), new HashMultiplicacao(), new HashDobramento()};

        System.out.println("Tamanho Tabela;Funcao Hash;Tamanho Dados;Tempo Insercao (ms);Colisoes;Tempo Busca (ms);Comparacoes");

        for (int tamTabela : TAMANHOS_TABELA) {
            for (FuncaoHash funcao : funcoes) {
                for (int i = 0; i < TAMANHOS_DADOS.length; i++) {
                    int tamDados = TAMANHOS_DADOS[i];
                    long seed = SEEDS[i];

                    // 1. Geração de Dados
                    List<Registro> dados = gerarDados(tamDados, seed);
                    List<Registro> amostraBusca = extrairAmostra(dados, QTD_BUSCAS, seed);

                    // 2. Inserção
                    TabelaHash tabela = new TabelaHash(tamTabela, funcao);

                    long inicioInsercao = System.nanoTime();
                    for (Registro r : dados) {
                        tabela.inserir(r);
                    }
                    long fimInsercao = System.nanoTime();
                    long tempoInsercao = (fimInsercao - inicioInsercao) / 1_000_000;

                    // ***** INÍCIO DA CORREÇÃO *****
                    // Captura o número de colisões ANTES de zerar os contadores.
                    long colisoesInsercao = tabela.getColisoes();

                    // 3. Busca
                    tabela.resetarContadores(); // Agora só afeta o contador de busca

                    long inicioBusca = System.nanoTime();
                    for (Registro r : amostraBusca) {
                        tabela.buscar(r.getCodigo());
                    }
                    long fimBusca = System.nanoTime();
                    long tempoBusca = (fimBusca - inicioBusca) / 1_000_000;
                    long comparacoesBusca = tabela.getComparacoesBusca();

                    // 4. Apresentação dos Resultados
                    String nomeFuncao = funcao.getClass().getSimpleName();
                    // Usa a variável que salvou o valor das colisões.
                    System.out.printf("%d;%s;%d;%d;%d;%d;%d\n",
                            tamTabela,
                            nomeFuncao,
                            tamDados,
                            tempoInsercao,
                            colisoesInsercao, // Variável corrigida aqui
                            tempoBusca,
                            comparacoesBusca); // Variável corrigida aqui
                    // ***** FIM DA CORREÇÃO *****
                }
            }
        }
    }

    private static List<Registro> gerarDados(int quantidade, long seed) {
        List<Registro> dados = new ArrayList<>(quantidade);
        Random random = new Random(seed);
        for (int i = 0; i < quantidade; i++) {
            int codigo = 100_000_000 + random.nextInt(900_000_000);
            dados.add(new Registro(codigo));
        }
        return dados;
    }

    private static List<Registro> extrairAmostra(List<Registro> dados, int tamanhoAmostra, long seed) {
        List<Registro> amostra = new ArrayList<>(tamanhoAmostra);
        Random random = new Random(seed);
        for (int i = 0; i < tamanhoAmostra; i++) {
            amostra.add(dados.get(random.nextInt(dados.size())));
        }
        return amostra;
    }
}