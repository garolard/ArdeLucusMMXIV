/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv;

import es.gabrielferreiro.apps.ardelucusmmxiv.connectivity.NetworkUtil;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.EventoDaoSQLiteImpl;
import es.gabrielferreiro.apps.ardelucusmmxiv.db.base.DatabaseConnection;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author Gabriel
 *
 */
public class BaseActivity extends Activity {

	protected int connectivityStatus;
	protected DatabaseConnection dbConnection;
	
	public int getConnectivityStatus() {
		return connectivityStatus;
	}
	public void setConnectivityStatus(int status) {
		this.connectivityStatus = status;
	}
	
	public DatabaseConnection getDatabaseConnection() {
		return this.dbConnection;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (this.dbConnection == null) {
			dbConnection = new DatabaseConnection(getApplicationContext());
			dbConnection.open();
		}
		//TODO: Comprobar si la BBDD está creada
		EventoDaoSQLiteImpl eventoDao = new EventoDaoSQLiteImpl(getApplicationContext(), getDatabaseConnection());
		eventoDao.createTable();
		//TODO: Llamar tambien al createTable() de LocalDaoSQLiteImpl
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		this.connectivityStatus = NetworkUtil.getConnectivityStatus(getApplicationContext());
		this.dbConnection.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.dbConnection.close();
	}
	
}
