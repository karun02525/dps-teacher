package org.dpsteacher.model
import com.google.gson.annotations.SerializedName


data class ProfileModel(
    @SerializedName("data")
    var `data`: DataProfileModel,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Boolean
)

data class DataProfileModel(
    @SerializedName("student")
    var student: List<StudentModel>,
    @SerializedName("teacher")
    var teacher: TeacherModel
)



