package cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {

	private ArrayList<Persona> personas;
	private ArrayList<Arista> aristas;
	private Map<Persona, Set<Persona>> vecinos;

	public Grafo() {

		personas = new ArrayList<Persona>();
		aristas = new ArrayList<Arista>();
		vecinos = new HashMap<Persona, Set<Persona>>();

	}

	public void agregarPersona(Persona p) {
		if (p != null && !personas.contains(p)) {
			personas.add(p);
			vecinos.put(p, new HashSet<Persona>());
		}else {
			throw new RuntimeException("persona inválida");
		}
	}

	public void agregarPersonaClique(Persona p) {
		agregarPersona(p);

		for (Persona persona : personas) {
			if (persona != p) {
				agregarArista(persona, p);
			}
		}
		
	}

	public void agregarArista(Persona a, Persona b) {
		if (personas.contains(a) && personas.contains(b)) {
			Arista nueva = new Arista(a, b);
			aristas.add(nueva);
			vecinos.get(a).add(b);
			vecinos.get(b).add(a);
		}
	}

	public void eliminarArista(Persona a, Persona b) {
		Arista arista = buscarArista(a, b);
		if (arista != null) {
			vecinos.get(a).remove(b);
			vecinos.get(b).remove(a);
			aristas.remove(arista);
		}
	}

	private Arista buscarArista(Persona a, Persona b) {
		for (Arista arista : aristas) {
			if (arista.tieneVertice(a) && arista.tieneVertice(b)) {
				return arista;
			}
		}
		return null;
	}

	public boolean tieneCircuito() {
		ArrayList<Persona> personasEnAristas = new ArrayList<Persona>();

		for (Arista arista : aristas) {
			if (!personasEnAristas.contains(arista.getA())) {
				personasEnAristas.add(arista.getA());
			}

			if (!personasEnAristas.contains(arista.getB())) {
				personasEnAristas.add(arista.getB());
			}
		}
		

		return this.aristas.size() >= personasEnAristas.size() ||
				(aristas.size() == (personasEnAristas.size() - 1)  && 
				personasEnAristas.size() != alcanzables(personasEnAristas.get(0)).size());
	}

	public Arista aristaMinima(ArrayList<Arista> aristas) {
		
		Arista min = aristas.get(0);
		for (Arista arista : aristas) {
			if (min.getSimilaridad() > arista.getSimilaridad()) {
				min = arista;
			}
		}
		return min;
	}
	
	public Arista aristaMaxima() {
		Arista max = aristas.get(0);
		for (Arista arista : aristas) {
			if (max.getSimilaridad() < arista.getSimilaridad()) {
				max = arista;
			}
		}
		return max;
	}
	
	public Grafo arbolGeneradorMinimo() {

		ArrayList<Arista> listaAristas = new ArrayList<Arista>(aristas);

		Grafo agm = new Grafo();
		
		for(Persona p: personas) {
			agm.agregarPersona(p);
		}
		
		int i = 1;
		while (i < personas.size() && !listaAristas.isEmpty() ) {
			
			Arista minima = aristaMinima(listaAristas);
			agm.agregarArista(minima.getA(), minima.getB());
			
			if (agm.tieneCircuito()) {
				agm.eliminarArista(minima.getA(), minima.getB());
			} else {
				i += 1;
			}

			listaAristas.remove(minima);

		}
		return agm;
	}

	public Set<Persona> alcanzables(Persona persona) {
		Set<Persona> ret = new HashSet<Persona>();
		ArrayList<Persona> listaDeVecinosPendientes = new ArrayList<Persona>();
		listaDeVecinosPendientes.add(persona);

		while (listaDeVecinosPendientes.size() > 0) {
			Persona i = listaDeVecinosPendientes.get(0);
			ret.add(i);
			
			listaDeVecinosPendientes.addAll(vecinos.get(i));
			listaDeVecinosPendientes.removeAll(ret);
		}

		return ret;
	}

	public ArrayList<Set<Persona>> obtenerGrupos() {
		ArrayList<Set<Persona>> componentes = new ArrayList<Set<Persona>>();
		
		if(personas.size() < 2 ) {
			Set<Persona> setPersonaSola = new HashSet<Persona>();
			if(personas.size() == 1) {
				setPersonaSola.add(personas.get(0));
			}
			componentes.add(setPersonaSola);
			return componentes;
		}
		
		//Paso 1
		Grafo clique = grafoClique(); 
		
		//Paso 2
		Grafo arbolGeneradorMinimo = clique.arbolGeneradorMinimo();
		
		//Paso 3
		Arista max = arbolGeneradorMinimo.aristaMaxima();
		Persona a = max.getA();
		Persona b = max.getB();
		arbolGeneradorMinimo.eliminarArista(a, b);
		
		//Paso 4
		componentes.add(arbolGeneradorMinimo.alcanzables(a));
		componentes.add(arbolGeneradorMinimo.alcanzables(b));
		System.out.println(componentes);
		return componentes;
	}
	
	private Grafo grafoClique() {
		Grafo clique = new Grafo();
		
		for(Persona p: personas) {
			clique.agregarPersonaClique(p);
		}
	
		return clique;
	}
}
