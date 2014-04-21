package es.gabrielferreiro.apps.ardelucusmmxiv;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.fragment.FeaturedListFragment;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private EventoService mEventoService;
	
	private FeaturedListFragment listFragment;	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] mDrawerItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupNavigationDrawer();
		
		mEventoService = ServiceFactory.getEventoService();
		mEventoService.findAllAsync(new AsyncHandler() {
			
			// Aunque el método recibe un Object, se garantiza
			// que el método findAllAsync devuelve un List<T> o null
			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object result) {
				
				List<Evento> eventos = null;
				try {
					eventos = (List<Evento>) result;
					listFragment = new FeaturedListFragment();
					listFragment.setAllEventos(eventos);
					getFragmentManager().beginTransaction()
										.replace(R.id.container, listFragment)
										.commit();
				} catch (ClassCastException cce) {
					Log.e(TAG, "Error de casting: " + cce.getMessage());
				}
				
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void setupNavigationDrawer() {
		mDrawerItems = getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItems));
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        mDrawerToggle = new ActionBarDrawerToggle(
        		this,
        		mDrawerLayout,
        		R.drawable.ic_drawer,
        		0,
        		0) {
        	
        	public void onDrawerClosed(android.view.View drawerView) {
        		invalidateOptionsMenu();
        	}
        	
        	public void onDrawerOpened(android.view.View drawerView) {
        		invalidateOptionsMenu();
        	}
        	
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
