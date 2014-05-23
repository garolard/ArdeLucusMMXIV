package es.gabrielferreiro.apps.ardelucusmmxiv;

import java.util.ArrayList;
import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.NavigationDrawerAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.NavigationDrawerItem;
import es.gabrielferreiro.apps.ardelucusmmxiv.fragment.EventoListFragment;
import es.gabrielferreiro.apps.ardelucusmmxiv.fragment.FeaturedListFragment;
import es.gabrielferreiro.apps.ardelucusmmxiv.fragment.LocalListFragment;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends BaseActivity {

	@SuppressWarnings("unused")
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private Fragment initialFragment;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private List<NavigationDrawerItem> mDrawerItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupNavigationDrawer();
		initialFragment = new FeaturedListFragment();
		getFragmentManager().beginTransaction()
							.replace(R.id.container, initialFragment)
							.commit();
	}
	
	private void setupNavigationDrawer() {
		
		mDrawerItems = createNavigationDrawerItems();
		
		//mDrawerItems = getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		//mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItems));
		mDrawerList.setAdapter(new NavigationDrawerAdapter(getApplicationContext(), 0, mDrawerItems));
		mDrawerList.setOnItemClickListener(new OnNavigationDrawerItemClicked());
		
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
	
	private class OnNavigationDrawerItemClicked implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> container, View view, int position, long itemId) {
			selectSection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
		
	}
	
	private void selectSection(int position) {
		
		Fragment fragment = null;
		Bundle fragmentArguments = null;
		NavigationDrawerItem item = mDrawerItems.get(position);
		String itemTitle = item.title;
		
		if (itemTitle.equalsIgnoreCase("programa")) {
			fragment = new FeaturedListFragment();
		}
		
		if (itemTitle.equalsIgnoreCase("romanos")) {
			fragment = new EventoListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(EventoListFragment.CATEGORY_KEY, Evento.ROMANO);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("castrexos")) {
			fragment = new EventoListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(EventoListFragment.CATEGORY_KEY, Evento.CASTREXO);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("infantil")) {
			fragment = new EventoListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(EventoListFragment.CATEGORY_KEY, Evento.INFANTIL);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("nocturnos")) {
			fragment = new EventoListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(EventoListFragment.CATEGORY_KEY, Evento.NOCTURNO);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("restauración")) {
			fragment = new LocalListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(LocalListFragment.CATEGORY_KEY, Local.RESTAURACION);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("recuerdos")) {
			fragment = new LocalListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(LocalListFragment.CATEGORY_KEY, Local.TIENDA);
			fragment.setArguments(fragmentArguments);
		}
		
		if (itemTitle.equalsIgnoreCase("entretenimiento")) {
			fragment = new LocalListFragment();
			fragmentArguments = new Bundle(1);
			fragmentArguments.putString(LocalListFragment.CATEGORY_KEY, Local.NOCTURNO);
			fragment.setArguments(fragmentArguments);
		}
		
		clearBackStack();
		
		getFragmentManager().beginTransaction()
							.remove(initialFragment)
							.replace(R.id.container, fragment)
							.commit();
		
	}
	
	private void clearBackStack() {
		FragmentManager fragmentManager = getFragmentManager();
		for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
			fragmentManager.popBackStack();
		}
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
	
	private List<NavigationDrawerItem> createNavigationDrawerItems() {
		List<NavigationDrawerItem> items = new ArrayList<NavigationDrawerItem>();
		
		NavigationDrawerItem eventosHeader = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_HEADER,
													"Eventos",
													0);
		items.add(eventosHeader);
		
		NavigationDrawerItem programaItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Programa",
													0);
		items.add(programaItem);
		
		NavigationDrawerItem romanosItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Romanos",
													0);
		items.add(romanosItem);
		
		NavigationDrawerItem castrexosItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Castrexos",
													0);
		items.add(castrexosItem);
		
		NavigationDrawerItem infantilItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Infantil",
													0);
		items.add(infantilItem);
		
		NavigationDrawerItem nocturnosItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Nocturnos",
													0);
		items.add(nocturnosItem);
		
		
		NavigationDrawerItem localesHeader = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_HEADER,
													"Locales",
													0);
		items.add(localesHeader);
		
		NavigationDrawerItem restauracionItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Restauración",
													0);
		items.add(restauracionItem);
		
		NavigationDrawerItem recuerdosItem = new NavigationDrawerItem(
													NavigationDrawerItem.TYPE_NORMAL,
													"Recuerdos",
													0);
		items.add(recuerdosItem);
		
		return items;
	}
	
}
