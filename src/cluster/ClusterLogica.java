package cluster;

import java.util.ArrayList;
import java.util.Set;

public class ClusterLogica {
	
	private ArrayList<Persona> personas;
	private Grafo grafo;
	
	public ClusterLogica() {
		personas = new ArrayList<Persona>();
		grafo = new Grafo();
	}

	public void agregarPersona(String dni, String nombre, Integer deporte, Integer musica, Integer espectaculos,
			Integer ciencia) throws Exception {
		Persona persona = new Persona(nombre, dni);
		persona.cargarInteres("deportes", deporte);
		persona.cargarInteres("música", musica);
		persona.cargarInteres("espectáculos", espectaculos);
		persona.cargarInteres("ciencia", ciencia);
		
		grafo.agregarPersona(persona);
		
	}

	public String obtenerGrupos() {
		
		
		ArrayList<Set<Persona>> gruposGrafo = grafo.obtenerGrupos();
		String mensaje = "";
		
		int i = 1;
		for(Set<Persona> personas : gruposGrafo) {
			mensaje += "Grupo " + i +" : " + personas.toString() + "\n";
			i++;
		}
		
		return mensaje;
	}
	
	

}
