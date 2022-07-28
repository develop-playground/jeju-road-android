package github.dev_playground.jeju_road.data

import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.model.*
import github.dev_playground.jeju_road.data.repository.RestaurantRepositoryImpl
import github.dev_playground.jeju_road.test_module.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RestaurantRepositoryImplTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val page: Int = 0
    private val id: Long = 0L
    private lateinit var restaurantRepositoryImpl: RestaurantRepositoryImpl
    private lateinit var restaurantApi: RestaurantApi

    private val restaurantContentData = RestaurantData(
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

    private val restaurantDetailData = RestaurantDetailData(
        message = "",
        detailInformation = DetailInformationData(
            id = 0L,
            name = "name",
            images = null,
            menus = listOf(),
            wayToGo = "wayToGo",
            simpleAddress = "simpleAddress",
            detailAddress = "detailAddress",
            openTimes = listOf(),
            introduction = "intro",
            tips = listOf()
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
    fun `요청한_리스트_페이지의_데이터를_잘_가져오는지에_대한_테스트`() = coroutineRule.runBlockingTest {

        //given
        whenever(restaurantApi.getRestaurantList(page))
            .thenReturn(restaurantContentData)

        //when
        val result = restaurantApi.getRestaurantList(page).information.content.map { it.toDomain() }

        //then
        assertEquals(restaurantRepositoryImpl.getRestaurantList(page), result)
    }

    @Test
    fun `리스트_페이지의_아이디에_대한_상세_페이지_데이터를_잘_가져오는지에_대한_테스트`() = coroutineRule.runBlockingTest {

        //given
        whenever(restaurantApi.getRestaurantDetail(id))
            .thenReturn(restaurantDetailData)

        //when
        val result = restaurantApi.getRestaurantDetail(id).detailInformation.toDomain()

        //then
        assertEquals(restaurantRepositoryImpl.getRestaurantDetail(id), result)

    }
}