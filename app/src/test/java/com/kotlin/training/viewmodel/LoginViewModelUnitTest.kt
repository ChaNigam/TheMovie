package com.kotlin.training.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations


//@RunWith(value = Parameterized::class)
@RunWith(value = JUnit4::class)
class LoginViewModelUnitTest {
    private lateinit var viewModel: LoginViewModel
    private lateinit var nameLiveData: LiveData<String>
    private lateinit var passwordLiveData: MutableLiveData<String>
    private lateinit var errorMessageLiveData: MutableLiveData<Int>
    private lateinit var loggedInLiveData: MutableLiveData<Boolean>

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel()
        nameLiveData = viewModel.name
        passwordLiveData = viewModel.password
        errorMessageLiveData = viewModel.errorMessage
        loggedInLiveData = viewModel.loggedIn

    }

    @Test
    fun usernameBlankTest() {
        viewModel.login("", "")
        loggedInLiveData.let { assertFalse(false) }

    }

    @Test
    fun passwordBlankTest() {

        viewModel.login("Test", "")
        loggedInLiveData.let { assertFalse(false) }

    }

    @Test
    fun passwordValidatorTest() {
        viewModel.login("Test", "Test")
        loggedInLiveData.let { assertFalse(false) }

    }

    @Test
    fun loginTest() {

        viewModel.login("Test", "Test123")
        loggedInLiveData.let { assertTrue(true) }

    }


}
