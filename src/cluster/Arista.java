package cluster;

public class Arista {

	private Persona a;
	private Persona b;
	private Integer similaridad;
	
	public Arista(Persona a, Persona b) {
		this.a = a;
		this.b = b;
		this.similaridad = a.similaridad(b);
	}
	
	public Integer getSimilaridad() {
		return similaridad;
	}

	public boolean tieneVertice(Persona persona) {
		return a.equals(persona) || b.equals(persona);
	}
	
	public Persona getA() {
		return a;
	}

	public Persona getB() {
		return b;
	}

	@Override
	public String toString() {
		return a.getNombre() + " --- "+ b.getNombre()+ " = "+ similaridad ;
	}

		
}
