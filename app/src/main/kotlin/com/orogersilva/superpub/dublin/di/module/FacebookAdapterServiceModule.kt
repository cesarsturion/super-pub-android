package com.orogersilva.superpub.dublin.di.module

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.orogersilva.superpub.dublin.adapter.facebook.FacebookHelper
import com.orogersilva.superpub.dublin.adapter.facebook.impl.FacebookAdapterHelper
import com.orogersilva.superpub.dublin.domain.di.scope.ActivityScope
import com.orogersilva.superpub.dublin.presentation.screen.login.LoginContract
import dagger.Module
import dagger.Provides

/**
 * Created by orogersilva on 6/23/2017.
 */
@ActivityScope
@Module
open class FacebookAdapterServiceModule {

    // region PROVIDERS

    @Provides @ActivityScope open fun provideFacebookAdapterService(loginView: LoginContract.View,
                                                                        loginManager: LoginManager,
                                                                        callbackManager: CallbackManager) : FacebookHelper =
            FacebookAdapterHelper(loginView, loginManager, callbackManager)

    // endregion
}