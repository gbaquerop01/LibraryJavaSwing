package controlador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import modelo.Libro;

public class Libreria {
	private ArrayList<Libro> arrayLibros = new ArrayList<Libro>();

	/**
	 * Añade un libro a la librería.
	 * 
	 * @param libro - Objeto Libro a añadir.
	 */
	public void añadirLibro(Libro libro) {
		arrayLibros.add(libro);
		sortLibreria();
		System.out.println("----------------" + arrayLibros.get(arrayLibros.size() - 1).toString());
	}

	/**
	 * Devuelve la lista de libros de la librería.
	 * 
	 * @return La lista.
	 */
	public ArrayList<Libro> getLibros() {
		return arrayLibros;
	}

	/**
	 * Elimina un libro según su ISBN.
	 * 
	 * @param ISBN - El ISBN del libro a eliminar.
	 * @return true si se ha eliminado el libro, false en lo contrario.
	 */
	public boolean removeLibro(String ISBN) {
		int index = 0;
		for (Libro libro : arrayLibros) {
			if (libro.getISBN().equals(ISBN)) {
				arrayLibros.remove(index);
				return true;
			}
			index++;
		}
		return false;
	}

	/**
	 * Modifica un libro según su ISBN, el campo a modificar y el nuevo valor del
	 * campo a modificar.
	 * 
	 * @param ISBN       - El ISBN del libro a modificar.
	 * @param campo      - El campo del libro que vamos a modificar.
	 * @param nuevoValor - El nuevo valor del campo del libro a modificar.
	 * @return true si se ha modificado, false en lo contrario.
	 */
	public boolean modificarLibro(String ISBN, String campo, String nuevoValor) {
		for (Libro libro : arrayLibros) {
			if (libro.getISBN().equals(ISBN)) {
				switch (campo.toLowerCase()) {
				case "título":
					libro.setTitulo(nuevoValor);
					break;
				case "autor":
					libro.setAutor(nuevoValor);
					break;
				case "editorial":
					libro.setEditorial(nuevoValor);
					break;
				case "precio":
					try {
						float nuevoPrecio = Float.parseFloat(nuevoValor);
						libro.setPrecio(nuevoPrecio);
					} catch (NumberFormatException e) {
						System.out.println("Precio incorrecto");
						return false;
					}
					break;
				case "formato":
					if (isValidFormato(nuevoValor)) {
						libro.setFormato(nuevoValor);
					} else {
						System.out.println("Formato no válido. Debe ser uno de: Cartoné, Grapada, Rústica, Espiral");
						return false;
					}
					break;
				case "edición":
					if (isValidEdicion(nuevoValor)) {
						libro.setEdicion(nuevoValor);
					} else {
						System.out.println("Edición no válida. Debe ser Reedición o Novedad.");
						return false;
					}
					break;
				default:
					System.out.println("Campo no válido");
					return false;
				}
				sortLibreria();
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba si existe el ISBN dentro de la librería.
	 * 
	 * @param ISBN - El ISBN a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeISBN(String ISBN) {
		for (Libro libro : arrayLibros) {
			if (libro.getISBN().equals(ISBN)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Busca un libro por ISBN.
	 * 
	 * @param ISBN - El ISBN a buscar.
	 * @return El libro en caso de que se haya encontrado, null si no se ha
	 *         encontrado libro.
	 */
	public Libro buscarLibroPorISBN(String ISBN) {
		for (Libro libro : arrayLibros) {
			if (libro.getISBN().equals(ISBN)) {
				return libro;
			}
		}
		return null;
	}

	/**
	 * Organiza la librería en orden ascendente según su título (case-insensitive)
	 */
	public void sortLibreria() {
		this.arrayLibros.sort(new Comparator<Libro>() {

			@Override
			public int compare(Libro o1, Libro o2) {
				return o1.getTitulo().toLowerCase().compareTo(o2.getTitulo().toLowerCase());
			}

		});
	}

	/**
	 * Método de ayuda para añadir libros a probar.
	 */
	public void addRandomBooks() {
		Random rand = new Random();
		String[] titulos = { "El Quijote", "Cien Años de Soledad", "Donde los Árboles Cantan", "Harry Potter",
				"El Hobbit", "La Sombra del Viento", "El Principito", "Drácula", "1984", "Fahrenheit 451",
				"Orgullo y Prejuicio", "La Isla del Tesoro", "Los Miserables", "La Divina Comedia", "Moby Dick" };

		String[] autores = { "Miguel de Cervantes", "Gabriel García Márquez", "Laura Gallego", "J.K. Rowling",
				"J.R.R. Tolkien", "Carlos Ruiz Zafón", "Antoine de Saint-Exupéry", "Bram Stoker", "George Orwell",
				"Ray Bradbury", "Jane Austen", "Robert Louis Stevenson", "Victor Hugo", "Dante Alighieri",
				"Herman Melville" };

		String[] editoriales = { "Penguin", "Planeta", "Santillana", "SM", "Anagrama", "Siruela", "Salamandra",
				"Cátedra", "Random House", "Tusquets", "Hola", "Bye", "Adele", "Catorce", "Quince" };

		for (int i = 0; i < 15; i++) {
			String ISBN = generateISBN(rand);
			String titulo = titulos[i % titulos.length];
			String autor = autores[i % autores.length];
			String editorial = editoriales[i % autores.length];

			Float precioInicial = 10.0f + rand.nextFloat() * 40.0f;
			Float precio = Math.round(precioInicial * 100.0f) / 100.0f;

			Libro libro = new Libro(ISBN, titulo, autor, editorial, precio, "Cartoné", "Novedad");
			añadirLibro(libro);
		}

		sortLibreria();
	}

	/**
	 * Método de ayuda para generar un ISBN aleatorio.
	 * 
	 * @param rand - Objeto Random para generar números.
	 * @return El ISBN aleatorio.
	 */
	private String generateISBN(Random rand) {
		StringBuilder isbn = new StringBuilder();
		for (int i = 0; i < 13; i++) {
			isbn.append(rand.nextInt(10));
		}
		return isbn.toString();
	}

	/**
	 * Comprueba que el formato introducido coincida con los cuatro formatos
	 * permitidos (Cartoné, Grapada, Rústica, Espiral).
	 * 
	 * @param formato - String a validar.
	 * @return true si es un formato válido, false si no.
	 */
	private boolean isValidFormato(String formato) {
		String[] formatosValidos = { "Cartone", "Grapada", "Rustica", "Espiral" };
		for (String formatoValido : formatosValidos) {
			if (formatoValido.equalsIgnoreCase(formato)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Comprueba que la edición introducida coincida con las dos ediciones
	 * permitidas (Reedición, Novedad).
	 * 
	 * @param edición - String a validar.
	 * @return true si es una edición válida, false si no.
	 */
	private boolean isValidEdicion(String edición) {
		String[] edicionesValidas = { "Reedicion", "Novedad" };
		for (String ediciónValida : edicionesValidas) {
			if (ediciónValida.equalsIgnoreCase(edición)) {
				return true;
			}
		}
		return false;
	}
}
