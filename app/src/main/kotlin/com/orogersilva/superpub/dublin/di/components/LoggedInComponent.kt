package com.orogersilva.superpub.dublin.di.components

import com.orogersilva.superpub.dublin.di.modules.*
import com.orogersilva.superpub.dublin.domain.di.scope.LoggedInScope
import com.orogersilva.superpub.dublin.presentation.screen.pubs.view.PubsActivity
import dagger.Subcomponent

/**
 * Created by orogersilva on 4/5/2017.
 */
@LoggedInScope
@Subcomponent(modules = arrayOf(GetPubsUseCaseModule::class, PubRepositoryModule::class,
        SchedulerProviderModule::class, CacheModule::class, DatabaseModule::class,
        NetworkModule::class, ClockModule::class))
interface LoggedInComponent {

    // region FACTORY METHODS

    fun newPubsActivityComponent(pubsPresenterModule: PubsPresenterModule): PubsActivityComponent

    // endregion
}