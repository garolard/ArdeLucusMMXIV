/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.db.annotation.Column;
import es.gabrielferreiro.apps.ardelucusmmxiv.db.annotation.Table;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Gabriel
 *
 */
public abstract class BaseDaoSQLiteImpl<T, PK> {

	protected Class<T> tClass;
	protected Context mContext;
	protected SQLiteDatabase mDB;
	
	public void createTable() {
		if (getTableName(tClass) == null) {
			return;
		}
		
		StringBuilder sql = new StringBuilder("CREATE TABLE ");
		sql.append(getTableName(tClass));
		sql.append(" (");
		boolean firstColumnAppended = false;
		
		for (Field field : tClass.getDeclaredFields()) {
			
			Column annotationColumn = field.getAnnotation(Column.class);
			
			if (annotationColumn != null) {
				
				if (firstColumnAppended) {
					sql.append(", ");
				}
				
				sql.append(getColumnName(field));
				sql.append(" ");
				sql.append(getColumnType(field));
				
				if (annotationColumn.isPrimaryKey()) {
					sql.append(" PRIMARY KEY ");
				}
				
				if (annotationColumn.isAutoincrement()) {
					sql.append(" AUTOINCREMENT");
				}
				
				firstColumnAppended = true;
				
			}
			
		}
		
		sql.append(");");
		mDB.execSQL(sql.toString());
	}
	
	/* Annotations and SQL Queries generation helpers */
	protected String getTableName(Class<?> tClass) {
		Table annotationTable = tClass.getAnnotation(Table.class);
		String tableName = tClass.getSimpleName();
		
		if (annotationTable != null) {
			if (!annotationTable.name().equals("")) {
				tableName = annotationTable.name();
			}
		}
		
		return tableName;
	}
	
	protected String getColumnName(Field field) {
		Column annotationColumn = field.getAnnotation(Column.class);
		String columnName = null;
		
		if (annotationColumn != null) {
			if (annotationColumn.name().equals("")) {
				columnName = field.getName();
			} else {
				columnName = annotationColumn.name();
			}
		}
		
		return columnName;
	}
	
	protected String getColumnType(Field field) {
		Column annotationColumn = field.getAnnotation(Column.class);
		String columnType = null;
		
		if (annotationColumn != null) {
			columnType = annotationColumn.type(); // Default is TEXT
			if (annotationColumn.type().equals("DATETIME")) {
				columnType = "TEXT"; // El tipo DATETIME no existe en SQLite, solo se pone para saber como transformar el valor
			}
		}
		
		return columnType;
	}
	
	/* CRUD */
	protected T findById(PK id) 
			throws InstantiationException, IllegalAccessException, NoSuchFieldException {
		Cursor cursor = getByCursor(id);
		
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			T newTObject = null;
			
			
			newTObject = tClass.newInstance();
			bindObject(newTObject, cursor);
			
			cursor.close();
			cursor = null;
			return newTObject;
		}
		
