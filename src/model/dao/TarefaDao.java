package model.dao;

import java.util.List;

import model.entities.Tarefa;

public interface TarefaDao {

	void adicionar(Tarefa tarefa);

	void atualizar(Tarefa tarefa);

	void remover(int id);

	Tarefa encontrarPorId(int id);
	
	List<Tarefa> motrarTodas();

}
