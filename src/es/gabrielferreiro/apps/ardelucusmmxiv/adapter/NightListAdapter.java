/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;
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
public class NightListAdapter extends BaseAdapter {

	private List<Local> locals;
	private LayoutInflater inflater;
	private Context context;
	
	public NightListAdapter(Context context, List<Local> locals) {
		this.locals = locals;
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return locals.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Local getItem(int position) {
		return locals.get(position);
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
			vi = inflater.inflate(R.layout.night_list_item, null);
		}
		
		TextView nombre = (TextView) vi.findViewById(R.id.nightLocalName);
		TextView descripcion = (TextView) vi.findViewById(R.id.nightLocalDescription);
		TextView direccion = (TextView) vi.findViewById(R.id.nightLocalAddress);
		Local actualItem = locals.get(position);
		
		nombre.setText(actualItem.getNombre());
		descripcion.setText(actualItem.getDescripcion());
		direccion.setText(actualItem.getDireccion());
		
		return vi;
		
	}

}
