import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Criar outras classes Ferramentas
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        File arquivo = new File("wikiAIpedia.csv");
        List<Ferramenta> listaFerramentas = new ArrayList<>(listaDoArquivo(arquivo));
        int opcao;

        if (!arquivo.exists()) {
            criarArquivo(arquivo);
        }

        do {
            System.out.print(menu());
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    adicionarFerramenta(listaFerramentas);
                }
                case 2 -> {
                    removerFerramenta(listaFerramentas);
                }
                case 3 -> {
                    listarFerramentas(listaFerramentas);
                }
                case 4 -> {
                    editarFerramenta(listaFerramentas);
                }
                case 5 -> {
                    gerenciarCategorias(listaFerramentas);

                }
                case 6 -> {
                    salvarECarregar(arquivo, listaFerramentas);
                }
                case 7 -> {
                    break;
                }
                default -> System.out.println("Opção inválida");
            }

        } while(opcao != 7);
    }

    private static String menu() {
        String menu = "";
        menu += "-------------------------\n";
        menu += "WikiAIpedia: O Gerador de Conhecimento em Inteligência Artificial\n";
        menu += "-------------------------\n";
        menu += "1) Adicionar Ferramenta\n";
        menu += "2) Remover Ferramenta\n";
        menu += "3) Listar Ferramentas\n";
        menu += "4) Editar Ferramenta\n";
        menu += "5) Gerenciar Categorias\n";
        menu += "6) Salvar e Carregar\n";
        menu += ">>> ";


        return menu;
    }

    private static String menuCategorias() {
        String menu = "";
        menu += "\n-------------------------\n";
        menu += "1) Criar Categoria\n";
        menu += "2) Remover Categoria\n";
        menu += "3) Listar Categorias\n";
        menu += "4) Editar Categoria\n";
        menu += "5) Cancelar\n";
        menu += ">>> ";


        return menu;
    }


    private static void gerenciarCategorias(List<Ferramenta> listaFerramentas) {
        int opcao;
        String ferramentaNome;

        System.out.println("Selecione uma ferramenta");
        listarFerramentas(listaFerramentas);
        System.out.print(">>> ");
        ferramentaNome = sc.nextLine();

        for (Ferramenta f : listaFerramentas) {
            if (f.getNome().equalsIgnoreCase(ferramentaNome)) {
                System.out.print(menuCategorias());
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> {
                        adicionarCategoria(f);
                    }
                    case 2 -> {
                        removerCategoria(f);
                    }
                    case 3 -> {
                        listarCategorias(f);
                    }
                    default -> System.out.println("Opção inválida");
                }
                break;
            }
        }
    }

    private static void adicionarCategoria(Ferramenta ferramenta) {
        System.out.print("Nova categoria: ");
        String nomeCategoria = sc.nextLine();
        Categoria novaCategoria = new Categoria(nomeCategoria);
        ferramenta.adicionarCategoria(novaCategoria);
        System.out.println("Categoria adicionada com sucesso!");
    }

    private static void removerCategoria(Ferramenta ferramenta) {
        listarCategorias(ferramenta);
        System.out.print("Nome da categoria a ser removida: ");
        String nomeCategoria = sc.nextLine();
        List<Categoria> categorias = ferramenta.getCategorias();
        Categoria categoriaRemover = null;

        for (Categoria categoria : categorias) {
            if (categoria.getCategoriaNome().equalsIgnoreCase(nomeCategoria)) {
                categoriaRemover = categoria;
                break;
            }
        }

        if (categoriaRemover != null) {
            ferramenta.removerCategoria(categoriaRemover);
            System.out.println("Categoria removida com sucesso!");
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private static void listarCategorias(Ferramenta ferramenta) {
        System.out.println(ferramenta.getCategorias());
    }

    private static void salvarECarregar(File arquivo, List<Ferramenta> lista) {
        try (FileWriter escrita = new FileWriter(arquivo)) {
            for (Ferramenta f : lista) {
                String categorias = "";

                for (Categoria c : f.getCategorias()) {
                    categorias += c.getCategoriaNome() + " ";
                }

                escrita.write(f.getNome() + "," + f.getDescricao() + "," + "[" + categorias.toString() + "]\n");
            }
            System.out.println("Salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro na escrita: " + e.getMessage());
        }
    }

    private static void editarFerramenta(List<Ferramenta> listaFerramentas) {
        System.out.println("Ferramentas disponíveis:");
        listarFerramentas(listaFerramentas);

        System.out.print("Digite o nome da ferramenta que deseja editar: ");
        String nomeFerramenta = sc.nextLine();

        Ferramenta ferramentaEditar = null;

        for (Ferramenta ferramenta : listaFerramentas) {
            if (ferramenta.getNome().equalsIgnoreCase(nomeFerramenta)) {
                ferramentaEditar = ferramenta;
                break;
            }
        }

        if (ferramentaEditar != null) {
            System.out.print("Digite a nova descrição: ");
            String novaDescricao = sc.nextLine();
            ferramentaEditar.setDescricao(novaDescricao);
            System.out.println("Ferramenta editada com sucesso!");
        } else {
            System.out.println("Ferramenta não encontrada.");
        }
    }

    private static void listarFerramentas(List<Ferramenta> listaFerramentas) {
        if (listaFerramentas.isEmpty()) {
            System.out.println("Não há nenhuma ferramenta adicionada.");
        } else {
            for (Ferramenta f : listaFerramentas) {
                System.out.println(f.getNome());
            }
        }
    }

    private static void removerFerramenta(List<Ferramenta> listaFerramentas) {
        listarFerramentas(listaFerramentas);

        System.out.print("Digite o nome da ferramenta que deseja remover: ");
        String nomeFerramenta = sc.nextLine();

        Ferramenta ferramentaRemover = null;

        for (Ferramenta ferramenta : listaFerramentas) {
            if (ferramenta.getNome().equalsIgnoreCase(nomeFerramenta)) {
                ferramentaRemover = ferramenta;
                break;
            }
        }

        if (ferramentaRemover != null) {
            listaFerramentas.remove(ferramentaRemover);
            System.out.println("Ferramenta removida com sucesso!");
        } else {
            System.out.println("Ferramenta não encontrada.");
        }
    }

    private static void adicionarFerramenta(List<Ferramenta> listaFerramentas) {
        System.out.print("Nome da nova ferramenta: ");
        String nomeFerramenta = sc.nextLine();

        System.out.print("Descrição da nova ferramenta: ");
        String descricaoFerramenta = sc.nextLine();

        Ferramenta novaFerramenta = new Ferramenta(nomeFerramenta, descricaoFerramenta);
        listaFerramentas.add(novaFerramenta);

        System.out.println("Ferramenta adicionada com sucesso!");
    }

    private static void criarArquivo(File arquivo) {
        try {
            FileWriter escrita = new FileWriter(arquivo);
            escrita.write("");

            escrita.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita: " + e.getMessage());
        }
    }

    private static List<Ferramenta> listaDoArquivo(File arquivo) {
        // nome , desc , [categ, categ ....]
        List<Ferramenta> lista = new ArrayList<>();
        try (BufferedReader bufferLeitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = bufferLeitor.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                String descricao = partes[1];
                Ferramenta ferramenta = new Ferramenta(nome, descricao);

                if (partes.length > 2 && partes[2].startsWith("[")) {
                    String[] categoriasPartes = partes[2].substring(1, partes[2].length() - 1).split(" ");
                    for (String categoriaNome : categoriasPartes) {
                        ferramenta.adicionarCategoria(new Categoria(categoriaNome));
                    }
                }

                lista.add(ferramenta);
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
        return lista;
    }
}

