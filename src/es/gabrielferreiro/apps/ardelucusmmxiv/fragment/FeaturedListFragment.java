/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public class FeaturedListFragment extends ListFragment implements OnItemClickListener {
	
	private List<Evento> allEventos;
	
	public void setAllEventos(List<Evento> eventos) {
		allEventos = eventos;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(this);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		ListAdapter adapter = new FeaturedListAdapter(getActivity(), allEventos);
		setListAdapter(adapter);
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
							.addToBackStack("")
							.commit();
		
	}
	
}
