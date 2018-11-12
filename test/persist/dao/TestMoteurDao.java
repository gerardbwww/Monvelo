package persist.dao;

import java.util.List;
import Business.entitie.Moteur;
import Persistence.dao.MoteurDao;
import persist.pere.TU_Pere;

public class TestMoteurDao extends TU_Pere {

	private MoteurDao md;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		md = new MoteurDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// CAS NORMAL
		List<Moteur> list = md.findList();
		int realNb = getInserter().select("select count(id) from moteur").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// CAS NORMAUX

		Moteur moteur = md.findById(3);
		assertEquals(moteur.getId(), 3);

		// CAS LIMITES
		moteur = md.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(moteur);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// CAS NORMAL
		Moteur moteur = new Moteur(0, "Dacia", "Sandero", 1100);
		int nbAvant = getInserter().select("select count(id) from moteur").getDataAsInt();
		md.create(moteur);
		int nbApres = getInserter().select("select count(id) from moteur").getDataAsInt();
		assertEquals(nbApres, nbAvant + 1);
		// TEST DE TAILLE
		// assertEquals(md.findList().size(), 13);
		// Test CHAMPS
		Moteur fFind = md.findById(moteur.getId());
		assertEquals(moteur.getId(), fFind.getId());
		assertEquals(moteur.getMarque(), fFind.getMarque());
		assertEquals(moteur.getModele(), fFind.getModele());
		assertEquals(moteur.getCylindree(), fFind.getCylindree());
		// CAS LIMITE
		Moteur result = md.create(null);
		assertNull(result);

	}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {
		// CAS NORMAUX
		Moteur moteur = new Moteur(2, "Jeep", "Renegade", 2300);
		md.updateById(moteur);
		Moteur aFind = md.findById(moteur.getId()); // affectation sur objet aFind par la méthode FindById;
		assertEquals(moteur.getId(), aFind.getId());
		assertEquals(moteur.getMarque(), aFind.getMarque());
		assertEquals(moteur.getModele(), aFind.getModele());

		// CAS LIMITE
		Moteur result = md.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------

	public void testDeleteById() throws Exception {
		// CAS NORMAL
		Moteur moteur = new Moteur(0, "Dacia", "Sandero", 1100);
		md.create(moteur);
		List<Moteur> list = md.findList();
		int nbInit = list.size();
		md.deleteById(moteur.getId());
		list = md.findList();
		assertEquals(nbInit - 1, list.size());
		// CAS LIMITE
		md.deleteById(0); // N'EXISTE PAS
		list = md.findList();
		assertEquals(list.size(), nbInit - 1);
	}
}