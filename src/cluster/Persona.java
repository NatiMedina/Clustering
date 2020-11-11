package cluster;

public class Persona {
	
	private String nombre;
	private String dni;
	private Intereses intereses;
	
	public Persona(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
		intereses = new Intereses();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Intereses getIntereses() {
		return intereses;
	}

	public void setIntereses(Intereses intereses) {
		this.intereses = intereses;
	}
	
	public void cargarInteres(String tema, Integer valor) throws Exception {
		this.intereses.puntuarInteres(tema, valor);
	}

	public Integer similaridad(Persona b) {
		return intereses.calcularIndiceDeSimilaridad(b.getIntereses());
	}

	@Override
	public String toString() {
		return this.dni + " " + this.nombre;
	}
	
	
	
}
