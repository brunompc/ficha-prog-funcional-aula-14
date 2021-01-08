import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Aluno> alunos = new ArrayList<>();

        alunos.add(new Aluno("Bruno", 19, "Portuguesa"));
        alunos.add(new Aluno("Pedro", 20, "Portuguesa"));
        alunos.add(new Aluno("Rodrigo", 9, "Portuguesa"));
        alunos.add(new Aluno("Yohana", 13, "Alemã"));
        alunos.add(new Aluno("Martina", 12, "Eslovénia"));
        alunos.add(new Aluno("Rui", 20, "Espanhola"));
        alunos.add(new Aluno("Ivo", 8, "Portuguesa"));

        // 0 - Quantos alunos existem?
        long nrAlunos = alunos.stream().count();

        System.out.println("Nr Alunos: " + nrAlunos);

        // 1 - 1) Quantos alunos estão aprovados?

        long nrAlunosAprovados = alunos.stream()
                                    .filter((Aluno a) -> a.estaAprovado()) // fiquei só com os aprovados
                                    .count();

        System.out.println("Nr Alunos Aprovados: " + nrAlunosAprovados);

        // 2) Quantos alunos de nacionalidade portuguesa estão aprovados?

        alunos.stream()
                .filter(a -> a.estaAprovado()) // fiquei só com os aprovados
                .filter(a -> a.getNacionalidade().equals("Portuguesa")) // fiquei só com os (aprovados) portugueses
                .count();

        // 3) Quais os nomes e notas finais dos alunos aprovados?
        alunos.stream()
                .filter(a -> a.estaAprovado()) // fiquei só com os aprovados
                .forEach(a -> {
                    System.out.println(a.getNome() + " " + a.getNota());
                });

        // 4) Quantas notas diferentes existem?

        long nrNotasDist = alunos.stream() // stream com obj Aluno
                            .map(a ->a.getNota()) // stream com int(s)
                            .distinct()
                            .count();

        System.out.println("Nr Noats Dif: " + nrNotasDist);

        // 4.1) Quais são as notas distintas?

        alunos.stream() // stream com obj Aluno
                .map(a ->a.getNota()) // stream com int(s)
                .distinct()
                .forEach(n -> System.out.println(n));

        // 5) Quais os nomes e notas finais dos alunos aprovados, ordenados por nota (crescente)?

        alunos.stream()
                    .filter(a -> a.estaAprovado()) // ficamos só com os Aluno(s) aprovados
                    .sorted((a1, a2) -> a1.getNota() - a2.getNota())
                    .forEach(a -> {
                        System.out.println(a.getNome() + " " + a.getNota());
                    });

        // 5.1

        // (Basta inverter a lógica do lambda que o sorted recebe)

        // 6) Quais os nomes e notas finais dos alunos reprovados, ordenados por nome (crescente)?

        alunos.stream()
                .filter(a -> !a.estaAprovado()) // ficamos só com os reprovados !!!!!
                .sorted((a1, a2) -> a1.getNome().compareTo(a2.getNome()))
                .forEach(a -> {
                    System.out.println(a.getNome() + " " + a.getNota());
                });

        // 7) Qual é a nota mais alta?

            // hipóteses:
                // a) ordenar os alunos por nota decrescente -> ir buscar o 1º (sorted + limit)
                // b) transformar os alunos em notas -> ordenar os ints (decrescente) -> ir buscar o 1º (map+sorted+limit)
                // c) usar o max()

        int notaMax = alunos.stream()
                    .max((a1, a2) -> a1.getNota() - a2.getNota())
                    .get()
                    .getNota();

        System.out.println("> " + notaMax);

        // 8) Obter 1 lista com os alunos aprovados.

        // versão feia
        List<Aluno> aprovados = new ArrayList<>();

        alunos.stream()
                .filter(a -> a.estaAprovado())
                .forEach(a -> {
                    aprovados.add(a);
                });

        System.out.println("1) Tamanho: " + aprovados.size());

        // versão bela
        List<Aluno> aprovados2 = alunos.stream()
                                        .filter(a -> a.estaAprovado())
                                        .collect(Collectors.toList());

        System.out.println("2) Tamanho: " + aprovados2.size());

        // 9) Qual é a média das notas?

        double media = alunos.stream()
                                .map(a -> a.getNota())
                                .collect(Collectors.averagingDouble(n -> n));

        System.out.println("Média: " + media);

        // versão alternativa
        media = alunos.stream()
                        .collect(Collectors.averagingDouble(a -> a.getNota()));

        System.out.println("Média: " + media);

        // 10) Qual é a média de notas dos alunos aprovados?

        double mediaAprovados = alunos.stream()
                                        .filter(a -> a.estaAprovado())
                                        .collect(Collectors.averagingDouble(a -> a.getNota()));

        System.out.println("Média Aprovados: " + mediaAprovados);

        // 11) Qual o nome, nacionalidade e nota dos 3 alunos com nota mais alta?

        System.out.println("Os 3 melhores alunos:");

        alunos.stream()
                .sorted((a1, a2) -> a2.getNota() - a1.getNota()) // ordenar DESC
                .limit(3)
                .forEach(a -> System.out.println(a.getNome() + " " + a.getNacionalidade() + " " + a.getNota()));

        // 12) Quantos alunos têm nota entre 10 e 15?

        long res = alunos.stream()
                            .filter(a -> a.getNota() >= 10 && a.getNota() <= 15)
                            .count();

        System.out.println("Nr aluns com nota [10; 15] ");

        // 13) Obtenha uma lista sem duplicados das nacionalidades dos alunos, ordenadas
        //alfabeticamente.

        List<String> nacionalidades = alunos.stream()
                                            .map(a -> a.getNacionalidade())
                                            .distinct()
                                            .sorted((nacionalidade1, nacionalidade2) -> nacionalidade1.compareTo(nacionalidade2))
                                            .collect(Collectors.toList());

        System.out.println("Nacionalidades: ");

        for(String nacionalidade : nacionalidades) {
            System.out.println(nacionalidade);
        }

        // 14)

        // TODO - para sacar a 1ª letra podemos usar charAt(0)

        // 15) Obtenha uma List<String> em que cada elemento tem a seguinte sintaxe:
        //<Nome Aluno> : <Nacionalidade> : <Nota>

        List<String> dados = alunos.stream()
                                    .map((Aluno a) -> a.getNome() + " : " + a.getNacionalidade() + " : " + a.getNota())
                                    .collect(Collectors.toList());
        for(String d : dados) {
            System.out.println(d);
        }
		
		
    }
}
