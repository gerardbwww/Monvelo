package Business.entitie;

public class Cycliste {

	private long id;
	private String name;
	private Equipe equipe;
	private int nombre_velos;

	/**
	 * 
	 */
	public Cycliste() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param equipe
	 * @param nombre_velos
	 */
	public Cycliste(long id, String name, Equipe equipe, int nombre_velos) {
		super();
		this.id = id;
		this.name = name;
		this.equipe = equipe;
		this.nombre_velos = nombre_velos;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the equipe
	 */
	public Equipe getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe the equipe to set
	 */
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	/**
	 * @return the nombre_velos
	 */
	public int getNombre_velos() {
		return nombre_velos;
	}

	/**
	 * @param nombre_velos the nombre_velos to set
	 */
	public void setNombre_velos(int nombre_velos) {
		this.nombre_velos = nombre_velos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cycliste [id=" + id + ", name=" + name + ", equipe=" + equipe + ", nombre_velos=" + nombre_velos + "]";
	}


}