package model.entities.enums;

public enum TarefaStatus {

	PENDENTE("pendente"), CONCLUIDA("concluida"), EM_PROGRESSO("em_progresso");

	private String descricao;

	TarefaStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TarefaStatus fromString(String status) {
		if (status == null)
			return null;
		try {
			return TarefaStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Status inválido: " + status);
		}
	}
}
