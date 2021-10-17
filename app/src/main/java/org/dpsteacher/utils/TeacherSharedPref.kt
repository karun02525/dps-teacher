package org.dpsteacher.utils

import android.content.Context
import org.dpsteacher.App


class TeacherSharedPref private constructor() {

    companion object {
        val instance = TeacherSharedPref()
        private const val preference_file_key = "_tech_pre"

        private const val LANGUAGE = "language"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val Teacher_id = "t_id"
        private const val REG_ID = "parent_id"
        private const val FULL_NAME = "fullname"
        private const val SNAME = "sname"
        private const val TEACHER_AVATAR = "tavatar"
        private const val DOB = "dob"
        private const val PHONE1 = "phone1"
        private const val PHONE2 = "phone2"
        private val EMAIL_ID = "email"
        private val Qualification = "qualification"
        private val Address = "address"
        private val PostOffice = "postoffice"
        private val Pincode = "pincode"
        private val Dist = "dist"
        private val State = "state"
        private val Country = "country"
        private val ParentOccupation = "parentOccupation"
        private val ParentFname = "pfname"
        private val ParentLname = "plname"
        private val ParentSname = "psname"
        private val Section="section"
        private val ClassName="class_name"
        private val ClassId="class_id"

        private val GENDER = "gender"
        private val MOBILE_NUMBER = "mobile_number"
        private val EarningPercentage = "earning_percentage"

        private val PROFILE_AVATAR = "profile_picture_url"
        private val AUTH_TOKEN = "auth_token"
        private val RefreshToken_TOKEN = "refreshToken"
        private val Employee_code = "Employee_code"
        private val Branch = "branch"
        private val Region = "region"

        private val Partners = "Partners"
    }


