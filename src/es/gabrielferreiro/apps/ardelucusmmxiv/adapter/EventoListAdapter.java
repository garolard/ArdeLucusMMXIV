/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.adapter.FeaturedListAdapter.EventoHolder;
import es.gabrielferreiro.apps.ardelucusmmxiv.helper.ImageHelper;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
			holder.imagen = (ImageView) vi.findViewById(R.id.imagenEvento);
			holder.titulo = (TextView) vi.findViewById(R.id.tituloEvento);
			holder.descripcion = (TextView) vi.findViewById(R.id.descripcionEvento);
			holder.fecha = (TextView) vi.findViewById(R.id.fechaEvento);
			vi.setTag(holder);
		}
		Evento actualItem = eventos.get(position);
		holder = (EventoHolder) vi.getTag();
		
		String category = actualItem.getCategoria();
		int color = 0;
		if (category.equalsIgnoreCase(Evento.ROMANO)) {
			color = context.getResources().getColor(R.color.roman_activity_background_color);
		} else if (category.equalsIgnoreCase(Evento.CASTREXO)) {
			color = context.getResources().getColor(R.color.celtic_actionbar_background_color);
		}
		
		if (actualItem.getImageUrl() == null || actualItem.getImageUrl().equals("")) {
			holder.imagen.setVisibility(View.GONE);
		} else {
			// Poner aquí imagen
		}
		
		if (color != 0) {
			holder.titulo.setTextColor(color);
		}
		
		holder.titulo.setText(actualItem.getTitulo());
		holder.descripcion.setText(actualItem.getDescripcion());
		String fechaRealizacion = actualItem.getTiempoRealizacion() != null ? actualItem.getTiempoRealizacion().toString() : "Sin fecha";
		holder.fecha.setText(fechaRealizacion);
		
		return vi;
		
	}

}
