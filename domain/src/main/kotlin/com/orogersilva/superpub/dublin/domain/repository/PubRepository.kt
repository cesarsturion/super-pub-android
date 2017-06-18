package com.orogersilva.superpub.dublin.domain.repository

import com.orogersilva.superpub.dublin.domain.model.Pub
import io.reactivex.Observable

/**
 * Created by orogersilva on 5/26/2017.
 */
interface PubRepository {

    // region METHODS

    fun getPubs(query: String = "\"pub\"",
                type: String = "place",
                fromLocation: String,
                radius: Int = 5000,
                limit: Int = 100,
                fields: String = "location,name,overall_star_rating,rating_count,checkins,phone,fan_count,picture,cover",
                displayedDataTimestamp: Long = 0L): Observable<Pub>?

    fun savePubs(pubs: List<Pub>)

    // endregion
}