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
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.EventoListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.LocalListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;

/**
 * @author Gabriel
 *
 */
public class EventoListFragment extends ListFragment implements
		OnItemClickListener {
	
	public static final String CATEGORY_KEY = "eventoCategory";
	
	private String eventosCategory;
	private EventoService eventoService;
	private List<Evento> eventos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.eventoService = ServiceFactory.getEventoService();
		eventosCategory = getArguments().getString(CATEGORY_KEY);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(this);
		
		eventoService.findByCategoryAsync(eventosCategory, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				eventos = (List<Evento>) result;
				ListAdapter adapter = new EventoListAdapter(getActivity(), eventos);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Evento evento = eventos.get(position);
		
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
