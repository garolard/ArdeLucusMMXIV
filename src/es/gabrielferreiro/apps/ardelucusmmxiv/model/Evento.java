/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gabriel
 *
 */
public class Evento implements Serializable {

	private static final long serialVersionUID = 9189979138757118477L;
	
	public static final String ROMANO = "romano";
	public static final String CASTREXO = "castrexo";
	public static final String INFANTIL = "infantil";
	public static final String NOCTURNO = "nocturno";

	private Integer _id;
	private String titulo;
	private String descripcion;
	private Double latitud;
	private Double longitud;
	private Date tiempo_realizacion;
	private String precio;
	private String categoria;
	
	public Integer getId() {
		return _id;
	}
	public void setId(Integer _id) {
		this._id = _id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Date getTiempoRealizacion() {
		return tiempo_realizacion;
	}
	public void setTiempoRealizacion(Date tiempo_realizacion) {
		this.tiempo_realizacion = tiempo_realizacion;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
