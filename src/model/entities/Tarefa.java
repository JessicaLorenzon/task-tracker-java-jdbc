package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import model.entities.enums.TarefaStatus;

public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String conteudo;
	private TarefaStatus status;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;

	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Tarefa() {
	}

	public Tarefa(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public TarefaStatus getStatus() {
		return status;
	}

	public void setStatus(TarefaStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "id = " + getId() + ", \"" + getConteudo() + "\", status = " + getStatus()
				+ ", criada em = " + getDataCriacao().format(fmt) + ", atualizada em = "
				+ getDataAtualizacao().format(fmt);
	}

}
