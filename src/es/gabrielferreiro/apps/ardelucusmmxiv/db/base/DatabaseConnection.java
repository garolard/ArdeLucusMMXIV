/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.db.base;


import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.EventoDaoSQLiteImpl;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.LocalDaoSQLiteImpl;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Gabriel
 *
 */
public class DatabaseConnection {

	private static final String DATABASE_DEFAULT_NAME = "ardelucusmmxiv.db";
	private static final int DATABASE_CURRENT_VERSION = 1;
	private SQLiteDatabase mDB;
	private DatabaseHelper mOpenHelper;
	private final Context mContext;
	
	private static final class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(final Context context) {
			super(context, DATABASE_DEFAULT_NAME, null, DATABASE_CURRENT_VERSION);
		}

		@Override
		public void onCreate(final SQLiteDatabase db) {
			new EventoDaoSQLiteImpl(Evento.class).createTable(db);
			new LocalDaoSQLiteImpl(Local.class).createTable(db);
		}

		@Override
		public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
			// TODO Auto-generated method stub
		}
		
	}
	
	public DatabaseConnection(final Context context) {
		mContext = context;
		mOpenHelper = new DatabaseHelper(mContext);
	}
	
	public final Context getContext() {
		return mContext;
	}
	
	public final SQLiteDatabase getDatabase() {
		return mDB;
	}
	
	public final void open() {
		if (mOpenHelper == null) {
			return;
		}
		
		mDB = mOpenHelper.getWritableDatabase();
	}
	
	public final void close() {
		if (mDB != null) {
			mDB.close();
		}
	}
	
}
