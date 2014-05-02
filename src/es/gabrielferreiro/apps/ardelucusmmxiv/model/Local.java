/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.model;

import java.io.Serializable;
import java.util.Date;

import es.gabrielferreiro.apps.ardelucusmmxiv.db.annotation.Column;
import es.gabrielferreiro.apps.ardelucusmmxiv.db.annotation.Table;

/**
 * @author Gabriel
 *
 */
@Table
public class Local implements Serializable {
	
	public static final String RESTAURACION = "restauracion";
	public static final String NOCTURNO = "nocturno";
	public static final String INFANTIL = "infantil";
	public static final String AMBIENTACION = "ambientacion"; // Cambiar por TIENDA?
	public static final String TIENDA = "tienda";

	private static final long serialVersionUID = -5746361331079117481L;

	@Column(isPrimaryKey = true, isAutoincrement = true)
	private Integer _id;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private String imagen;
	
	@Column(type = "REAL")
	private Float latitud;
	
	@Column(type = "REAL")
	private Float longitud;
	
	@Column
	private String direccion;
	
	@Column
	private String categoria;
	
	@Column(type = "DATETIME")
	private Date horaApertura;
	
	@Column(type = "DATETIME")
	private Date horaCierre;
	
	public Integer getId() {
		return _id;
	}
	public void setId(Integer _id) {
		this._id = _id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}
	public Date getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}
	
}
