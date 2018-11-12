package persist.dao;

import java.util.List;

import Business.entitie.Frein;
import Persistence.dao.FreinDao;
import persist.pere.TU_Pere;

public class TestFreinDao extends TU_Pere {

	private FreinDao fd;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		fd = new FreinDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// CAS NORMAL
		List<Frein> list = fd.findList();
		int realNb = getInserter().select("select count(id) from frein").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// CAS NORMAUX

		Frein frein = fd.findById(3);
		assertEquals(frein.getId(), 3);

		// CAS LIMITES
		frein = fd.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(frein);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// CAS NORMAL
		Frein frein = new Frein(0, "VALEO", "15FE");
		int nbAvant = getInserter().select("select count(id) from frein").getDataAsInt();
		fd.create(frein);
		int nbApres = getInserter().select("select count(id) from frein").getDataAsInt();
		assertEquals(nbApres, nbAvant + 1);
		// test de taille
		// assertEquals(fd.findList().size(), 13);
		// Test CHAMPS
		Frein fFind = fd.findById(frein.getId());
		assertEquals(frein.getId(), fFind.getId());
		assertEquals(frein.getMarque(), fFind.getMarque());
		assertEquals(frein.getModele(), fFind.getModele());
		// CAS LIMITE
		Frein result = fd.create(null);
		assertNull(result);

	}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {
		// CAS NORMAUX
		Frein frein = new Frein(2, "VALEO", "453BZ");
		fd.updateById(frein);
		Frein aFind = fd.findById(frein.getId()); // affectation sur objet aFind par la méthode FindById;
		assertEquals(frein.getId(), aFind.getId());
		assertEquals(frein.getMarque(), aFind.getMarque());
		assertEquals(frein.getModele(), aFind.getModele());

		// CAS LIMITE
		Frein result = fd.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------

	public void testDeleteById() throws Exception {
		// CAS NORMAL
		Frein frein = new Frein(0, "VALEO", "15FE");
		fd.create(frein);
		List<Frein> list = fd.findList();
		int nbInit = list.size();
		fd.deleteById(frein.getId());
		list = fd.findList();
		assertEquals(nbInit - 1, list.size());
		// CAS LIMITE
		fd.deleteById(0); // N'EXISTE PAS
		list = fd.findList();
		assertEquals(list.size(), nbInit - 1);
	}
}