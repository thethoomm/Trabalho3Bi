public class Categoria {
    // variaveis
    private String categoriaNome;

    // encapsulamento
    public void setCategoriaNome(String _categoriaNome)
    {
        this.categoriaNome = _categoriaNome;
    }

    public String getCategoriaNome()
    {
        return this.categoriaNome;
    }

    // metodos
    @Override
    public String toString() {
        String str = "Categoria: " + this.categoriaNome;
        return str;
    }
    public int atualizarCategoria(Categoria categoriaNova, Categoria categoriaAntiga) {
        try {
            if (categoriaAntiga.getCategoriaNome() == null) {
                return 404;
            }
            else {
                if (categoriaNova.getCategoriaNome() == null) {
                    categoriaAntiga.setCategoriaNome(categoriaNova.getCategoriaNome());
                    return 200;
                } else {
                    return 400;
                }
            }
        } catch (Exception e) {
            return 500;
        }
    }

}