		return null;
	}
	
	protected List<T> findAllBase() 
			throws InstantiationException, IllegalAccessException, NoSuchFieldException {
		if (mDB == null) {
			return null;
		}
		
		List<T> items = new ArrayList<T>();
		Cursor cursor = null;
		
		synchronized (mDB) {
			cursor = getAllByCursor();
		}
		
		if (cursor != null && cursor.getCount() > 0) {
			
			for (int i = 0; i <cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				T newTObject;
				
				
				newTObject = tClass.newInstance();
				bindObject(newTObject, cursor);
				items.add(newTObject);
				
			}
			cursor.close();
			cursor = null;
			
		}
		return items;
	}
	
	protected long insert(T item) {
		if (item != null) {
			try {
				ContentValues values = getFilledContentValues(item);
				long id = mDB.insert(getTableName(tClass), null, values);
				return id;
			} catch (IllegalAccessException iae) {
				
			}
		}
		return -1;
	}
	
	/* SQL-to-CRUD helpers*/
	protected Cursor getByCursor(PK id) {
		return mDB.query(getTableName(tClass), new String[]{"*"}, "where _id = ?", new String[]{id.toString()}, null, null, null);
	}
	
	protected Cursor getAllByCursor() {
		return mDB.query(getTableName(tClass), null, null, null, null, null, null);
	}
	
	protected Cursor getAllByCursorWithWhere(String whereClause, String[] whereArgs) {
		return mDB.query(getTableName(tClass), new String[]{"*"}, whereClause, whereArgs, null, null, null);
	}
	
	protected ContentValues getFilledContentValues(Object object)
		throws IllegalAccessException {
		ContentValues contentValues = new ContentValues();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			Column annotationColumn = field.getAnnotation(Column.class);
			
			if (annotationColumn != null) {
				if (!annotationColumn.isAutoincrement()) {
					putInContentValues(contentValues, field, object);
				}
			}
			
		}
		
		return contentValues;
	}
	
	protected void putInContentValues(ContentValues contentValues, Field field, Object object) 
		throws IllegalAccessException {
		
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		
		Object fieldValue = field.get(object);
		String key = getColumnName(field);
		
		if (fieldValue instanceof Integer) {
			contentValues.put(key, Long.valueOf(fieldValue.toString()));
		} else if (fieldValue instanceof Long) {
			contentValues.put(key, Integer.valueOf(fieldValue.toString()));
		} else if (fieldValue instanceof String) {
			contentValues.put(key, fieldValue.toString());
		} else if (fieldValue instanceof Double) {
			contentValues.put(key, Double.valueOf(fieldValue.toString()));
		} else if (fieldValue instanceof Float) {
			contentValues.put(key, Float.valueOf(fieldValue.toString()));
		} else if (fieldValue instanceof Boolean) {
			contentValues.put(key, Boolean.parseBoolean(fieldValue.toString()));
		} else if (fieldValue instanceof Date) {
			contentValues.put(key, tryFormatDate((Date)fieldValue));
		}
		
	}
	
	/* SQL-to-Object transformation helpers*/
	protected void bindObject(T newTObject, Cursor cursor)
		throws NoSuchFieldException, IllegalAccessException {
		
		for (Field field : tClass.getDeclaredFields()) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			
			Column annotationColumn = field.getAnnotation(Column.class);
			if (annotationColumn != null) {
				field.set(newTObject, getValueFromCursor(cursor, field));
			}
		}
		
	}
	
	protected Object getValueFromCursor(Cursor cursor, Field field)
		throws IllegalAccessException {
		Class<?> fieldType = field.getType();
		Object value = null;
		int columnIndex = cursor.getColumnIndex(getColumnName(field));
		
		if (fieldType.isAssignableFrom(Integer.class) ||
			fieldType.isAssignableFrom(int.class)) {
			value = cursor.getInt(columnIndex);
		} else if (fieldType.isAssignableFrom(Long.class) ||
				   fieldType.isAssignableFrom(long.class)) {
			value = cursor.getLong(columnIndex);
		} else if (fieldType.isAssignableFrom(String.class)) {
			value = cursor.getString(columnIndex);
		} else if (fieldType.isAssignableFrom(Double.class) ||
				   fieldType.isAssignableFrom(double.class)) {
			value = cursor.getDouble(columnIndex);
		} else if (fieldType.isAssignableFrom(Float.class) ||
				   fieldType.isAssignableFrom(float.class)) {
			value = cursor.getFloat(columnIndex);
		} else if (fieldType.isAssignableFrom(Boolean.class) ||
				   fieldType.isAssignableFrom(boolean.class)) {
			int booleanInteger = cursor.getInt(columnIndex);
			value = booleanInteger == 1;
		} else if (fieldType.isAssignableFrom(Date.class)) {
			String dateAsString = cursor.getString(columnIndex);
			value = tryParseDate(dateAsString);
		}
		
		return value;		
	}
	
	@SuppressLint("SimpleDateFormat")
	protected Date tryParseDate(String dateAsString) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.parse(dateAsString);
		} catch (ParseException pe) {
			return null;
		}
	}
	
	@SuppressLint("SimpleDateFormat")
	protected String tryFormatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}
	
}
