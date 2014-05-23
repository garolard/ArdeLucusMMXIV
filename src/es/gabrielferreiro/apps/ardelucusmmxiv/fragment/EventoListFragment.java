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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import es.gabrielferreiro.apps.ardelucusmmxiv.BaseActivity;
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
public class EventoListFragment extends ListFragment {
	
	public static final String CATEGORY_KEY = "eventoCategory";
	
	private String eventosCategory;
	private EventoService eventoService;
	private List<Evento> eventos;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.eventoService = ServiceFactory.getEventoService();
		BaseActivity activity = (BaseActivity) getActivity();
		this.eventoService.setConnectivityStatus(activity.getConnectivityStatus());
		eventosCategory = getArguments().getString(CATEGORY_KEY);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.base_list_layout, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setUpTheme(eventosCategory);
		final Context context = getActivity();
		
		eventoService.findByCategoryAsync(eventosCategory, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				eventos = (List<Evento>) result;
				ListAdapter adapter = new EventoListAdapter(context, eventos);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private void setUpTheme(String category) {
		if (Evento.ROMANO.equalsIgnoreCase(category)) {
			getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.roman_actionbar_background));
			getActivity().getWindow().setBackgroundDrawableResource(R.drawable.roman_activity_background);
		} else if (Evento.CASTREXO.equalsIgnoreCase(category)) {
			getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.celtic_actionbar_background));
			getActivity().getWindow().setBackgroundDrawableResource(R.drawable.celtic_activity_background);
		} else if (Evento.INFANTIL.equalsIgnoreCase(category)) {
			getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.childhood_actionbar_background));
			getActivity().getWindow().setBackgroundDrawableResource(R.drawable.childhood_activity_background);
		} else if (Evento.NOCTURNO.equalsIgnoreCase(category)) {
			getActivity().getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.night_actionbar_background));
			getActivity().getWindow().setBackgroundDrawableResource(R.drawable.night_activity_background);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Evento evento = eventos.get(position);
		
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
