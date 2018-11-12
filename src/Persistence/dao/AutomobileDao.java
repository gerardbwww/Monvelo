package Persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Business.entitie.Automobile;
import Business.entitie.Frein;
import Business.entitie.Moteur;
import Persistence.Manager.JDBCManager;

public class AutomobileDao implements IDAO<Automobile> {

	public static final String sql_selectC = "SELECT * FROM automobile inner join moteur on automobile.moteur_id = moteur.id inner join frein on frein.id = automobile.frein_id";	
	public static final String sql_selectbyID = "SELECT * FROM automobile inner join moteur on automobile.moteur_id = moteur.id inner join frein on frein.id = automobile.frein_id WHERE automobile.id = ?";
	public static final String sql_Insert = "INSERT INTO automobile (marque, modele, moteur_id, frein_id) values (?,?,?,?)";
	public static final String sql_updateByID = "UPDATE automobile SET marque = ?, modele = ?, moteur_id = ? , frein_id = ? WHERE id = ?";
	public static final String sql_deletebyID = "DELETE FROM automobile WHERE id = ?";

	// [1] ----------------- FINDLIST ----------------------------------
	@Override
	public List<Automobile> findList() throws Exception {
		List<Automobile> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_selectC);
		ResultSet st = prStatement.executeQuery();

		while (st.next()) {
			long id = st.getLong("automobile.id");
			String marque = st.getString("automobile.marque");
			String modele = st.getString("automobile.modele");
			long moteur_id = st.getLong("moteur.id");
			String marqueM = st.getString("moteur.marque");
			String modeleM = st.getString("moteur.modele");
			Long cylindreeM = st.getLong("moteur.cylindree");
			long frein_id = st.getLong("frein.id");
			String marqueF = st.getString("frein.marque");
			String modeleF = st.getString("frein.modele");
	
			
			/* ... */
			Moteur moteur = new Moteur(moteur_id, marqueM, modeleM, cylindreeM);
			Frein frein = new Frein(frein_id, marqueF, modeleF);
			Automobile automobile = new Automobile(id, marque, modele, moteur, frein);
			list.add(automobile);
		}
		JDBCManager.getInstance().closeConnection();
		return list;

	}

	// [2] ------------------ FINDBYID -----------------------------
	@Override
	public Automobile findById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_selectbyID);
		prStatement.setLong(1, pId);
		ResultSet st = prStatement.executeQuery();

		//
		Automobile automobile = null;
		while (st.next()) {
			long id = st.getLong("automobile.id");
			String marque = st.getString("automobile.marque");
			String modele = st.getString("automobile.modele");
			long moteur_id = st.getLong("moteur.id");
			String marqueM = st.getString("moteur.marque");
			String modeleM = st.getString("moteur.modele");
			long cylindreeM = st.getLong("moteur.cylindree");
			long frein_id = st.getLong("frein.id");
			String marqueF = st.getString("frein.marque");
			String modeleF = st.getString("frein.modele");
	
			
			/* ... */
			Moteur moteur = new Moteur(moteur_id, marqueM, modeleM, cylindreeM);
			Frein frein = new Frein(frein_id, marqueF, modeleF);
			automobile = new Automobile(id, marque, modele, moteur, frein);

		}
		JDBCManager.getInstance().closeConnection();
		return automobile;
	}

	// [3] ----------------------CREATE-------------------------------------
	@Override
	public Automobile create(Automobile pT) throws Exception {
		if (pT == null)
			return null; // retourne null si paramètre null
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_Insert, PreparedStatement.RETURN_GENERATED_KEYS);

		prStatement.setString(1, pT.getMarque());
		prStatement.setString(2, pT.getModele());
		prStatement.setLong(3, pT.getMoteur().getId());
		prStatement.setLong(4, pT.getFrein().getId());
	

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

	// [4] -------------------- UPDATE --------------------------------
	@Override
	public Automobile updateById(Automobile pT) throws Exception {
		if (pT == null)
			return null; // retourne null si paramètre null

		Connection cnx = JDBCManager.getInstance().openConection();

		PreparedStatement prStatement = cnx.prepareStatement(sql_updateByID);
		
		prStatement.setString(1, pT.getMarque());
		prStatement.setString(2, pT.getModele());
		prStatement.setLong(3, pT.getMoteur().getId());
		prStatement.setLong(4, pT.getFrein().getId());
		prStatement.setLong(5, pT.getId());
		
		prStatement.execute();
		JDBCManager.getInstance().closeConnection();
		return pT;
	}

	// [5] ------------------- DELETEByID ------------------------------------
	@Override
	public void deleteById(long pId) throws Exception {
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_deletebyID);
		prStatement.setLong(1, pId);
		prStatement.execute();
		JDBCManager.getInstance().closeConnection();
	}

}
