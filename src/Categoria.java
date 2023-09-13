public class Categoria {
    // variaveis
    private String nome;


    // construtores
    public Categoria(){}
    public Categoria(String _nome) {
        this.nome = _nome;
    }

    // encapsulamento
    public void setCategoriaNome(String _categoriaNome)
    {
        this.nome = _categoriaNome;
    }

    public String getCategoriaNome()
    {
        return this.nome;
    }

    // metodos
    @Override
    public String toString() {
        String str = "Categoria: " + this.nome;
        return str;
    }
    public boolean atualizarCategoria(Categoria categoriaNova, Categoria categoriaAntiga) {
            if (categoriaAntiga.getCategoriaNome() == null) {
                return false;
            }
            else {
                if (categoriaNova.getCategoriaNome() == null) {
                    categoriaAntiga.setCategoriaNome(categoriaNova.getCategoriaNome());
                    return true;
                } else {
                    return false;
                }
            }
    }

}
