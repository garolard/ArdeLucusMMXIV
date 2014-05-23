/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Gabriel
 *
 */
public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerItem> {

	private Context context;
	private List<NavigationDrawerItem> items;
	private LayoutInflater inflater;
	
	public NavigationDrawerAdapter(Context context, int resource,
			List<NavigationDrawerItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.items = objects;
		this.inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}		
	
	@Override
	public int getItemViewType(int position) {
		return items.get(position).type;
	}
	
	@Override
	public boolean isEnabled(int position) {
		return items.get(position).type == 0 ? true : false;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		NavigationDrawerItem actualItem = items.get(position);
		
		if (v == null) {
			if (actualItem.type == NavigationDrawerItem.TYPE_HEADER) {
				v = inflater.inflate(R.layout.drawer_header_item, null);
				TextView title = (TextView)v.findViewById(R.id.drawer_header_text);
				title.setText(actualItem.title);
			} else {
				v = inflater.inflate(R.layout.drawer_list_item, null);
				TextView title = (TextView)v.findViewById(R.id.drawer_item_text);
				title.setText(actualItem.title);
				
				ImageView icon = (ImageView)v.findViewById(R.id.drawer_item_icon);
				icon.setImageResource(actualItem.iconId);
			}
		}
		
		return v;
		
	}
	
}