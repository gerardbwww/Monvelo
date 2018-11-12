package Business.entitie;

public class Frein {
	
	private long id;
	private String marque;
	private String modele;
	/**
	 * 
	 */
	public Frein() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param marque
	 * @param modele
	 */
	public Frein(long id, String marque, String modele) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}
	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}
	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "frein [id=" + id + ", marque=" + marque + ", modele=" + modele + "]";
	}


}
