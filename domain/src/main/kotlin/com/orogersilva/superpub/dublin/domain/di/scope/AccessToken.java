package com.orogersilva.superpub.dublin.domain.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by orogersilva on 6/5/2017.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessToken {
}
