package org.blogs.blogsphere.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    String [] value();
}

//the use : @RequiresRole({"ROLE_ADMIN", "ROLE_OTHER", ...})