package model.entities.enums;

public enum TarefaStatus {

	PENDENTE("pendente"), 
	CONCLUIDA("concluida"), 
	EM_ANDAMENTO("em_progresso");

	private String descricao;
	
	TarefaStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
