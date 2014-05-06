/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv;

import es.gabrielferreiro.apps.ardelucusmmxiv.db.base.DatabaseConnection;
import android.app.Application;
import android.content.Context;

/**
 * @author Gabriel
 *
 */
public class ArdeLucusApp extends Application {

	public static Context mContext;
	public static DatabaseConnection mDatabaseConnection;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		mDatabaseConnection = new DatabaseConnection(mContext);
		mDatabaseConnection.open();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		mDatabaseConnection.close();
	}
	
}
