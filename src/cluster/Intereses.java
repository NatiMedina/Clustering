package cluster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Intereses {

	private HashMap<String, Integer> tema_interes;
	private static List<String> listaTemasDeInteres = Arrays.asList("deportes", "música",
			"espectáculos", "ciencia");

	public Intereses() {
		tema_interes = new HashMap<String, Integer>(listaTemasDeInteres.size());
		for (String interes : listaTemasDeInteres) {
			tema_interes.put(interes, 1);
		}
	}
	
	public static void agregarInteres(String nuevo) {
		listaTemasDeInteres.add(nuevo);	
	}
	
	public static List<String> mostrarListaDeIntereses() {
		return listaTemasDeInteres;
	}

	public void puntuarInteres(String tema, Integer valor) throws Exception {
		if (listaTemasDeInteres.contains(tema)) {
			if (valor != null && valor > 0 && valor <= 5) {
				tema_interes.put(tema, valor);
			} else {
				throw new Exception("Ingreso un valor inválido, debe ser de 1 a 5");
			}
		} else {
			throw new Exception("no existe esa descripción en la lista de los intereses");
		}
	}
	
	public Integer getValorInteres(String tema) {
		return tema_interes.get(tema);
	}

	public HashMap<String, Integer> getTema_interes() {
		return tema_interes;
	}

	public Integer calcularIndiceDeSimilaridad(Intereses interesesOtro) {
		Integer similaridad = 0;
		
		for(String tema: listaTemasDeInteres) {
			similaridad += Math.abs(this.getValorInteres(tema) - interesesOtro.getValorInteres(tema));
		}
		
		return similaridad;
	}

}
