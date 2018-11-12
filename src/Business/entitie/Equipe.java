package Business.entitie;

public class Equipe {

	private long id;
	private String name;
	private int bugdet;

	/**
	 * 
	 */
	public Equipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param bugdet
	 */
	public Equipe(long id, String name, int bugdet) {
		super();
		this.id = id;
		this.name = name;
		this.bugdet = bugdet;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bugdet
	 */
	public int getBugdet() {
		return bugdet;
	}

	/**
	 * @param bugdet
	 *            the bugdet to set
	 */
	public void setBugdet(int bugdet) {
		this.bugdet = bugdet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Equipe [id=" + id + ", name=" + name + ", bugdet=" + bugdet + "]";
	}

}
