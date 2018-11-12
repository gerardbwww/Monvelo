package Business.entitie;

public class Moteur {

	private long id;
	private String marque;
	private String modele;
	private long cylindree;
	/**
	 * 
	 */
	public Moteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param marque
	 * @param modele
	 * @param cylindree
	 */
	public Moteur(long id, String marque, String modele, long cylindree) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.cylindree = cylindree;
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
	/**
	 * @return the cylindree
	 */
	public long getCylindree() {
		return cylindree;
	}
	/**
	 * @param cylindree the cylindree to set
	 */
	public void setCylindree(Long cylindree) {
		this.cylindree = cylindree;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "moteur [id=" + id + ", marque=" + marque + ", modele=" + modele + ", cylindree=" + cylindree + "]";
	} 
	
	

}
