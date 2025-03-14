package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.TarefaDao;
import model.entities.Tarefa;
import model.entities.enums.TarefaStatus;

public class TarefaDaoJDBC implements TarefaDao {

	private Connection conn;

	public TarefaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionar(Tarefa tarefa) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tarefa (conteudo) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, tarefa.getConteudo());

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				tarefa.setId(rs.getInt(1));
			}
			DB.closeResultSet(rs);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizar(Tarefa tarefa) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tarefa SET conteudo = ?, status = ? WHERE id = ?");

			st.setString(1, tarefa.getConteudo());
			st.setString(2, tarefa.getStatus().getDescricao());
			st.setInt(3, tarefa.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void remover(int id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tarefa WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Tarefa> motrarTodas() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Tarefa> list = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT * FROM tarefa");

			rs = st.executeQuery();

			while (rs.next()) {
				list.add(mapearTarefa(rs));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return list;
	}

	@Override
	public List<Tarefa> encontrarPorStatus(TarefaStatus status) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Tarefa> list = new ArrayList<>();

		try {
			st = conn.prepareStatement("SELECT * FROM tarefa WHERE status = ?");

			st.setString(1, status.getDescricao());

			rs = st.executeQuery();

			while (rs.next()) {
				list.add(mapearTarefa(rs));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return list;
	}

	@Override
	public Tarefa encontrarPorId(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tarefa WHERE id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				return mapearTarefa(rs);
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Tarefa mapearTarefa(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(rs.getInt("id"));
		tarefa.setConteudo(rs.getString("conteudo"));
		tarefa.setStatus(TarefaStatus.fromString(rs.getString("status")));

		Timestamp timestampCriacao = rs.getTimestamp("criado_em");
		Timestamp timestampAtualizacao = rs.getTimestamp("atualizado_em");

		if (timestampCriacao != null) {
			tarefa.setDataCriacao(timestampCriacao.toLocalDateTime());
		}
		if (timestampAtualizacao != null) {
			tarefa.setDataAtualizacao(timestampAtualizacao.toLocalDateTime());
		}
		return tarefa;
	}
}
