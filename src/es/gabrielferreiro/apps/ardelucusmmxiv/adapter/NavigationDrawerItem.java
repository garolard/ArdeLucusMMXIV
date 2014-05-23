/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.adapter;

/**
 * @author Gabriel
 *
 */
public class NavigationDrawerItem {
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_HEADER = 1;
	
	public int type;
	public String title;
	public int iconId;
	
	public NavigationDrawerItem(int type,
								   String title,
								   int iconId) {
		this.type = type;
		this.title = title;
		this.iconId = iconId;
	}
}
