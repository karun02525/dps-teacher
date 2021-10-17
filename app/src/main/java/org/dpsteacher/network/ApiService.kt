package org.dpsteacher.network

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import org.dpsteacher.model.AssignStudentsModel
import org.dpsteacher.model.DashboardModel
import org.dpsteacher.model.LoginTeacherModel
import org.dpsteacher.model.ProfileModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("/api/teacher/auth/login")
    fun loginByTeacherAsync(@Body param: HashMap<String, String>): Deferred<Response<LoginTeacherModel>>

    @GET("/api/teacher/get-students")
    fun getStudentsListAsync(@Query("class_id") class_id: String,@Query("section") section: String): Deferred<Response<AssignStudentsModel>>

    @POST("/api/teacher/attendance")
    fun submitAttendanceAsync(@Body params: RequestBody): Deferred<Response<JsonObject>>

    @GET("/api/teacher/dashboard")
    fun getDashboardAsync(@Query("teacher_id") teacher_id: String): Deferred<Response<DashboardModel>>






    //......>>>>NEw...............

    @GET("/api/teacher-info/{id}")
    fun getProfileAsync(@Path("id") id: String): Deferred<Response<ProfileModel>>



}