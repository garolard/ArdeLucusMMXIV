/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.BaseActivity;
import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.LocalListAdapter;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.LocalService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author Gabriel
 *
 */
public class LocalListFragment extends ListFragment {

	public static final String CATEGORY_KEY = "localCategory";
	
	private String localesCategory;
	private LocalService localService;
	private List<Local> locales;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.localService = ServiceFactory.getLocalService();
		BaseActivity activity = (BaseActivity) getActivity();
		this.localService.setConnectivityStatus(activity.getConnectivityStatus());
		localesCategory = getArguments().getString(CATEGORY_KEY);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.base_list_layout, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final Context context = getActivity();
		
		localService.findByCategoryAsync(localesCategory, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				locales = (List<Local>) result;
				ListAdapter adapter = new LocalListAdapter(context, locales);
				setListAdapter(adapter);
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Local local = locales.get(position);
		
		Bundle arguments = new Bundle();
		arguments.putInt("localId", local.getId());
		
		LocalDetailFragment detailFragment = new LocalDetailFragment();
		detailFragment.setArguments(arguments);
		
		getFragmentManager().beginTransaction()
							.setCustomAnimations(R.anim.slide_in_left_to_right, R.anim.slide_out_right_to_left,
												 R.anim.slide_in_left_to_right, R.anim.slide_out_right_to_left)
							.replace(R.id.container, detailFragment)
							.addToBackStack(getTag())
							.commit();
		
	}

}
