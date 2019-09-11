package com.mpakbaz.mycv

import androidx.test.InstrumentationRegistry
import com.mpakbaz.mycv.common.TestComponentRule
import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.features.login.LoginActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class ALoginActivityTest {

    private val mComponent = TestComponentRule(InstrumentationRegistry.getTargetContext())
    private val mMain = ActivityTestRule(LoginActivity::class.java, false, false)

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule @JvmField
    var chain: TestRule = RuleChain.outerRule(mComponent).around(mMain)

    @Test
    fun clickingLoginLaunchesDetailActivity() {
        val loginResponse = TestDataFactory.makeTestLoginResponse()
        stubDataManagerGetLoginResponse(Single.just(loginResponse))
        mMain.launchActivity(null)

        onView(withId(R.id.btn_login))
                .perform(click())

    }

    private fun stubDataManagerGetLoginResponse(single: Single<ApiSingleResponse<LoginResponse>>) {
        `when`(mComponent.mockDataManager.login(TestDataFactory.makeTestUserName(), TestDataFactory.makeTestPassword()))
                .thenReturn(single)
    }

}