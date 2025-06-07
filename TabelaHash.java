import java.util.LinkedList;

public class TabelaHash {
    private final LinkedList<Registro>[] tabela;
    private final int tamanho;
    private final FuncaoHash funcaoHash;
    private long colisoes = 0;
    private long comparacoesBusca = 0;

    @SuppressWarnings("unchecked")
    public TabelaHash(int tamanho, FuncaoHash funcao) {
        this.tamanho = tamanho;
        this.funcaoHash = funcao;
        this.tabela = new LinkedList[tamanho];
        // Inicializa todas as listas da tabela
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    public void inserir(Registro registro) {
        int indice = funcaoHash.hash(registro.getCodigo(), this.tamanho);

        // Verifica se a lista nesse índice já tem elementos (ocorrência de colisão)
        if (!tabela[indice].isEmpty()) {
            colisoes++;
        }

        tabela[indice].add(registro);
    }

    public Registro buscar(int codigo) {
        int indice = funcaoHash.hash(codigo, this.tamanho);
        LinkedList<Registro> bucket = tabela[indice];

        for (Registro registro : bucket) {
            comparacoesBusca++; // Incrementa a cada verificação
            if (registro.getCodigo() == codigo) {
                return registro;
            }
        }
        return null; // Não encontrado
    }

    public long getColisoes() {
        return colisoes;
    }

    public long getComparacoesBusca() {
        return comparacoesBusca;
    }

    public void resetarContadores() {
        this.colisoes = 0;
        this.comparacoesBusca = 0;
    }
}