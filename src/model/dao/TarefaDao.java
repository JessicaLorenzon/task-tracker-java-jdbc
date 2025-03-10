package model.dao;

import model.entities.Tarefa;

public interface TarefaDao {

	void adicionar(Tarefa tarefa);
	
	void atualizar(Tarefa tarefa);

	void remover(int id);

}
