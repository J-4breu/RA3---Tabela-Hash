import java.util.Objects;

public class Registro {
    private final int codigo;

    public Registro(int codigo) {
        // Garante que o código seja positivo para evitar problemas com o operador %
        this.codigo = Math.abs(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro registro = (Registro) o;
        return codigo == registro.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("%09d", codigo);
    }
}