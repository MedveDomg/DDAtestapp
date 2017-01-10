package com.example.medvedomg.ddatest.ui;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by medvedomg on 22.12.16.
 */
@Scope
@Documented
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
