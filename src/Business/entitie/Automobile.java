package Business.entitie;

public class Automobile {

	private long id;
	private String marque;
	private String modele;
	private Moteur moteur;
	private Frein frein;
	/**
	 * 
	 */
	public Automobile() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param marque
	 * @param modele
	 * @param moteur
	 * @param frein
	 */
	public Automobile(long id, String marque, String modele, Moteur moteur, Frein frein) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.moteur = moteur;
		this.frein = frein;
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
	 * @return the moteur
	 */
	public Moteur getMoteur() {
		return moteur;
	}
	/**
	 * @param moteur the moteur to set
	 */
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	/**
	 * @return the frein
	 */
	public Frein getFrein() {
		return frein;
	}
	/**
	 * @param frein the frein to set
	 */
	public void setFrein(Frein frein) {
		this.frein = frein;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Automobile [id=" + id + ", marque=" + marque + ", modele=" + modele + ", moteur=" + moteur + ", frein="
				+ frein + "]";
	}
	

}
