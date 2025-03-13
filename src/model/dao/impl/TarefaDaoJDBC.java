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
			st = conn.prepareStatement("INSERT INTO tarefa " + "(conteudo) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, tarefa.getConteudo());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					tarefa.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Tarefa encontrarPorId(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT tarefa.* " + "FROM tarefa " + "WHERE tarefa.Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setConteudo(rs.getString("conteudo"));
				tarefa.setStatus(TarefaStatus.valueOf(rs.getString("status").toUpperCase()));

				Timestamp timestamp = rs.getTimestamp("criado_em");
				timestamp = rs.getTimestamp("atualizado_em");
				if (timestamp != null) {
					tarefa.setDataCriacao(timestamp.toLocalDateTime());
					tarefa.setDataAtualizacao(timestamp.toLocalDateTime());
				}
				return tarefa;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizar(Tarefa tarefa) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tarefa " + "SET conteudo = ?, status = ? " + "WHERE id = ?");

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
			st = conn.prepareStatement("DELETE FROM tarefa " + "WHERE Id = ?");

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
		try {
			st = conn.prepareStatement("SELECT tarefa.* " + "FROM tarefa");

			rs = st.executeQuery();

			List<Tarefa> list = new ArrayList<>();
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setConteudo(rs.getString("conteudo"));
				tarefa.setStatus(TarefaStatus.valueOf(rs.getString("status").toUpperCase()));

				Timestamp timestamp = rs.getTimestamp("criado_em");
				timestamp = rs.getTimestamp("atualizado_em");
				if (timestamp != null) {
					tarefa.setDataCriacao(timestamp.toLocalDateTime());
					tarefa.setDataAtualizacao(timestamp.toLocalDateTime());
				}
				list.add(tarefa);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Tarefa> encontrarPorStatus(TarefaStatus status) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * " + "FROM tarefa " + "WHERE tarefa.status = ?");

			st.setString(1, status.getDescricao());

			rs = st.executeQuery();

			List<Tarefa> list = new ArrayList<>();
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setConteudo(rs.getString("conteudo"));
				tarefa.setStatus(TarefaStatus.valueOf(rs.getString("status").toUpperCase()));

				Timestamp timestamp = rs.getTimestamp("criado_em");
				timestamp = rs.getTimestamp("atualizado_em");
				if (timestamp != null) {
					tarefa.setDataCriacao(timestamp.toLocalDateTime());
					tarefa.setDataAtualizacao(timestamp.toLocalDateTime());
				}
				list.add(tarefa);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
}
