package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
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
	public View getView(int position, View view, ViewGroup parent) {
		
		View vi = view;
		if (vi == null) {
			vi = inflater.inflate(R.layout.featured_item_layout, null);
		}
		
		TextView titulo = (TextView) vi.findViewById(R.id.tituloItem);
		TextView descripcion = (TextView) vi.findViewById(R.id.descripcionItem);
		Evento actualItem = this.events.get(position);
		
		titulo.setText(actualItem.getTitulo());
		descripcion.setText(actualItem.getDescripcion());
		
		return vi;
	}

}
