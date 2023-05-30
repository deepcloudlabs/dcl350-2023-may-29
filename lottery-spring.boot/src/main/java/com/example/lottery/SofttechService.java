package com.example.lottery;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Target(TYPE)
@Documented
@Inherited
@Component
public @interface SofttechService {
	@AliasFor(annotation = Component.class)
	String value() default "";

}
