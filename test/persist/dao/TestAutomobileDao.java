package persist.dao;

import java.util.List;
import Business.entitie.Automobile;
import Business.entitie.Frein;
import Business.entitie.Moteur;
import Persistence.dao.AutomobileDao;
import Persistence.dao.FreinDao;
import Persistence.dao.MoteurDao;
import persist.pere.TU_Pere;

public class TestAutomobileDao extends TU_Pere {

	private AutomobileDao ad;
	private MoteurDao md;
	private FreinDao fd;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		ad = new AutomobileDao();
		md = new MoteurDao();
		fd = new FreinDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// CAS NORMAUX
		List<Automobile> list = ad.findList();
		int realNb = getInserter().select("select count(id) from automobile").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// CAS NORMAUX
		Automobile automobile = ad.findById(1);
		assertEquals(automobile.getId(), 1);
		// CAS LIMITES
		automobile = ad.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(automobile);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// CAS NORMAL
		Moteur moteur = md.findById(1);
		Frein frein = fd.findById(1);
		Automobile automobile = new Automobile(0, "Dacia", "Sandero", moteur, frein); // construction automobile
		int nbAvant = getInserter().select("select count(id) from automobile").getDataAsInt();
		ad.create(automobile); // création automobile
		int nbApres = getInserter().select("select count(id) from automobile").getDataAsInt();
		assertEquals(nbApres, nbAvant + 1);
		Automobile aFind = ad.findById(automobile.getId()); // affectation sur objet aFind par la méthode FindById

		// LES CHAMPS AUTOMOBILE
		assertEquals(automobile.getId(), aFind.getId());
		assertEquals(automobile.getMarque(), aFind.getMarque());
		assertEquals(automobile.getModele(), aFind.getModele());

		// LES CHAMPS MOTEUR
		assertEquals(automobile.getMoteur().getId(), aFind.getMoteur().getId());
		assertEquals(automobile.getMoteur().getMarque(), aFind.getMoteur().getMarque());
		assertEquals(automobile.getMoteur().getModele(), aFind.getMoteur().getModele());
		assertEquals(automobile.getMoteur().getCylindree(), aFind.getMoteur().getCylindree());

		// LES CHAMPS FREIN
		assertEquals(automobile.getFrein().getId(), aFind.getFrein().getId());
		assertEquals(automobile.getFrein().getMarque(), aFind.getFrein().getMarque());
		assertEquals(automobile.getFrein().getModele(), aFind.getFrein().getModele());

		// CAS LIMITE
		Automobile result = ad.create(null);
		assertNull(result);
	}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {
		// CAS NORMAL
		Moteur moteur = md.findById(1);
		Frein frein = fd.findById(1);

		Automobile automobile = new Automobile(1, "Dacia", "Duster", moteur, frein);
		ad.updateById(automobile);
		Automobile aFind = ad.findById(automobile.getId()); // affectation sur objet aFind par la méthode FindById;

		// LES CHAMPS AUTOMOBILE
		assertEquals(automobile.getId(), aFind.getId());
		assertEquals(automobile.getMarque(), aFind.getMarque());
		assertEquals(automobile.getModele(), aFind.getModele());

		// LES CHAMPS MOTEUR
		assertEquals(automobile.getMoteur().getId(), aFind.getMoteur().getId());
		assertEquals(automobile.getMoteur().getMarque(), aFind.getMoteur().getMarque());
		assertEquals(automobile.getMoteur().getModele(), aFind.getMoteur().getModele());
		assertEquals(automobile.getMoteur().getCylindree(), aFind.getMoteur().getCylindree());

		// LES CHAMPS FREIN
		assertEquals(automobile.getFrein().getId(), aFind.getFrein().getId());
		assertEquals(automobile.getFrein().getMarque(), aFind.getFrein().getMarque());
		assertEquals(automobile.getFrein().getModele(), aFind.getFrein().getModele());

		// // CAS LIMITE
		Automobile result = ad.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------
	public void testDeleteById() throws Exception {
		// CAS NORMAL
		Moteur moteur = md.findById(1);
		Frein frein = fd.findById(1);
		Automobile automobile = new Automobile(1, "Dacia", "Duster", moteur, frein);
		ad.updateById(automobile);
		List<Automobile> list = ad.findList();
		int nbInit = list.size();
		ad.deleteById(automobile.getId());
		list = ad.findList();
		assertEquals(nbInit - 1, list.size());
		// CAS LIMITE
		fd.deleteById(0); // N'EXISTE PAS
		list = ad.findList();
		assertEquals(list.size(), nbInit - 1);

	}
}
