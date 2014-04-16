/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public class MockEventoDao implements EventoDao {

	private List<Evento> mockEvents;
	
	public MockEventoDao() {
		this.mockEvents = cargarDatosPrueba();
	}
	
	private List<Evento> cargarDatosPrueba() {
		List<Evento> data = new ArrayList<Evento>();
		Evento evento1 = new Evento();
		evento1.setId(1);
		evento1.setTitulo("Circo Romano");
		evento1.setDescripcion("Luchadores de todas partes del imperio se dan cita para luchar hasta la muerte");
		evento1.setTiempoRealizacion(new Date());
		evento1.setPrecio("3.0");
		
		Evento evento2 = new Evento();
		evento2.setId(2);
		evento2.setTitulo("Desfile Romano");
		evento2.setDescripcion("Las legiones pasean por el centro de la ciudad.");
		evento2.setTiempoRealizacion(new Date());
		evento2.setPrecio("0.0");
		
		data.add(evento1);
		data.add(evento2);
		
		return data;
	}
	
	@Override
	public Evento find(Integer objId) {
		for (Evento evento : mockEvents) {
			if (evento.getId() == objId) {
				return evento;
			}
		}
		return null;
	}

	@Override
	public List<Evento> findAll() {
		return mockEvents;
	}

	@Override
	public Integer save(Evento obj) {
		mockEvents.add(obj);
		return obj.getId();
	}

}
