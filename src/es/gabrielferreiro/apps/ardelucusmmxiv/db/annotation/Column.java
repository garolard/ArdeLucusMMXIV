package es.gabrielferreiro.apps.ardelucusmmxiv.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

	boolean isPrimaryKey() default false;
	boolean isAutoincrement() default false;
	String type() default "TEXT";
	String name() default "";
	
}
