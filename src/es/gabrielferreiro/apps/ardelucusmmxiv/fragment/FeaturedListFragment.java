/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
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
public class FeaturedListFragment extends ListFragment implements OnItemClickListener {
	
	private EventoService eventoService;
	private List<Evento> allEventos;
	
	public FeaturedListFragment() {
		eventoService = ServiceFactory.getEventoService();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(this);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		eventoService.findAllAsync(new AsyncHandler() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object result) {
				allEventos = (List<Evento>) result;
				ListAdapter adapter = new FeaturedListAdapter(getActivity(), allEventos);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {}
			
		});
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Evento evento = allEventos.get(position);
		
		Bundle arguments = new Bundle();
		arguments.putInt("eventoId", evento.getId());
		
		EventoDetailFragment detailFragment = new EventoDetailFragment();
		detailFragment.setArguments(arguments);
		
		getFragmentManager().beginTransaction()
							.replace(R.id.container, detailFragment)
							.addToBackStack(getTag())
							.commit();
		
	}
	
}
