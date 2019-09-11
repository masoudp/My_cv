package com.mpakbaz.mycv

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.mpakbaz.mycv.common.TestComponentRule
import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.features.detail.DetailActivity
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class BDetailActivityTest {

    private val component = TestComponentRule(InstrumentationRegistry.getTargetContext())
    private val main = ActivityTestRule(DetailActivity::class.java, false, true)

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    @JvmField
    var chain: TestRule = RuleChain.outerRule(component).around(main)

    @Test
    fun checkCVDataDisplays() {
        val response = TestDataFactory.makeTestGetCVResponse()
        stubDataManagerGetCVData(Single.just(response))


        onView(withId(R.id.tv_name))
                .check(matches(withText(response.result?.basics?.name)))
                .check(matches(isDisplayed()))

    }


    private fun stubDataManagerGetCVData(single: Single<ApiSingleResponse<CVData>>) {
        `when`(component.mockDataManager.getMyCv())
                .thenReturn(single)
    }

}