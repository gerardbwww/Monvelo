package Persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Business.entitie.Cycliste;
import Business.entitie.Equipe;
import Persistence.Manager.JDBCManager;


public class CyclisteDao implements IDAO<Cycliste> {

	public static final String sql_selectC = "SELECT * FROM cycliste inner join equipe on equipe.id = cycliste.equipe_id";	
	public static final String sql_selectbyID = "SELECT * FROM cycliste inner join equipe on equipe.id = cycliste.equipe_id WHERE cycliste.id = ?";
	public static final String sql_Insert = "INSERT INTO cycliste (name, equipe_id, nombre_velos) values (?,?,?)";
	public static final String sql_updateByID = "UPDATE cycliste SET name = ?, equipe_id = ?, nombre_velos = ? WHERE id = ?";
	public static final String sql_deletebyID = "DELETE FROM cycliste WHERE id = ?";

	// [1] ----------------- FINDLIST ----------------------------------
	@Override
	public List<Cycliste> findList() throws Exception {
		List<Cycliste> list = new ArrayList<>();
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_selectC);
		ResultSet st = prStatement.executeQuery();

		while (st.next()) {
			long id = st.getLong("cycliste.id");
			String name = st.getString("cycliste.name");
			int nombre_velos = st.getInt("cycliste.nombre_velos");
			long equipe_id = st.getLong("equipe.id");
			String nameE = st.getString("equipe.name");
			int budget = st.getInt("equipe.budget");
			/* ... */
			Equipe equipe = new Equipe(equipe_id, nameE, budget);
			Cycliste cycliste = new Cycliste(id, name, equipe, nombre_velos);
			
			list.add(cycliste);
		}
		JDBCManager.getInstance().closeConnection();
		return list;

	}

	// [2] ------------------ FINDBYID -----------------------------
	@Override
	public Cycliste findById(long pId) throws Exception {

		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_selectbyID);
		prStatement.setLong(1, pId);
		ResultSet st = prStatement.executeQuery();

		//
		Cycliste cycliste = null;
		while (st.next()) {
			long id = st.getLong("cycliste.id");
			String name = st.getString("cycliste.name");
			int nombre_velos = st.getInt("cycliste.nombre_velos");
			long equipe_id = st.getLong("equipe.id");
			String nameE = st.getString("equipe.name");
			int budget = st.getInt("equipe.budget");
			/* ... */
			Equipe equipe = new Equipe(equipe_id, nameE, budget);
			cycliste = new Cycliste(id, name, equipe, nombre_velos);

		}
		JDBCManager.getInstance().closeConnection();
		return cycliste;
	}

	// [3] ----------------------CREATE-------------------------------------
	@Override
	public Cycliste create(Cycliste pT) throws Exception {
		if (pT == null) 
			return null;
		// retourne null si paramètre null
		Connection cnx = JDBCManager.getInstance().openConection();
		PreparedStatement prStatement = cnx.prepareStatement(sql_Insert, PreparedStatement.RETURN_GENERATED_KEYS);

		prStatement.setString(1, pT.getName());
		prStatement.setLong(2, pT.getEquipe().getId());
		prStatement.setInt(3, pT.getNombre_velos());

	

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
	public Cycliste updateById(Cycliste pT) throws Exception {
		if (pT == null)
			return null; 
		// retourne null si paramètre null

		Connection cnx = JDBCManager.getInstance().openConection();

		PreparedStatement prStatement = cnx.prepareStatement(sql_updateByID);
		
		prStatement.setString(1, pT.getName());
		prStatement.setLong(2, pT.getEquipe().getId());
		prStatement.setLong(3, pT.getNombre_velos());
		prStatement.setLong(4, pT.getId());
		
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
