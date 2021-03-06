package com.orogersilva.superpub.dublin.data.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.orogersilva.superpub.dublin.data.PubDataSource
import com.orogersilva.superpub.dublin.data.api.BaseNetworkTestCase
import com.orogersilva.superpub.dublin.data.api.endpoint.SearchApiClient
import com.orogersilva.superpub.dublin.data.entity.PubEntity
import com.orogersilva.superpub.dublin.data.entity.PubHttpResponse
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

/**
 * Created by orogersilva on 5/29/2017.
 */
class PubRemoteDataSourceTest : BaseNetworkTestCase() {

    // region PROPERTIES

    private val ACCESS_TOKEN = "dod9DKsjs923KDMc32sskzZISr2J"

    private lateinit var apiClientMock: SearchApiClient

    private lateinit var pubRemoteDataSource: PubDataSource

    // endregion

    // region SETUP METHODS

    @Before fun setup() {

        apiClientMock = mock<SearchApiClient>()

        pubRemoteDataSource = PubRemoteDataSource(ACCESS_TOKEN, apiClientMock)
    }

    // endregion

    // region TEST METHODS

    @Test fun `Get pubs, when there is no data, then returns no pubs`() {

        // ARRANGE

        val EMITTED_EVENTS_COUNT = 0

        val QUERY_VALUE = "\"pub\""
        val TYPE_VALUE = "place"
        val FROM_LOCATION_VALUE = "-30.0262844,-51.2072853"
        val DISTANCE_VALUE = 5000
        val LIMIT_VALUE = 100
        val FIELDS_VALUE = "location,name,overall_star_rating,rating_count,checkins,phone,fan_count,picture,cover"

        whenever(apiClientMock.getPubs(QUERY_VALUE, TYPE_VALUE, FROM_LOCATION_VALUE,
                DISTANCE_VALUE, LIMIT_VALUE, FIELDS_VALUE, ACCESS_TOKEN))
                .thenReturn(Flowable.empty())

        val testSubscriber = TestSubscriber<List<PubEntity>>()

        // ACT

        pubRemoteDataSource.getPubs(fromLocation = FROM_LOCATION_VALUE)
                .subscribe(testSubscriber)

        // ASSERT

        testSubscriber.assertComplete()
                .assertNoErrors()
                .assertValueCount(EMITTED_EVENTS_COUNT)
    }

    @Test fun `Get pubs, when there is data, then returns pubs`() {

        // ARRANGE

        val RESOURCES_FILE_NAME = "pubsHttpResponse.json"

        val EMITTED_EVENTS_COUNT = 1

        val QUERY_VALUE = "\"pub\""
        val TYPE_VALUE = "place"
        val FROM_LOCATION_VALUE = "-30.0262844,-51.2072853"
        val DISTANCE_VALUE = 5000
        val LIMIT_VALUE = 100
        val FIELDS_VALUE = "location,name,overall_star_rating,rating_count,checkins,phone,fan_count,picture,cover"

        val expectedPubsHttpResponse = createTestHttpData(loadJsonFromAsset(RESOURCES_FILE_NAME))

        val networkData = Flowable.just(expectedPubsHttpResponse)

        whenever(apiClientMock.getPubs(QUERY_VALUE, TYPE_VALUE, FROM_LOCATION_VALUE,
                DISTANCE_VALUE, LIMIT_VALUE, FIELDS_VALUE, ACCESS_TOKEN))
                .thenReturn(networkData)

        val testSubscriber = TestSubscriber<List<PubEntity>>()

        // ACT

        pubRemoteDataSource.getPubs(fromLocation = FROM_LOCATION_VALUE)
                .subscribe(testSubscriber)

        // ASSERT

        testSubscriber.assertComplete()
                .assertNoErrors()
                .assertValueCount(EMITTED_EVENTS_COUNT)
                .assertOf { pub1 ->
                    expectedPubsHttpResponse.data.forEach { pub2 -> (pub1 == transform(pub2))}
                }
    }

    // endregion

    // region UTILITY METHODS

    fun transform(pubData: PubHttpResponse.PubData): PubEntity =
            PubEntity(pubData.id,
                    pubData.name,
                    pubData.phone,
                    pubData.rating,
                    pubData.ratingCount,
                    pubData.checkins,
                    pubData.fanCount,
                    pubData.cover?.source,
                    pubData.picture.picturedata.url,
                    pubData.location.latitude,
                    pubData.location.longitude,
                    pubData.location.street,
                    pubData.picture.picturedata.isSilhouette)

    // endregion
}