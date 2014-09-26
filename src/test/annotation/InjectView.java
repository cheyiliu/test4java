
package test.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * from Roboguice
 */
@Retention(RUNTIME)
@Target({
        ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD
})
public @interface InjectView {
    int value() default -1;

    String tag() default "";
}
