package org.dpsteacher.ui.authenticaton

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.dpsteacher.ui.MainActivity
import org.dpsteacher.R
import org.dpsteacher.model.TeacherData
import org.dpsteacher.utils.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()
    private val spTeacher by lazy { TeacherSharedPref.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViewModel()
        btnLogin.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() {
        val username = edit_username.text.toString()
       // val pass = edit_password.text.toString()
        val pass = username

        when {
            username.isBlank() -> {
                mess("Please enter username")
            }
           /* pass.isBlank() -> {
                mess("Please enter password")
            }*/
            else -> {
                hideShowProgress(true)
                    viewModel.loginByTeacher(username, pass)
            }
        }
    }

    private fun setupViewModel() {

        viewModel.loginTeacherData.observe(this, Observer {
            log("TAGS => Teacher Login Data:   $it")
            hideShowProgress(false)
            successfullyTeacherLogin(it)
        })

        viewModel.errorMsg.observe(this, Observer {
            log("TAGS => Parent/Teacher Login Error :   $it")
            hideShowProgress(false)
            toast(it)
        })
    }

    private fun successfullyTeacherLogin(it: TeacherData?) {
        it.let {
            spTeacher.run {
                isLoginStatus = 1
                teacherId = it?.id
                regId = it?.registrationNo
                fullname = it?.fname +" "+ it?.lname
                sname = it?.surname
                gender = it?.gender
                authToken = it?.token
                dob=it?.dob
                qualification=it?.qualification
                address=it?.address
                postoffice=it?.postoffice
                pincode=it?.pincode
                dist=it?.dist
                state=it?.state
                country=it?.country
                parentOccupation=it?.parent_occupation
                pfname=it?.parentFname
                plname=it?.parentLname
                psname=it?.parentSname
                startNewActivityFinish(MainActivity::class.java)
            }
        }
    }

    private fun mess(s: String) {
        hideSoftKeyboard()
        messToast(s)
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}