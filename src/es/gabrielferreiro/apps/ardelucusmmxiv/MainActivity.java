package es.gabrielferreiro.apps.ardelucusmmxiv;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private EventoService mEventoService;
	private FeaturedListFragment listFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		Log.i(TAG, "Estoy despues de la llamada asíncrona");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static class FeaturedListFragment extends ListFragment {
		
		private List<Evento> allEventos;
		
		public void setAllEventos(List<Evento> eventos) {
			allEventos = eventos;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			ListAdapter adapter = new FeaturedListAdapter(getActivity(), allEventos);
			setListAdapter(adapter);
		}
		
	}
	
}
