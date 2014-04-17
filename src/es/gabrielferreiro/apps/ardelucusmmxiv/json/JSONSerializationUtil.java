package es.gabrielferreiro.apps.ardelucusmmxiv.json;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONSerializationUtil {

	public static void deserializeObject(JSONObject json, Object targetObject) {
		
		Class<?> eventoClass = targetObject.getClass();
		Field[] fields = eventoClass.getDeclaredFields();
		
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			
			setValue(targetObject, field, json);

		}
	}
	
	private static void setValue(Object evento, Field field, JSONObject json) {
		
		if (field.getType().equals(Integer.class)) {
			
			try {
				Integer value = json.getInt(field.getName());
				field.set(evento, value);
			} catch (IllegalAccessException iae) {
				Log.e("JSONSerializationUtil", iae.getMessage());
			} catch (JSONException je) {
				Log.e("JSONSerializationUtil", je.getMessage());
			}
			
		} else if (field.getType().equals(Double.class)) {
			
			try {
				Double value = json.getDouble(field.getName());
				field.set(evento, value);
			} catch (IllegalAccessException iae) {
				Log.e("JSONSerializationUtil", iae.getMessage());
			} catch (JSONException je) {
				Log.e("JSONSerializationUtil", je.getMessage());
			}
			
		} else if (field.getType().equals(String.class)) {
			
			try {
				String value = json.getString(field.getName());
				field.set(evento, value);
			} catch (IllegalAccessException iae) {
				Log.e("JSONSerializationUtil", iae.getMessage());
			} catch (JSONException je) {
				Log.e("JSONSerializationUtil", je.getMessage());
			}
			
		} else if (field.getType().equals(Boolean.class)) {
			
			try {
				Boolean value = json.getBoolean(field.getName());
				field.set(evento, value);
			} catch (IllegalAccessException iae) {
				Log.e("JSONSerializationUtil", iae.getMessage());
			} catch (JSONException je) {
				Log.e("JSONSerializationUtil", je.getMessage());
			}
			
		} else if (field.getType().equals(Date.class)) {
			
			try {
				String valueAsString = json.getString(field.getName());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date value = format.parse(valueAsString);
				field.set(evento, value);
			} catch (IllegalAccessException iae) {
				Log.e("JSONSerializationUtil", iae.getMessage());
			} catch (JSONException je) {
				Log.e("JSONSerializationUtil", je.getMessage());
			} catch (ParseException pe) {
				Log.e("JSONSerializationUtil", pe.getMessage());
			}
			
		}
		
	}
	
}
