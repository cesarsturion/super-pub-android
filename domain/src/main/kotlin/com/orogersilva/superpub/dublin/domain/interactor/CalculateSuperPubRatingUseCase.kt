package com.orogersilva.superpub.dublin.domain.interactor

import com.orogersilva.superpub.dublin.domain.model.Pub
import io.reactivex.Observable

/**
 * Created by orogersilva on 6/13/2017.
 */
interface CalculateSuperPubRatingUseCase {

    // region METHODS

    fun calculateSuperPubRating(pubs: List<Pub>): Observable<Pub>

    // endregion
}