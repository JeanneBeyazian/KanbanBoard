package annotations;

import java.lang.annotation.*;

// Annotation to be used when a class is created

@SuppressWarnings("ALL")
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)

public @interface ClassAnnotation {
    String[] classAuthors();
    String creationDate();
    String lastEdit();
}