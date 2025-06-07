// Interface
public interface FuncaoHash {
    int hash(int chave, int tamanhoTabela);
}

// Implementação 1: Resto da Divisão
class HashDivisao implements FuncaoHash {
    @Override
    public int hash(int chave, int tamanhoTabela) {
        return chave % tamanhoTabela;
    }
}

// Implementação 2: Multiplicação
class HashMultiplicacao implements FuncaoHash {
    private static final double A = 0.6180339887; // Constante (conjugado da razão áurea)

    @Override
    public int hash(int chave, int tamanhoTabela) {
        double val = chave * A;
        val = val - (long) val; // Pega a parte fracionária
        return (int) (tamanhoTabela * val);
    }
}

// Implementação 3: Dobramento
class HashDobramento implements FuncaoHash {
    @Override
    public int hash(int chave, int tamanhoTabela) {
        // Divide a chave de 9 dígitos em 3 partes de 3 dígitos
        int p1 = chave / 1000000;
        int p2 = (chave / 1000) % 1000;
        int p3 = chave % 1000;

        int soma = p1 + p2 + p3;
        return soma % tamanhoTabela;
    }
}