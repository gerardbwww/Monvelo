package persist.dao;

import java.util.List;

import Business.entitie.Equipe;
import Persistence.dao.EquipeDao;
import persist.pere.TU_Pere;

public class TestEquipeDao extends TU_Pere {

	private EquipeDao ed;

	public void setUp() throws Exception {

		// TODO Auto-generated method stub
		super.setUp();
		ed = new EquipeDao();
	}

	// [1] ---------------- TestFINDLIST -------------------------------
	public void testFindList() throws Exception {

		// # CAS NORMAUX #
		// TEST TAILLE
		List<Equipe> list = ed.findList();
		int realNb = getInserter().select("select count(id) from equipe").getDataAsInt();
		assertEquals(list.size(), realNb);

	}

	// [2] ------------------ TestFINDByID -----------------------------
	public void testFindById() throws Exception {

		// # CAS NORMAUX #
		// TEST ID
		Equipe equipe = ed.findById(3);
		assertEquals(equipe.getId(), 3);

		// # CAS LIMITES #
		equipe = ed.findById(0); // VALEUR N'EXISTANT PAS
		assertNull(equipe);

	}

	// [3] ----------------------testCREATE-------------------------------------
	public void testCreate() throws Exception {

		// # CAS NORMAUX #
		// TEST TAILLE
		Equipe equipe = new Equipe(0, "Moviestar", 15000);
		int nbAvant = getInserter().select("select count(id) from equipe").getDataAsInt();
		ed.create(equipe);
		int nbApres = getInserter().select("select count(id) from equipe").getDataAsInt();
		assertEquals(nbApres, nbAvant + 1);

		// TEST CHAMPS
		Equipe eFind = ed.findById(equipe.getId());
		assertEquals(equipe.getId(), eFind.getId());
		assertEquals(equipe.getName(), eFind.getName());
		assertEquals(equipe.getBugdet(), eFind.getBugdet());

		// # CAS LIMITES #
		Equipe result = ed.create(null);
		assertNull(result);

	}

	// [4] -------------------- testUPDATEByID --------------------------------
	public void testUpdateById() throws Exception {

		// # CAS NORMAUX #
		// TEST CHAMPS
		Equipe equipe = new Equipe(2, "Cofidis", 10000); // FAUX
		ed.updateById(equipe);
		Equipe aFind = ed.findById(equipe.getId()); // affectation sur objet aFind par la méthode FindById;
		assertEquals(equipe.getId(), aFind.getId());
		assertEquals(equipe.getName(), aFind.getName());
		assertEquals(equipe.getBugdet(), aFind.getBugdet());

		// # CAS LIMITE #
		Equipe result = ed.updateById(null);
		assertNull(result);
	}

	// [5] ------------------ TestDELETEByID ------------------------------------

	public void testDeleteById() throws Exception {

		// # CAS NORMAUX #
		Equipe equipe = new Equipe(2, "Cofidis", 10000);
		ed.create(equipe);
		// TEST NOMBRE
		List<Equipe> list = ed.findList();
		int nbInit = list.size();
		ed.deleteById(equipe.getId());
		list = ed.findList();
		assertEquals(nbInit - 1, list.size());

		// # CAS LIMITES #
		// ID N'EXISTE PAS
		ed.deleteById(0);
		list = ed.findList();
		assertEquals(list.size(), nbInit - 1);
		// ID EST NEGATIF
		ed.deleteById(-1);
		list = ed.findList();
		assertEquals(list.size(), nbInit - 1);
	}
}