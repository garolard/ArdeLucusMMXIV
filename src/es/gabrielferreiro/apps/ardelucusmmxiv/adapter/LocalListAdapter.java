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
public class LocalListAdapter extends BaseAdapter {

	private List<Local> locals;
	private LayoutInflater inflater;
	private Context context;
	
	public LocalListAdapter(Context context, List<Local> locals) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		LocalHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.local_list_item, null);
			holder = new LocalHolder();
			holder.nombre = (TextView) vi.findViewById(R.id.localName);
			holder.descripcion = (TextView) vi.findViewById(R.id.localDescription);
			holder.direccion = (TextView) vi.findViewById(R.id.localAddress);
			vi.setTag(holder);
		}
		
		Local actualItem = locals.get(position);
		holder = (LocalHolder) vi.getTag();
		
		holder.nombre.setText(actualItem.getNombre());
		holder.descripcion.setText(actualItem.getDescripcion());
		holder.direccion.setText(actualItem.getDireccion());
		
		return vi;
		
	}
	
	static class LocalHolder {
		public TextView nombre;
		public TextView descripcion;
		public TextView direccion;
	}

}
