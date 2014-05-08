/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter.EventoHolder;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		EventoHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.evento_list_item, null);
			holder = new EventoHolder();
			holder.titulo = (TextView) vi.findViewById(R.id.tituloEvento);
			holder.descripcion = (TextView) vi.findViewById(R.id.descripcionEvento);
			holder.fecha = (TextView) vi.findViewById(R.id.fechaEvento);
			vi.setTag(holder);
		}
		Evento actualItem = eventos.get(position);
		holder = (EventoHolder) vi.getTag();
		
		holder.titulo.setText(actualItem.getTitulo());
		holder.descripcion.setText(actualItem.getDescripcion());
		String fechaRealizacion = actualItem.getTiempoRealizacion() != null ? actualItem.getTiempoRealizacion().toString() : "Sin fecha";
		holder.fecha.setText(fechaRealizacion);
		
		return vi;
		
	}

}
