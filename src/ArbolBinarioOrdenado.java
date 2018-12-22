
public class ArbolBinarioOrdenado {
	class Nodo {
		int info;
		Nodo izq, der;
	}
	Nodo raiz;
	int cant, altura;
	String dir;
	
	public ArbolBinarioOrdenado() {
		raiz=null;
	}
	
	public void insertar (int info) {
		Nodo nuevo;
		nuevo = new Nodo ();
		nuevo.info = info;
		nuevo.izq = null;
		nuevo.der = null;
		if (raiz == null) {
			raiz = nuevo;
		}	
		else {
			Nodo anterior = null, reco;
			reco = raiz;
			while (reco != null) {
				anterior = reco;
				if (info < reco.info) {
					reco = reco.izq;
				}	
				else {
					reco = reco.der;
				}	
			}
			if (info < anterior.info) {
				anterior.izq = nuevo;
				dir = "izquierda";
			}
			else {
				anterior.der = nuevo;
				dir = "derecha";
			}	
		}
	}
	
	private void imprimirConPadre (Nodo reco, String dir) {
		if (reco != null) {
			System.out.println(reco.info + " "+dir);
			imprimirConPadre(reco.izq, " por izquierda del nodo "+ reco.info);
			imprimirConPadre(reco.der, " por derecha del nodo "+ reco.info);
		}
	}
	public void imprimirConPadre() {
		imprimirConPadre(raiz, dir = "Nodo raiz del árbol.");
		System.out.println();
	}
	
	private void existePrimerosTresNiveles(Nodo reco, int nivel) {
		if (reco != null) {
			existePrimerosTresNiveles (reco.izq,nivel+1);
			if (nivel>altura) {
				altura=nivel;
			}	
			existePrimerosTresNiveles (reco.der,nivel+1);
		}
	}
	public int existePrimerosTresNiveles() {
		altura=0;
		existePrimerosTresNiveles(raiz,1);
		return altura;
	}
	
	private void mayorIzq(Nodo reco) {
		if (reco == null) {
			System.out.println("El árbol está vacío.");
		}
		else if (reco != null && reco.izq == null) {
			System.out.println("El sub-árbol izquierdo está vacío.");
		}
		else if (reco.izq != null) {
			reco = reco.izq;
			while (reco.der != null) {
				reco = reco.der;
			}
			System.out.println("El mayor valor del sub-árbol izquierdo es: "+reco.info);
		}
	}	
	public void mayorIzq() {
		mayorIzq(raiz);
	}
	
	private void menorIzq(Nodo reco) {
		if (reco == null) {
			System.out.println("El árbol está vacío.");
		}
		else if (reco.izq == null) {
			System.out.println("El sub-árbol izquierdo está vacío.");
		}
		else {
			while (reco.izq != null) {
				reco = reco.izq;
			}
			System.out.println("El menor valor del sub-árbol izquierdo es: "+reco.info);
		}
	}
	public void menorIzq() {
		menorIzq(raiz);
	}
	
	private void cantidadNodosHoja(Nodo reco) {
		if (reco!=null) {
			if (reco.izq == null && reco.der == null) {
				cant++;
			}
			else {
				cantidadNodosHoja(reco.izq);
				cantidadNodosHoja(reco.der);
			}
		}
	}	
	public void cantidadNodosHoja() {
		cant=0;
		cantidadNodosHoja(raiz.izq);
		System.out.println("El sub-árbol izquierdo contiene los siguientes nodos hoja: "+cant);
	}

	public void borrarMayorIzq() {
		Nodo reco=raiz.izq;
		if (reco.izq != null || reco.der != null) {
			Nodo atras=raiz;
			if (reco.izq != null && reco.der == null) {
				atras.izq = reco.izq;
			}
			else {
				while (reco.der!=null) {
					atras=reco;
					reco=reco.der;
				}
				atras.der=reco.izq;
			}
		}
		else {
			raiz.izq = null;
		}
	}
	
	public static void main (String [] ar) {
		ArbolBinarioOrdenado abo = new ArbolBinarioOrdenado();
		abo.insertar (10);
		abo.insertar (7);
		abo.insertar (9);
		abo.insertar (8);
		abo.insertar (20);
		abo.insertar (22);
		abo.insertar (24);
		abo.insertar (26);
		abo.insertar (1);
		abo.insertar (4);
		abo.insertar (3);
		abo.insertar (2);
		System.out.println("------------                 RESULTADOS               ------------");
		System.out.println();
		System.out.println("------------ Impresion de nodos insertados: ------------");
		System.out.println();
		abo.imprimirConPadre();
		abo.menorIzq();
		abo.mayorIzq();
		abo.cantidadNodosHoja();
		System.out.println("La información se extiende hasta el nivel: "+abo.existePrimerosTresNiveles());
		System.out.println();
		System.out.println("*****  A continuación borramos el numero mayor del sub-árbol izquierdo.");
		abo.borrarMayorIzq();
		System.out.println();
		System.out.println("------------ Impresion de resultados luego de la modificación: ------------");
		System.out.println();
		abo.imprimirConPadre();
		abo.menorIzq();
		abo.mayorIzq();
		abo.cantidadNodosHoja();
		System.out.println("La información se extiende hasta el nivel: "+abo.existePrimerosTresNiveles());
		System.out.println();
		System.out.println("------------ FIN DE LOS RESULTADOS ------------");
	}
}