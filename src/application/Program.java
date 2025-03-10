package application;

import model.dao.DaoFactory;
import model.dao.TarefaDao;
import model.entities.Tarefa;
import model.entities.enums.TarefaStatus;

public class Program {

	public static void main(String[] args) {

		TarefaDao tarefaDao = DaoFactory.criarTarefaDao();

		System.out.println("---- TESTE ADICIONAR ----");
		Tarefa novaTarefa = new Tarefa(null, "Nova tarefa", TarefaStatus.CONCLUIDA);
		tarefaDao.adicionar(novaTarefa);
		System.out.println("Tarefa criada! Novo id = " + novaTarefa.getId());

	}

}
