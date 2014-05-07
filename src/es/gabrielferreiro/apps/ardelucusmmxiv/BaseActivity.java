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
	protected boolean busy;
	
	public int getConnectivityStatus() {
		return connectivityStatus;
	}
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	public boolean isBusy() {
		return busy;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		this.connectivityStatus = NetworkUtil.getConnectivityStatus(getApplicationContext());
	}
	
}
