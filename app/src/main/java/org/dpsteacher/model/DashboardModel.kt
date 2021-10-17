package org.dpsteacher.model
import com.google.gson.annotations.SerializedName


data class DashboardModel(
    @SerializedName("data")
    var `data`: DashboardData?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)

data class DashboardData(
    @SerializedName("banner")
    var banner: Any?,
    @SerializedName("class_info")
    var classInfo: ClassInfo?,
    @SerializedName("teacher")
    var teacher: Teacher?
)

data class ClassInfo(
    @SerializedName("class_id")
    var classId: String?,
    @SerializedName("class_name")
    var className: String?,
    @SerializedName("section")
    var section: String?
)

data class Teacher(
    @SerializedName("email")
    var email: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("alternate_no")
    var alternate_no: String?,
    @SerializedName("teacher_picture")
    var teacherPicture: String?
)