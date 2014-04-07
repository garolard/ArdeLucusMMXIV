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

	private Integer _id;
	private String titulo;
	private String descripcion;
	private Float latitud;
	private Float longitud;
	private Date tiempo_realizacion;
	private Float precio;
	
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
	public Float getLatitud() {
		return latitud;
	}
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}
	public Float getLongitud() {
		return longitud;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	public Date getTiempoRealizacion() {
		return tiempo_realizacion;
	}
	public void setTiempoRealizacion(Date tiempo_realizacion) {
		this.tiempo_realizacion = tiempo_realizacion;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
}
