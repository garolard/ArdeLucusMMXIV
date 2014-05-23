/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import es.gabrielferreiro.apps.ardelucusmmxiv.ArdeLucusApp;
import es.gabrielferreiro.apps.ardelucusmmxiv.BaseActivity;
import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;

/**
 * @author Gabriel
 *
 */
public class FeaturedListFragment extends ListFragment {
	
	private EventoService eventoService;
	private List<Evento> allEventos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eventoService = ServiceFactory.getEventoService();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.base_list_layout, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().setTheme(R.style.AppTheme);
		final Context context = getActivity();
		eventoService.updateLocalDatabaseIfNeeded(new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				loadAllItems(context);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				loadAllItems(context);
			}
		});		
	}
	
	private void loadAllItems(final Context context) {
		eventoService.findAllAsync(new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				allEventos = (List<Evento>) result;
				ListAdapter adapter = new FeaturedListAdapter(context, allEventos);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				Log.e("ArdeLucus", exception.getMessage());
			}
			
		});
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Evento evento = allEventos.get(position);
		
		Bundle arguments = new Bundle();
		arguments.putInt("eventoId", evento.getId());
		
		EventoDetailFragment detailFragment = new EventoDetailFragment();
		detailFragment.setArguments(arguments);
		
		
		getFragmentManager().beginTransaction()
							.setCustomAnimations(R.anim.slide_in_left_to_right, R.anim.slide_out_right_to_left,
												 R.anim.slide_in_left_to_right, R.anim.slide_out_right_to_left)
							.replace(R.id.container, detailFragment)
							.addToBackStack(getTag())
							.commit();
		
	}
	
}
