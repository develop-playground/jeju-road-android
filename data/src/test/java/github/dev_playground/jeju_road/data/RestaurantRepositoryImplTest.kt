package github.dev_playground.jeju_road.data

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.api.mock.MockRestaurantApi
import github.dev_playground.jeju_road.data.model.ContentData
import github.dev_playground.jeju_road.data.model.InformationData
import github.dev_playground.jeju_road.data.model.RestaurantData
import github.dev_playground.jeju_road.data.model.toDomain
import github.dev_playground.jeju_road.data.repository.RestaurantRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RestaurantRepositoryImplTest {

    private val id: Int = 0
    private lateinit var restaurantRepositoryImpl: RestaurantRepositoryImpl
    private lateinit var restaurantApi: RestaurantApi

    private val contListData = RestaurantData(
        message = "",
        information = InformationData(
            listOf(
                ContentData(
                    id = 0L,
                    name = "name",
                    categories = listOf(),
                    address = "address",
                    image = "image",
                    introduction = "introduction"
                )
            )
        )
    )

    @Before
    fun setUp() {
        restaurantApi = mock()
        restaurantRepositoryImpl = RestaurantRepositoryImpl(
            restaurantApi
        )
    }

    @Test
    fun `요청한_리스트_페이지의_데이터를_잘_가져오는지에_대한_테스트`() = runBlockingTest {
        //given
        whenever(restaurantApi.getRestaurantList(id))
            .thenReturn(contListData)

        //when
        val result = restaurantApi.getRestaurantList(id).information.content.map { it.toDomain() }

        //then
        assertEquals(result, restaurantRepositoryImpl.getRestaurantList(id))
    }

    @Test
    fun `리스트_페이지의_아이디에_대한_상세_페이지_데이터를_잘_가져오는지에_대한_테스트`() {
        //given

        //when

        //then
    }
}