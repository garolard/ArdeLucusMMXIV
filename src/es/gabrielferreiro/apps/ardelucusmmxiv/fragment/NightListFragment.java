/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.NightListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.LocalService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * @author Gabriel
 *
 */
public class NightListFragment extends ListFragment implements
		OnItemClickListener {

	private LocalService localService;
	private List<Local> nightLocales;
	
	public NightListFragment() {
		this.localService = ServiceFactory.getLocalService();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(this);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
				
		localService.findByCategoryAsync(Local.NOCTURNO, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				nightLocales = (List<Local>) result;
				ListAdapter adapter = new NightListAdapter(getActivity(), nightLocales);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Local local = nightLocales.get(position);
		
		Bundle arguments = new Bundle();
		arguments.putInt("localId", local.getId());
		
		LocalDetailFragment detailFragment = new LocalDetailFragment();
		detailFragment.setArguments(arguments);
		
		getFragmentManager().beginTransaction()
							.replace(R.id.container, detailFragment)
							.addToBackStack(getTag())
							.commit();

	}

}
