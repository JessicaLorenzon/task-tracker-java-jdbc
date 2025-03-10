package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.dao.TarefaDao;
import model.entities.Tarefa;

public class TarefaDaoJDBC implements TarefaDao {

	private Connection conn;

	public TarefaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void adicionar(Tarefa tarefa) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tarefa " + "(conteudo, status) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, tarefa.getConteudo());
			st.setString(2, tarefa.getStatus().getDescricao());

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
	public void atualizar(Tarefa tarefa) {

	}

	@Override
	public void remover(int id) {
		// TODO Auto-generated method stub

	}
}
