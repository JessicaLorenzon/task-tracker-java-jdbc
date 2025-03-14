package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TarefaDao;
import model.entities.Tarefa;
import model.entities.enums.TarefaStatus;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		TarefaDao tarefaDao = DaoFactory.criarTarefaDao();

		System.out.println(menu());
		while (true) {
			System.out.print("O que deseja fazer? ");
			String acao = sc.nextLine().trim().toLowerCase();

			if (acao.equals("sair") || acao.equals("11")) {
				System.out.println("Task Traker encerrado!");
				break;
			}

			switch (acao) {
			case "1":
			case "adicionar":
				System.out.print("Digite a nova tarefa: ");
				String conteudo = sc.nextLine();
				Tarefa novaTarefa = new Tarefa(conteudo);
				if (conteudo.isBlank()) {
					System.out.println("Tarefa em branco. Tente novamente!");
				} else {
					tarefaDao.adicionar(novaTarefa);
					System.out.println("Tarefa adicionada com sucesso!");
				}
				System.out.println();
				break;
			case "2":
			case "remover":
				System.out.print("Digite o ID da tarefa a ser removida: ");
				int idRemover = sc.nextInt();
				sc.nextLine();
				if (tarefaDao.encontrarPorId(idRemover) == null) {
					System.out.println("ID não encontrado. Tente novamente!");
				} else {
					tarefaDao.remover(idRemover);
					System.out.println("Tarefa com ID: " + idRemover + " removida com sucesso!");
				}
				System.out.println();
				break;
			case "3":
			case "modificar":
				System.out.print("Digite o ID da tarefa a ser modificada: ");
				int idModificar = sc.nextInt();
				sc.nextLine();
				if (tarefaDao.encontrarPorId(idModificar) == null) {
					System.out.println("ID não encontrado. Tente novamente!");
				} else {
					Tarefa tarefa = tarefaDao.encontrarPorId(idModificar);
					System.out.print("Digite o novo conteudo da tarefa: ");
					String novoConteudo = sc.nextLine();
					if (novoConteudo.isBlank()) {
						System.out.println("Tarefa em branco. Tente novamente!");
					} else {
						tarefa.setConteudo(novoConteudo);
						tarefaDao.atualizar(tarefa);
						System.out.println("Tarefa com ID: " + idModificar + " modificada com sucesso!");
					}
				}
				System.out.println();
				break;
			case "4":
			case "marcar concluida":
				System.out.print("Digite o ID da tarefa a ser marcada como concluida: ");
				int idConcluida = sc.nextInt();
				sc.nextLine();
				if (tarefaDao.encontrarPorId(idConcluida) == null) {
					System.out.println("ID não encontrado. Tente novamente!");
				} else {
					Tarefa tarefa = tarefaDao.encontrarPorId(idConcluida);
					tarefa.setStatus(TarefaStatus.CONCLUIDA);
					tarefaDao.atualizar(tarefa);
					System.out.println("Status da tarefa com ID: " + idConcluida + " atualizado com sucesso!");
				}
				System.out.println();
				break;
			case "5":
			case "marcar em progresso":
				System.out.print("Digite o ID da tarefa a ser marcada como em progresso: ");
				int idProgresso = sc.nextInt();
				sc.nextLine();
				if (tarefaDao.encontrarPorId(idProgresso) == null) {
					System.out.println("ID não encontrado. Tente novamente!");
				} else {
					Tarefa tarefa = tarefaDao.encontrarPorId(idProgresso);
					tarefa.setStatus(TarefaStatus.EM_PROGRESSO);
					tarefaDao.atualizar(tarefa);
					System.out.println("Status da tarefa com ID: " + idProgresso + " atualizado com sucesso!");
				}
				System.out.println();
				break;
			case "6":
			case "listar todas":
				List<Tarefa> todas = tarefaDao.motrarTodas();
				for (Tarefa tarefa : todas) {
					System.out.println(tarefa);
				}
				System.out.println();
				break;
			case "7":
			case "listar concluidas":
				List<Tarefa> concluidas = tarefaDao.encontrarPorStatus(TarefaStatus.CONCLUIDA);
				for (Tarefa tarefa : concluidas) {
					System.out.println(tarefa);
				}
				System.out.println();
				break;
			case "8":
			case "listar pendentes":
				List<Tarefa> pendentes = tarefaDao.encontrarPorStatus(TarefaStatus.PENDENTE);
				for (Tarefa tarefa : pendentes) {
					System.out.println(tarefa);
				}
				System.out.println();
				break;
			case "9":
			case "listar em progresso":
				List<Tarefa> progresso = tarefaDao.encontrarPorStatus(TarefaStatus.EM_PROGRESSO);
				for (Tarefa tarefa : progresso) {
					System.out.println(tarefa);
				}
				System.out.println();
				break;
			case "10":
			case "ajuda":
				System.out.println();
				System.out.println(menu());
				break;
			default:
				System.out.println("Ação não reconhecida. Tente novamente!");
				System.out.println();
			}
		}
		sc.close();
	}

	public static String menu() {
		StringBuilder sb = new StringBuilder();
		sb.append("MENU DE AÇÕES\n");
		sb.append("1 - Adicionar;\n");
		sb.append("2 - Remover;\n");
		sb.append("3 - Modificar;\n");
		sb.append("4 - Marcar concluida;\n");
		sb.append("5 - Marcar em progresso;\n");
		sb.append("6 - Listar todas;\n");
		sb.append("7 - Listar concluidas;\n");
		sb.append("8 - Listar pendentes;\n");
		sb.append("9 - Listar em progresso;\n");
		sb.append("10 - Ajuda;\n");
		sb.append("11 - Sair;\n");
		return sb.toString();
	}
}
