package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TarefaDao;
import model.entities.Tarefa;

public class Program {

	public static void main(String[] args) {

		TarefaDao tarefaDao = DaoFactory.criarTarefaDao();

		System.out.println("---- TESTE ADICIONAR ----");
		Tarefa novaTarefa = new Tarefa("Nova tarefa");
		tarefaDao.adicionar(novaTarefa);
		System.out.println("Tarefa criada! Novo id = " + novaTarefa.getId());

		System.out.println("\n---- TESTE ENCONTRAR POR ID ----");
		Tarefa tarefa = tarefaDao.encontrarPorId(5);
		System.out.println(tarefa);

		System.out.println("\n---- TESTE ATUALIZAR ----");
		tarefa = tarefaDao.encontrarPorId(3);
		tarefa.setConteudo("Email enviado!");
		System.out.println(tarefa);
		tarefaDao.atualizar(tarefa);
		System.out.println("Tarefa atualizada!");

		System.out.println("\n---- TESTE REMOVER ----");
		tarefaDao.remover(10);
		System.out.println("Tarefa removida!");

		System.out.println("\n---- TESTE MOSTRAR TODAS AS TAREFAS ----");
		List<Tarefa> list = tarefaDao.motrarTodas();
		for (Tarefa obj : list) {
			System.out.println(obj);
		}

	}

}
