package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FeaturedListAdapter extends BaseAdapter {

	private List<Evento> events;
	private LayoutInflater inflater;
	private Context context;
	private Activity activity;
	
	public FeaturedListAdapter(Activity activity, List<Evento> events) {
		this.events = events;
		this.activity = activity;
		this.context = activity;
		this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Evento getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		EventoHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.featured_item_layout, null);
			holder = new EventoHolder();
			holder.titulo = (TextView) vi.findViewById(R.id.tituloItem);
			holder.descripcion = (TextView) vi.findViewById(R.id.descripcionItem);
			holder.fecha = (TextView) vi.findViewById(R.id.fechaItem);
			vi.setTag(holder);
		}
		
		Evento actualItem = this.events.get(position);
		holder = (EventoHolder) vi.getTag();
		
		holder.titulo.setText(actualItem.getTitulo());
		holder.descripcion.setText(actualItem.getDescripcion());
		String fechaRealizacion = actualItem.getTiempoRealizacion() != null ? actualItem.getTiempoRealizacion().toString() : "Sin fecha";
		holder.fecha.setText(fechaRealizacion);
		
		return vi;
	}
	
	static class EventoHolder {
		public TextView titulo;
		public TextView descripcion;
		public TextView direccion;
		public TextView fecha;
	}

}
