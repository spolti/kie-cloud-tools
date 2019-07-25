package org.kie.cekit.cacher.properties.loader;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier for te Cacher Properties
 * Boolean value are defaulted to false if omitted.
 * Example:
 * <pre>
 *  &#064;CacherProperty(name = "org.kie.cekit.cacher.myprops", required = true)
 *  Object myProperty;
 *  </pre>
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CacherProperty {

    /**
     * The System Property name
     *
     * @return its name
     */
    @Nonbinding String name();

    /**
     * Make a property required
     * Default is false
     *
     * @return none
     */
    @Nonbinding boolean required() default false;

}
