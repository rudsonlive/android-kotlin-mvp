package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as _when

/**
 * This class makes tests for [HomeInteractor]
 *
 * @author Rudson Lima
 * @since 09/24/17
 */

class HomeInteractorTest {

    val PAGE: Int? = 2

    @Mock
    var mApiEndPoint: ApiEndPoint? = null

    @Mock
    var mInteractor: HomeInteractor? = null

    @Mock
    var mUserResponse: UserResponse? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mInteractor = HomeInteractor(mApiEndPoint!!)
        _when(mInteractor!!.fetchUsers(PAGE!!)).thenReturn(Observable.just(mUserResponse!!))
    }

    @Test
    fun fetchUsers_sucess() {
        val subscriber = TestObserver.create<UserResponse>()

        mInteractor!!.fetchUsers(PAGE!!).subscribe(subscriber)
        subscriber.onNext(mUserResponse!!)
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }
}