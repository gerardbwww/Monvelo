package Persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Business.entitie.Equipe;
import Persistence.Manager.JDBCManager;
import Persistence.exception.DaoException;

public class EquipeDao implements IDAO<Equipe> {

	public static final String sql_selectC = "SELECT * FROM equipe";
	public static final String sql_selectbyID = "SELECT * FROM equipe WHERE Id = ?";
	public static final String sql_Insert = "INSERT INTO equipe(name, budget) values (?,?)";
	public static final String sql_updateByID = "UPDATE equipe SET name = ?, budget=?  WHERE id = ?";
	public static final String sql_deletebyID = "DELETE FROM equipe WHERE id = ?";

	// -------------------- FINDLIST -------------------------------
	public List<Equipe> findList() throws DaoException {

		try {
			List<Equipe> list = new ArrayList<>();
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement sel = cnx.prepareStatement(sql_selectC);
			ResultSet st = sel.executeQuery();

			while (st.next()) {
				Long id = st.getLong("id");
				String name = st.getString("name");
				int budget = st.getInt("budget");
				/* ... */
				Equipe equipe = new Equipe(id, name, budget);
				list.add(equipe);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}

	}

	// ---------------FINDByID-------------------------------------
	public Equipe findById(long pId) throws DaoException {

		try {
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement prStatement = cnx.prepareStatement(sql_selectbyID);
			prStatement.setLong(1, pId);
			ResultSet st = prStatement.executeQuery();

			Equipe equipe = null;
			while (st.next()) {
				Long id = st.getLong("id");
				String name = st.getString("name");
				int budget = st.getInt("budget");

				equipe = new Equipe(id, name, budget);
			}
			return equipe;
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	// ----------------------CREATE-------------------------------------
	public Equipe create(Equipe pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_Insert, PreparedStatement.RETURN_GENERATED_KEYS);
		prStatement.setString(1, pT.getName());
		prStatement.setInt(2, pT.getBugdet());
		prStatement.execute();

		ResultSet rs = prStatement.getGeneratedKeys();
		long id = 0L;
		while (rs.next()) {
			id = rs.getLong("GENERATED_KEY");
		}
		pT.setId(id);
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	@Override
	// ---------------------- UPDATE -------------------------------------
	public Equipe updateById(Equipe pT) throws Exception {
		if (pT == null)
			return null;
		Connection cnx = JDBCManager.getInstance().openConection();

		PreparedStatement prStatement = cnx.prepareStatement(sql_updateByID);
		prStatement.setString(1, pT.getName());
		prStatement.setInt(2, pT.getBugdet());
		prStatement.setLong(3, pT.getId());
		prStatement.execute();
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	@Override
	// ---------------------- DELETE -------------------------------------
	public void deleteById(long pId) throws Exception {

		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_deletebyID);
		prStatement.setLong(1, pId);
		prStatement.execute();
		JDBCManager.getInstance().closeConnection();
	}

}
