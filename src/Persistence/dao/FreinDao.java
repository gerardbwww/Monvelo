package Persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Business.entitie.Frein;
import Persistence.Manager.JDBCManager;
import Persistence.exception.DaoException;

public class FreinDao implements IDAO<Frein> {

	public static final String sql_selectC = "SELECT * FROM frein";
	public static final String sql_selectbyID = "SELECT * FROM frein WHERE Id = ?";
	public static final String sql_Insert = "INSERT INTO frein(Marque, Modele) values (?,?)";
	public static final String sql_updateByID = "UPDATE frein SET Marque = ?, Modele=?  WHERE id = ?";
	public static final String sql_deletebyID = "DELETE FROM frein WHERE id = ?";

	// -------------------- FINDLIST -------------------------------
	public List<Frein> findList() throws DaoException {

		try {
			List<Frein> list = new ArrayList<>();
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement sel = cnx.prepareStatement(sql_selectC);
			ResultSet st = sel.executeQuery();

			while (st.next()) {
				Long id = st.getLong("ID");
				String marque = st.getString("Marque");
				String modele = st.getString("Modele");
				/* ... */
				Frein frein = new Frein(id, marque, modele);
				list.add(frein);
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
	public Frein findById(long pId) throws DaoException {

		try {
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement prStatement = cnx.prepareStatement(sql_selectbyID);
			prStatement.setLong(1, pId);
			ResultSet st = prStatement.executeQuery();

			Frein frein = null;
			while (st.next()) {
				Long id = st.getLong("id");
				String name = st.getString("Marque");
				String latinName = st.getString("Modele");

				frein = new Frein(id, name, latinName);
			}
			return frein;
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
	public Frein create(Frein pT) throws Exception {
		if (pT == null)
			return null;
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_Insert, PreparedStatement.RETURN_GENERATED_KEYS);
		prStatement.setString(1, pT.getMarque());
		prStatement.setString(2, pT.getModele());
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
	public Frein updateById(Frein pT) throws Exception {
		if (pT == null)
			return null;
		Connection cnx = JDBCManager.getInstance().openConection();

		PreparedStatement prStatement = cnx.prepareStatement(sql_updateByID);
		prStatement.setString(1, pT.getMarque());
		prStatement.setString(2, pT.getModele());
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
