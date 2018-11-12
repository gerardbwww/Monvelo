package Persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Business.entitie.Moteur;
import Persistence.Manager.JDBCManager;
import Persistence.exception.DaoException;

public class MoteurDao implements IDAO<Moteur> {

	public static final String sql_selectC = "SELECT * FROM moteur";
	public static final String sql_selectbyID = "SELECT * FROM moteur WHERE Id = ?";
	public static final String sql_Insert = "INSERT INTO moteur(Marque, Modele, Cylindree) values (?,?,?)";
	public static final String sql_updateByID = "UPDATE moteur SET Marque = ?, Modele=?  WHERE id = ?";
	public static final String sql_deletebyID = "DELETE FROM moteur WHERE id = ?";

	// -------------------- FINDLIST -------------------------------
	public List<Moteur> findList() throws DaoException {

		try {
			List<Moteur> list = new ArrayList<>();
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement sel = cnx.prepareStatement(sql_selectC);
			ResultSet st = sel.executeQuery();

			while (st.next()) {
				Long id = st.getLong("ID");
				String marque = st.getString("Marque");
				String modele = st.getString("Modele");
				Long cylindree = st.getLong("Cylindree");
				/* ... */
				Moteur moteur = new Moteur(id, marque, modele, cylindree);
				list.add(moteur);
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
	public Moteur findById(long pId) throws DaoException {

		try {
			Connection cnx = JDBCManager.getInstance().openConection();
			PreparedStatement prStatement = cnx.prepareStatement(sql_selectbyID);
			prStatement.setLong(1, pId);
			ResultSet st = prStatement.executeQuery();

			Moteur moteur = null;
			while (st.next()) {
				Long id = st.getLong("id");
				String marque = st.getString("Marque");
				String modele = st.getString("Modele");
				int cylindree = st.getInt("Cylindree");
				moteur = new Moteur(id, marque, modele, cylindree);
			}
			return moteur;
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
	public Moteur create(Moteur pT) throws Exception {
		if (pT == null)
			return null;
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_Insert, PreparedStatement.RETURN_GENERATED_KEYS);
		prStatement.setString(1, pT.getMarque());
		prStatement.setString(2, pT.getModele());
		prStatement.setLong(3, pT.getCylindree());
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
	public Moteur updateById(Moteur pT) throws Exception {
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
