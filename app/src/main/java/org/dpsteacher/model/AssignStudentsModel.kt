package org.dpsteacher.model

import com.google.gson.annotations.SerializedName


data class AssignStudentsModel(
    @SerializedName("data")
    var `data`: List<AssignStudentsData>?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class AssignStudentsData(
    @SerializedName("address")
    var address: String?,
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("classes")
    var classes: ClassesInfo?,
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
    @SerializedName("father_fname")
    var fatherFname: String?,
    @SerializedName("father_lname")
    var fatherLname: String?,
    @SerializedName("father_sname")
    var fatherSname: String?,
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("_id")
    var id: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("mother_fname")
    var motherFname: String?,
    @SerializedName("mother_lname")
    var motherLname: String?,
    @SerializedName("mother_sname")
    var motherSname: String?,
    @SerializedName("parent_id")
    var parentId: String?,
    @SerializedName("parent_occupation")
    var parentOccupation: String?,
    @SerializedName("parent_phone")
    var parentPhone: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("pincode")
    var pincode: String?,
    @SerializedName("police_station")
    var policeStation: String?,
    @SerializedName("post_office")
    var postOffice: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("student_picture")
    var studentPicture: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("userId")
    var userId: String?,
    @SerializedName("__v")
    var v: Int?,
    var flag: Int = 2
)

data class ClassesInfo(
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("class_name")
    var className: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("_id")
    var id: String?,
    @SerializedName("roll_no")
    var rollNo: String?,
    @SerializedName("section")
    var section: String?,
    @SerializedName("student_id")
    var studentId: String?,
    @SerializedName("__v")
    var v: Int?
)