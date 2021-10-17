package org.dpsteacher.model
import com.google.gson.annotations.SerializedName


data class LoginTeacherModel(
    @SerializedName("data")
    var `data`: TeacherData?,
    @SerializedName("message")
    var message: String?
)

data class TeacherData(
    @SerializedName("address")
    var address: String?,
    @SerializedName("alternate_no")
    var alternateNo: String?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("dist")
    var dist: String?,
    @SerializedName("dob")
    var dob: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("parent_fname")
    var parentFname: String?,
    @SerializedName("parent_lname")
    var parentLname: String?,
    @SerializedName("parent_sname")
    var parentSname: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("pincode")
    var pincode: String?,
    @SerializedName("post_office")
    var postoffice: String?,
    @SerializedName("qualification")
    var qualification: String?,
    @SerializedName("registration_no")
    var registrationNo: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("token")
    var token: String?,
    @SerializedName("teacher_picture")
    var teacher_picture: String?,
    @SerializedName("parent_occupation")
    var parent_occupation: String?
)