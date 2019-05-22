package com.sample.renovatio.analytics.Main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.sample.renovatio.analytics.model.DataModel.SummonerDTO
import com.sample.renovatio.analytics.R
import com.sample.renovatio.analytics.main.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val mockSummonerData = SummonerDTO(
        accountId = "1",
        id = "id",
        name = "Test Account",
        profileIconId = 1,
        puuid = "1",
        revisionDate = 123456789L,
        summonerLevel = 1
    )

    @Before
    fun check_init_view(){
//        val behavior = NetworkBehavior.create().apply {
//            setErrorFactory {  }
//        }

        // 화면에 소환사 이름 입력창이 뜨는지 확인한다
        onView(withId(R.id.main_activity_search_editext_view)).check(matches(isDisplayed()))

        // 화면에 검색 버튼이 보이는지 확인한다.
        onView(withId(R.id.main_activity_search_button)).check(matches(isDisplayed()))

        // 화면에 결과창이 뜨는지 확인한다
        onView(withId(R.id.main_activity_search_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun searchSummonerData_Success(){
        // 소환사 이름을 입력한다.
        onView(withId(R.id.main_activity_search_editext_view)).perform(typeText(mockSummonerData.name))

        // 검색 버튼 클릭
        onView(withId(R.id.main_activity_search_button)).perform(click())

        // 결과가 보여진다
        onView(withId(R.id.main_activity_search_text_view)).check(matches(withText(mockSummonerData.toString())))
    }

    @Test
    fun searchSummonerData_fail_no_data() {
        // 존재하지 않는 소환사 이름을 입력한다.
        val noExistSummonerName = "No Exist Summoner Name"
        onView(withId(R.id.main_activity_search_editext_view)).perform(typeText(noExistSummonerName))

        // 검색 버튼 클릭
        onView(withId(R.id.main_activity_search_button)).perform(click())

        // 스낵바를 보여준다
        onView(allOf(withId(R.id.snackbar_text),withText(R.string.data_not_found))).check(matches(isDisplayed()))
    }

    @Test
    fun searchSummonerData_fail_empty() {
    }

    @Test
    fun searchSummoner() {
    }

    @Test
    fun setSummonerData() {
    }

    @Test
    fun onStop() {
    }
}
