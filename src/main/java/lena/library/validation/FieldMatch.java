package lena.library.validation;

import org.jvnet.staxex.StAxSOAPBody;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface FieldMatch {

    String message() default "{constraints.field-match}";
    Class<?>[] groups() default {};
    Class<? extends StAxSOAPBody.Payload>[] payload() default {};
    String first();
    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }
}
