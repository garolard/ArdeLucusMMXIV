/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.LocalService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Gabriel
 *
 */
public class LocalDetailFragment extends Fragment {

	private LocalService service;
	
	public LocalDetailFragment() {
		service = ServiceFactory.getLocalService();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int localId = getArguments().getInt("localId");
		final View view = inflater.inflate(R.layout.local_list_item, container, false);
		service.findAsync(localId, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				
				Local local = (Local) result;
				TextView nombre = (TextView) view.findViewById(R.id.localName);
				TextView descripcion = (TextView) view.findViewById(R.id.localDescription);
				TextView direccion = (TextView) view.findViewById(R.id.localAddress);
				nombre.setText(local.getNombre());
				descripcion.setText(local.getDescripcion());
				direccion.setText(local.getDireccion());
				
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}
	
}
