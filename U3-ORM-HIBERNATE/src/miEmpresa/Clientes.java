package miEmpresa;
// Generated 12 ene 2024 17:06:13 by Hibernate Tools 6.2.13.Final

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private int id;
	private String nombre;
	private String pais;

	public Clientes() {
	}

	public Clientes(int id, String nombre, String pais) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
