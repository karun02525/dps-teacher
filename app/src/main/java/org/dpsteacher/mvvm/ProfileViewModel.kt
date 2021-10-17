package org.dpsteacher.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import org.dpsteacher.App
import org.dpsteacher.R
import org.dpsteacher.network.ApiStatus
import org.dpsteacher.network.RestClient
import org.dpsteacher.model.AssignStudentsData
import org.dpsteacher.model.DataProfileModel
import org.json.JSONObject

class ProfileViewModel(private val restClient: RestClient) : ViewModel() {

    val errorMsg = MutableLiveData<String>()
    val data = MutableLiveData<List<AssignStudentsData>>()
    val success = MutableLiveData<String>()
    val errorMsgSubmit = MutableLiveData<String>()


    val dataProfile = MutableLiveData<DataProfileModel>()



    fun getProfile(teacher_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getProfileAsync(teacher_id).await().let {
                    if (it.isSuccessful)
                        dataProfile.value = it.body()!!.data
                    else
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }



    fun getStudentsList(class_id: String,section: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getStudentsListAsync(class_id,section).await().let {
                    if (it.isSuccessful)
                        data.value = it.body()!!.data!!
                    else
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }

    fun submitAttendance(params: RequestBody) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().submitAttendanceAsync(params).await().let {
                    if (it.isSuccessful)
                        success.value = JSONObject(it.body().toString()).optString("message")
                    else
                        errorMsgSubmit.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsgSubmit.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }
}