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
        System.out.println(listaFerramentas.size());
        for (Ferramenta f : listaFerramentas) {
            System.out.println("Teste: " + f);
        }

        do {
            System.out.print(menu());
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    adicionar(listaFerramentas, "ferramenta", "");
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
        int opcao = 0;
        String ferramentaNome;
        Ferramenta ferramentaEmAlteracao;

        System.out.println("Selecione uma ferramenta");
        for (Ferramenta f : listaFerramentas) {
            System.out.println(f);
        }
        System.out.print(">>> ");
        ferramentaNome = sc.nextLine();
        do {
            boolean naoEncontrou = false;
            System.out.println(ferramentaNome);
            for (Ferramenta f : listaFerramentas) {
                if (f.getNome().equalsIgnoreCase(ferramentaNome)) {
                    naoEncontrou = false;
                    System.out.print(menuCategorias());
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            adicionar(listaFerramentas, "categoria", ferramentaNome);
                        }
                    }
                } else {
                    naoEncontrou = true;
                }
            }

            if (naoEncontrou) {
                System.out.println("Nome invalido");
                opcao = 5;
            }
        } while(opcao != 5);
    }

    private static void salvarECarregar(File arquivo, List<Ferramenta> lista) {
        try {
            FileWriter escrita = new FileWriter(arquivo);

            for (Ferramenta f : lista) {
                String requisicao;
                String categorias = "";
                requisicao = null + ";" + f.getNome() + ";" + f.getDescricao() + ";";
                for (Categoria c : f.getCategorias()) {
                    if (f.getCategorias().size() > 1) {
                        categorias += "|" +  c.getCategoriaNome();
                    } else if (f.getCategorias().size() == 1) {
                        categorias += c.getCategoriaNome();
                    }
                }
                requisicao += "[" + categorias + "]";

                escrita.write(requisicao + "\n");

                System.out.println("Salvo com sucesso!");
            }
            escrita.close();
        } catch (IOException e) {
            System.out.println("Erro na escrita: " + e.getMessage());
        }
    }

    private static void editarFerramenta(List<Ferramenta> listaFerramentas) {
        Ferramenta ferramenta = new Ferramenta();
        String opcaoFerramenta;
        int opcao;

        System.out.println("-------------------");
        System.out.println("    Editar Ferramenta    ");
        System.out.println("-------------------");
        listarFerramentas(listaFerramentas);
        if (!listaFerramentas.isEmpty()) {
            System.out.println("Qual ferramenta você deseja editar?");
            System.out.print(">>> ");
            opcaoFerramenta = sc.nextLine();
            for (Ferramenta f : listaFerramentas) {
                if (f.getNome().equalsIgnoreCase(opcaoFerramenta)) {
                    System.out.println("Qual atributo deseja editar?");
                    System.out.println("1) Descrição");
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            String descricaoNova;
                            System.out.println("Nova descrição: ");
                            descricaoNova = sc.nextLine();
                            f.setDescricao(descricaoNova);
                        }
                        default -> System.out.println("Opção inválida");
                    }
                }
            }
        }
    }

    private static void listarFerramentas(List<Ferramenta> listaFerramentas) {
        if (listaFerramentas.isEmpty()) {
            System.out.println("Não há nenhuma ferramenta adicionada");
        } else {
            String opcao;
            boolean filtro = false;

            System.out.println("Deseja com filtro ou sem? Com | Sem");
            opcao = sc.nextLine();
            if (opcao.equalsIgnoreCase("com")) filtro = true;
            if (filtro) {
                String filtroNome;

                System.out.print("Filtro: ");
                filtroNome = sc.nextLine();

                for (Ferramenta f : listaFerramentas) {
                    for (Categoria c : f.getCategorias()) {
                        if (c.getCategoriaNome().equalsIgnoreCase(filtroNome)) {
                            System.out.println(c.getCategoriaNome());
                            System.out.println(f.getNome());
                        }
                    }
                }
            } else {
                for (Ferramenta f : listaFerramentas) {
                    System.out.println(f.getNome());
                }
            }
        }
    }

    private static void removerFerramenta(List<Ferramenta> listaFerramentas) {
        Ferramenta ferramenta = new Ferramenta();

        System.out.println("-------------------");
        System.out.println("    Remover Ferramenta    ");
        System.out.println("-------------------");
        listarFerramentas(listaFerramentas);
        if (!listaFerramentas.isEmpty()) {
            System.out.println("Qual ferramenta você deseja remover?");
            System.out.print(">>> ");
            ferramenta.setNome(sc.nextLine());

            for (Ferramenta f : listaFerramentas) {
                if (f.getNome().equalsIgnoreCase(ferramenta.getNome())) {
                    listaFerramentas.remove(f);
                    break;
                }
            }
        }
    }

    private static void adicionar(List<Ferramenta> listaFerramentas, String tipo, String adicionais) {
        if (tipo.equalsIgnoreCase("ferramenta")) {
            Ferramenta ferramenta = new Ferramenta();
            String categorias;

            System.out.println("-------------------");

            System.out.println("    Adicionar Ferramenta    ");
            System.out.println("-------------------");
            System.out.print("Nome: ");
            ferramenta.setNome(sc.nextLine());
            System.out.print("Descrição: ");
            ferramenta.setDescricao(sc.nextLine());
            System.out.print("Categorias: ");
            categorias = sc.nextLine();

            String[] listaCategorias = categorias.split(" ");
            // Percorre as categorias em string para transforma-las em Categorias
            for (String s : listaCategorias) {
                Categoria categoria = new Categoria(s);
                ferramenta.adicionarCategoria(categoria);
            }

            if (!listaFerramentas.isEmpty()) {
                for (Ferramenta f : listaFerramentas) {
                    if (f.getNome().equalsIgnoreCase(ferramenta.getNome())) {
                        System.out.println("Já existe essa ferramenta");
                        break;
                    } else {
                        listaFerramentas.add(ferramenta);
                        System.out.println("Ferramenta adicionada");
                        break;
                    }
                }
            } else {
                listaFerramentas.add(ferramenta);
                System.out.println("Ferramenta adicionada");
            }
        } else if (tipo.equalsIgnoreCase("categoria")) {
            Ferramenta ferramentaEmAlteracao;

            for (Ferramenta f : listaFerramentas) {
                if (f.getNome().equalsIgnoreCase(adicionais)) {
                    String categorias;
                    System.out.println("Novas Categorias: ");
                    categorias = sc.nextLine();

                    String[] listaCategorias = categorias.split(" ");
                    for (String s : listaCategorias) {
                        Categoria categoria = new Categoria(s);
                        f.adicionarCategoria(categoria);
                    }
                }
            }

            System.out.println("Adicionado com sucesso!");
        }
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
        // null | nome | desc | categ | categ ....
        List<Ferramenta> lista = new ArrayList<Ferramenta>();
        try {

            FileReader leitura = new FileReader(arquivo);
            BufferedReader bufferLeitor = new BufferedReader(leitura);
            String linha;

            while((linha = bufferLeitor.readLine()) != null) {
                String[] linhaCompleta = linha.split(";");
                Ferramenta ferramenta = new Ferramenta(linhaCompleta[1], linhaCompleta[2], linhaCompleta[3]);

                lista.add(ferramenta);
            }

            leitura.close();
            bufferLeitor.close();

        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
        return lista;
    }

}

