/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.LocalListAdapter;
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
public class LocalListFragment extends ListFragment implements
		OnItemClickListener {

	public static final String CATEGORY_KEY = "localCategory";
	
	private String localesCategory;
	private LocalService localService;
	private List<Local> locales;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.localService = ServiceFactory.getLocalService();
		localesCategory = getArguments().getString(CATEGORY_KEY);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(this);
		
		localService.findByCategoryAsync(localesCategory, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				locales = (List<Local>) result;
				ListAdapter adapter = new LocalListAdapter(getActivity(), locales);
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
		
		Local local = locales.get(position);
		
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
