package modelo;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private Float precio;
	private Integer cantidad;
	private String formato;
	private String edicion;

	public Libro(String iSBN, String titulo, String autor, String editorial, Float precio,
			String formato, String edicion) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.cantidad = 1;
		this.formato = formato;
		this.edicion = edicion;
	}

	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public Float getPrecio() {
		return precio;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public String getFormato() {
		return formato;
	}


	public void setFormato(String formato) {
		this.formato = formato;
	}


	public String getEdicion() {
		return edicion;
	}


	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}


	public String getISBN() {
		return ISBN;
	}


	@Override
	public String toString() {
		return "\nISBN: " + ISBN + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial
				+ "\nPrecio: " + precio + "\nCantidad: " + cantidad + "\nFormato: " + formato + "\nEdicion: " + edicion;
	}

}
