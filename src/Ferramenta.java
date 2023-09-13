import java.util.ArrayList;
import java.util.List;

public class Ferramenta extends Categoria {
    // variaveis
    private String nome;
    private String descricao;
    private List<Categoria> categorias = new ArrayList<Categoria>();

    // construtores
    public Ferramenta() {
    }

    public Ferramenta(String _nome, String _descricao) {
        this.nome = _nome;
        this.descricao = _descricao;
    }

    public Ferramenta(String _nome, String _descricao, String _categoria) {
        this.nome = _nome;
        this.descricao = _descricao;
        this.setCategoriaNome(_categoria);
    }

    // encapsulamento
    public String getNome() {
        return this.nome;
    }
    public void setNome(String _nome) {
        this.nome = _nome;
    }
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String _descricao) {
        this.descricao = _descricao;
    }
    public List<Categoria> getCategorias() {
        return this.categorias;
    }
    public void setCategorias(List<Categoria> _categorias) {
        this.categorias = _categorias;
    }

    // metodos
    @Override
    public String toString() {
        String str = "Nome: " + this.nome;

        return str;
    }
    public int adicionarCategoria(Categoria _categoria) {
        try {
            this.categorias.add(_categoria);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }

    public int removerCategoria(Categoria _categoria) {
        try {
            this.categorias.remove(_categoria);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