    private val sharedPref =
        App.appContext!!.getSharedPreferences(preference_file_key, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()


    var isLoginStatus: Int
        get() = sharedPref.getInt(IS_LOGGED_IN, 0)
        set(isLoginStatus) {
            editor.putInt(IS_LOGGED_IN, isLoginStatus)
            editor.apply()
        }


    var regId: String?
        get() = sharedPref.getString(REG_ID, "----")
        set(regId) {
            editor.putString(REG_ID, regId)
            editor.apply()
        }
    var teacherId: String?
        get() = sharedPref.getString(Teacher_id, "----")
        set(teacherId) {
            editor.putString(Teacher_id, teacherId)
            editor.apply()
        }
    var fullname: String?
        get() = sharedPref.getString(FULL_NAME, "----")
        set(fullname) {
            editor.putString(FULL_NAME, fullname)
            editor.apply()
        }
    var sname: String?
        get() = sharedPref.getString(SNAME, "----")
        set(sname) {
            editor.putString(SNAME, sname)
            editor.apply()
        }
    var teacherAvatar: String?
        get() = sharedPref.getString(TEACHER_AVATAR, "----")
        set(teacher_avatar) {
            editor.putString(TEACHER_AVATAR, teacher_avatar)
            editor.apply()
        }

    var dob: String?
        get() = sharedPref.getString(DOB, "----")
        set(dob) {
            editor.putString(DOB, dob)
            editor.apply()
        }

    var phone1: String?
        get() = sharedPref.getString(PHONE1, "----")
        set(phone1) {
            editor.putString(PHONE1, phone1)
            editor.apply()
        }
    var phone2: String?
        get() = sharedPref.getString(PHONE2, "----")
        set(phone2) {
            editor.putString(PHONE2, phone2)
            editor.apply()
        }
    var email: String?
        get() = sharedPref.getString(EMAIL_ID, "----")
        set(email) {
            editor.putString(EMAIL_ID, email)
            editor.apply()
        }
    var qualification: String?
        get() = sharedPref.getString(Qualification, "----")
        set(qualification) {
            editor.putString(Qualification, qualification)
            editor.apply()
        }

    var address: String?
        get() = sharedPref.getString(Address, "----")
        set(address) {
            editor.putString(Address, address)
            editor.apply()
        }


    var postoffice: String?
        get() = sharedPref.getString(PostOffice, "----")
        set(postoffice) {
            editor.putString(PostOffice, postoffice)
            editor.apply()
        }

    var pincode: String?
        get() = sharedPref.getString(Pincode, "----")
        set(pincode) {
            editor.putString(Pincode, pincode)
            editor.apply()
        }
    var dist: String?
        get() = sharedPref.getString(Dist, "----")
        set(dist) {
            editor.putString(Dist, dist)
            editor.apply()
        }

    var state: String?
        get() = sharedPref.getString(State, "----")
        set(state) {
            editor.putString(State, state)
            editor.apply()
        }
    var country: String?
        get() = sharedPref.getString(Country, "----")
        set(country) {
            editor.putString(Country, country)
            editor.apply()
        }

    var pfname: String?
        get() = sharedPref.getString(ParentFname, "----")
        set(pfname) {
            editor.putString(ParentFname, pfname)
            editor.apply()
        }

    var plname: String?
        get() = sharedPref.getString(ParentLname, "----")
        set(plname) {
            editor.putString(ParentLname, plname)
            editor.apply()
        }


    var psname: String?
        get() = sharedPref.getString(ParentSname, "----")
        set(psname) {
            editor.putString(ParentSname, psname)
            editor.apply()
        }

    var parentOccupation: String?
        get() = sharedPref.getString(ParentOccupation, "----")
        set(parentOccupation) {
            editor.putString(ParentOccupation, parentOccupation)
            editor.apply()
        }

    var class_name: String?
        get() = sharedPref.getString(ClassName, "----")
        set(class_name) {
            editor.putString(ClassName, class_name)
            editor.apply()
        }


    var section: String?
        get() = sharedPref.getString(Section, "----")
        set(section) {
            editor.putString(Section, section)
            editor.apply()
        }

    var class_id: String?
        get() = sharedPref.getString(ClassId, "----")
        set(class_id) {
            editor.putString(ClassId, class_id)
            editor.apply()
        }






    var branch: String?
        get() = sharedPref.getString(Branch, "")
        set(branch) {
            editor.putString(Branch, branch)
            editor.apply()
        }

    var region: String?
        get() = sharedPref.getString(Region, "")
        set(region) {
            editor.putString(Region, region)
            editor.apply()
        }


    var partners: String?
        get() = sharedPref.getString(Partners, "")
        set(partners) {
            editor.putString(Partners, partners)
            editor.apply()
        }


    var checkLanguage: String?
        get() = sharedPref.getString(LANGUAGE, "")
        set(language) {
            editor.putString(LANGUAGE, language)
            editor.apply()
        }

    var mobileNumber: String?
        get() = sharedPref.getString(MOBILE_NUMBER, "")
        set(mobile) {
            editor.putString(MOBILE_NUMBER, mobile)
            editor.apply()
        }

    var earningPercentage: String?
        get() = sharedPref.getString(EarningPercentage, "")
        set(earning_percentage) {
            editor.putString(EarningPercentage, earning_percentage)
            editor.apply()
        }


    var gender: String?
        get() = sharedPref.getString(GENDER, "----")
        set(gender) {
            editor.putString(GENDER, gender)
            editor.apply()
        }

    var profileAvatar: String?
        get() = sharedPref.getString(PROFILE_AVATAR, "")
        set(avatar) {
            editor.putString(PROFILE_AVATAR, avatar)
            editor.apply()
        }

    var authToken: String?
        get() = sharedPref.getString(AUTH_TOKEN, "")
        set(authToken) {
            editor.putString(AUTH_TOKEN, authToken)
            editor.apply()
        }

    var refreshToken: String?
        get() = sharedPref.getString(RefreshToken_TOKEN, "")
        set(refreshToken) {
            editor.putString(RefreshToken_TOKEN, refreshToken)
            editor.apply()
        }


    fun logOutTeacher() {
        val lang = TeacherSharedPref.instance.checkLanguage
        editor.clear().apply()
        TeacherSharedPref.instance.checkLanguage = lang!!
    }
}
