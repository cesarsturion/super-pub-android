package com.orogersilva.superpub.dublin.di.modules

import com.orogersilva.superpub.dublin.BuildConfig
import com.orogersilva.superpub.dublin.data.PubDataSource
import com.orogersilva.superpub.dublin.data.api.RestClient
import com.orogersilva.superpub.dublin.data.api.endpoint.SearchApiClient
import com.orogersilva.superpub.dublin.data.di.scope.Remote
import com.orogersilva.superpub.dublin.data.remote.PubRemoteDataSource
import com.orogersilva.superpub.dublin.domain.di.scope.PubInfoScope
import dagger.Module
import dagger.Provides

/**
 * Created by orogersilva on 5/1/2017.
 */
@PubInfoScope
@Module
open class NetworkModule {

    // region PROVIDERS

    @Provides @PubInfoScope open fun provideBaseEndpoint(): String = BuildConfig.FACEBOOK_GRAPH_API

    @Provides @PubInfoScope @Remote open fun providePubRemoteDataSource(baseEndpoint: String): PubDataSource? =
            PubRemoteDataSource(RestClient.getApiClient(SearchApiClient::class.java, baseEndpoint))

    // endregion
}