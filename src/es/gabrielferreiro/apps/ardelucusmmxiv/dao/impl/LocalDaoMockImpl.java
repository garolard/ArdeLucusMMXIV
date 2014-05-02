/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

/**
 * @author Gabriel
 *
 */
public class LocalDaoMockImpl implements LocalDao {

	private List<Local> mockLocals;
	
	public LocalDaoMockImpl() {
		mockLocals = cargarDatosPrueba();
	}
	
	private List<Local> cargarDatosPrueba() {
		List<Local> data = new ArrayList<Local>();
		
		Local local1 = new Local();
		local1.setId(1);
		local1.setNombre("Cervecería Baco");
		local1.setDescripcion("Cervecería con tapas y camareras buenísimas");
		local1.setDireccion("Aguas ferreas");
		local1.setCategoria(Local.RESTAURACION);
		
		Local local2 = new Local();
		local2.setId(2);
		local2.setNombre("Pub Onda Media");
		local2.setDescripcion("Pub en el pleno centro de Lugo");
		local2.setDireccion("Calle Catedral");
		local2.setCategoria(Local.NOCTURNO);
		
		data.add(local1);
		data.add(local2);
		
		return data;
	}
	
	@Override
	public List<Local> findByCategory(String categoryId) throws DaoException {
		List<Local> targetLocales = new ArrayList<Local>();
		
		for (Local local : mockLocals) {
			if (local.getCategoria().equals(categoryId)) {
				targetLocales.add(local);
			}
		}
		
		return targetLocales;
	}
	
	@Override
	public Local find(Integer objId) {
		for (Local local : mockLocals) {
			if (local.getId() == objId) {
				return local;
			}
		}
		return null;
	}

	@Override
	public List<Local> findAll() {
		return mockLocals;
	}

	@Override
	public Integer save(Local obj) {
		mockLocals.add(obj);
		return obj.getId();
	}

}
