package src;

import java.util.ArrayList;
import java.util.Scanner;

class Livro {

    private String titulo;
    private String autor;
    private int ano;
    private double preco;
    private boolean disponivel;

    public Livro() {
        this.titulo     = "Sem titulo";
        this.autor      = "Desconhecido";
        this.ano        = 2024;
        this.preco      = 0.0;
        this.disponivel = true;
    }

    public Livro(String titulo, String autor, int ano, double preco) {
        this.titulo     = titulo;
        this.autor      = autor;
        this.ano        = ano;
        this.preco      = preco;
        this.disponivel = true;
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Livro \"" + titulo + "\" emprestado com sucesso!");
        } else {
            System.out.println("Este livro ja esta emprestado.");
        }
    }

    public void devolver() {
        if (!disponivel) {
            disponivel = true;
            System.out.println("Livro \"" + titulo + "\" devolvido com sucesso!");
        } else {
            System.out.println("Este livro ja esta disponivel.");
        }
    }

    @Override
    public String toString() {
        String status = disponivel ? "Disponivel" : "Emprestado";
        return "--------------------------------\n"
             + "Titulo  : " + titulo + "\n"
             + "Autor   : " + autor + "\n"
             + "Ano     : " + ano + "\n"
             + "Preco   : R$ " + String.format("%.2f", preco) + "\n"
             + "Status  : " + status + "\n"
             + "--------------------------------";
    }

    public String  getTitulo()    { return titulo; }
    public String  getAutor()     { return autor; }
    public int     getAno()       { return ano; }
    public double  getPreco()     { return preco; }
    public boolean isDisponivel() { return disponivel; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            System.out.println("Titulo invalido!");
        } else {
            this.titulo = titulo;
        }
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            System.out.println("Autor invalido!");
        } else {
            this.autor = autor;
        }
    }

    public void setAno(int ano) {
        if (ano < 1000 || ano > 2024) {
            System.out.println("Ano invalido!");
        } else {
            this.ano = ano;
        }
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            System.out.println("Preco nao pode ser negativo!");
        } else {
            this.preco = preco;
        }
    }
}

public class Main {

    static ArrayList<Livro> livros = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        livros.add(new Livro("O Hobbit", "Tolkien", 1937, 49.90));
        livros.add(new Livro("Clean Code", "Robert Martin", 2008, 89.90));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n============================");
            System.out.println("   SISTEMA DE BIBLIOTECA   ");
            System.out.println("============================");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por titulo");
            System.out.println("4. Emprestar livro");
            System.out.println("5. Devolver livro");
            System.out.println("6. Remover livro");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if      (opcao == 1) cadastrar();
            else if (opcao == 2) listarTodos();
            else if (opcao == 3) buscar();
            else if (opcao == 4) emprestar();
            else if (opcao == 5) devolver();
            else if (opcao == 6) remover();
            else if (opcao == 0) System.out.println("Ate logo!");
            else                 System.out.println("Opcao invalida!");
        }
    }

    static void cadastrar() {
        System.out.println("\n-- Cadastrar Livro --");
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.print("Preco: ");
        double preco = sc.nextDouble();
        sc.nextLine();
        livros.add(new Livro(titulo, autor, ano, preco));
        System.out.println("Livro cadastrado com sucesso!");
    }

    static void listarTodos() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n-- Lista de Livros --");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println("[" + i + "]");
            System.out.println(livros.get(i));
        }
    }

    static void buscar() {
        System.out.print("Digite o titulo: ");
        String busca = sc.nextLine().toLowerCase();
        boolean achou = false;
        for (Livro l : livros) {
            if (l.getTitulo().toLowerCase().contains(busca)) {
                System.out.println(l);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum livro encontrado.");
    }

    static void emprestar() {
        listarTodos();
        if (livros.isEmpty()) return;
        System.out.print("Numero do livro: ");
        int i = sc.nextInt();
        sc.nextLine();
        if (i >= 0 && i < livros.size()) livros.get(i).emprestar();
        else System.out.println("Numero invalido.");
    }

    static void devolver() {
        listarTodos();
        if (livros.isEmpty()) return;
        System.out.print("Numero do livro: ");
        int i = sc.nextInt();
        sc.nextLine();
        if (i >= 0 && i < livros.size()) livros.get(i).devolver();
        else System.out.println("Numero invalido.");
    }

    static void remover() {
        listarTodos();
        if (livros.isEmpty()) return;
        System.out.print("Numero do livro: ");
        int i = sc.nextInt();
        sc.nextLine();
        if (i >= 0 && i < livros.size()) {
            System.out.println("Removido: " + livros.get(i).getTitulo());
            livros.remove(i);
        } else {
            System.out.println("Numero invalido.");
        }
    }
}