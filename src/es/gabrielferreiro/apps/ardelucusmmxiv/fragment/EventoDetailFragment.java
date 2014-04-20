/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.fragment;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.impl.ServiceFactory;
import android.app.Activity;
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
public class EventoDetailFragment extends Fragment {

	private EventoService service;
	
	public EventoDetailFragment() {
		this.service = ServiceFactory.getEventoService();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int eventoId = (Integer) getArguments().get("eventoId");
		final View view = inflater.inflate(R.layout.featured_item_layout, null);
		service.findAsync(eventoId, new AsyncHandler() {
			
			@Override
			public void onSuccess(Object result) {
				
				try {
					Evento evento = (Evento) result;
					TextView titulo = (TextView) view.findViewById(R.id.tituloItem);
					TextView descripcion = (TextView) view.findViewById(R.id.descripcionItem);
					titulo.setText(evento.getTitulo());
					descripcion.setText(evento.getDescripcion());
				} catch (ClassCastException cce) {
					
				}
				
			}
			
			@Override
			public void onError(Object result, Exception exception) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}
	
}
