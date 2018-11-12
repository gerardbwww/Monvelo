package persist.pere;

import java.util.Properties;

import org.junit.Ignore;

import Persistence.Manager.CfgManager;
import inserter.Inserter;
import junit.framework.TestCase;

@Ignore
public class TU_Pere extends TestCase {
	private Inserter inserter = null;
	protected Properties testCfg = null;
	public void setUp() throws Exception {
		super.setUp();
		System.setProperty(CfgManager.OTHER_CFG_FILE, "files/testcfg.ini");
		testCfg = CfgManager.getInstance().getCfg();
		inserter = new Inserter(testCfg);

		inserter.insert("create.sql");
	}
	public void tearDown() throws Exception {
		super.tearDown();
		inserter.insert("drop.sql");
	}
	public Inserter getInserter() {
		return inserter;
	}
}
