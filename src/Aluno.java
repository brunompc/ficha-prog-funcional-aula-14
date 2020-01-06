public class Aluno {
    String nome;
    int nota;
    String nacionalidade;
    Aluno(String nome, int nota, String nacionalidade) {
        this.nome = nome;
        this.nota = nota;
        this.nacionalidade = nacionalidade;
    }
    boolean estaAprovado() {
        return nota >= 10;
    }
    String getNome() {
        return nome;
    }
    int getNota() {
        return nota;
    }
    String getNacionalidade() {
        return nacionalidade;
    }
}
