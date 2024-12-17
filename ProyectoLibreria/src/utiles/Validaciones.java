package utiles;

import java.util.regex.Pattern;

public class Validaciones {
	
	final static int LONGITUD_ISBN = 13;
	
	/**
	 * Validación de ISBN según unos parámetros (número y longitud correcta).
	 * @param ISBN - El ISBN a validar.
	 * @return true si es un ISBN válido, false si no.
	 */
	public static boolean validarISBN(String ISBN){
		return ISBN.length() == LONGITUD_ISBN && isNumber(ISBN);
	}
	
	/**
	 * Validador de String para campos de texto.
	 * @param nombre - String a validar.
	 * @return true si es un regex correcto, false si no.
	 */
	public static boolean validarNombre(String nombre){
		return Pattern.matches("[a-zA-Z\\s]+", nombre);
	}
	
	/**
	 * Validador de precio en Float, asegurándose que no se introduzcan caracteres erróneos.
	 * @param precio - El precio a validar.
	 * @return true si es un precio válido, false si no.
	 */
	public static boolean validarPrecio(String precio){
		try {
			Float.parseFloat(precio);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validador de números.
	 * @param cadena - Cadena a validar.
	 * @return true si son números, false si no.
	 */
	private static boolean isNumber(String cadena) {
		return Pattern.matches("[0-9]*", cadena);
	}
}
