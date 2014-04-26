/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Gabriel
 *
 */
public class EventoListAdapter extends BaseAdapter {

	private List<Evento> eventos;
	private LayoutInflater inflater;
	private Context context;
	
	public EventoListAdapter(Context context, List<Evento> eventos) {
		this.eventos = eventos;
		this.context = context;
		this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return eventos.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Evento getItem(int position) {
		return eventos.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		View vi = view;
		if (vi == null) {
			vi = inflater.inflate(R.layout.evento_list_item, null);
		}
		
		TextView nombre = (TextView) vi.findViewById(R.id.tituloEvento);
		TextView descripcion = (TextView) vi.findViewById(R.id.descripcionEvento);
		TextView direccion = (TextView) vi.findViewById(R.id.fechaEvento);
		Evento actualItem = eventos.get(position);
		
		nombre.setText(actualItem.getTitulo());
		descripcion.setText(actualItem.getDescripcion());
		direccion.setText(actualItem.getTiempoRealizacion().toString());
		
		return vi;
		
	}

}
