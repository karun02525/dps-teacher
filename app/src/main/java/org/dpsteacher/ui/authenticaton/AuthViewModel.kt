package org.dpsteacher.ui.authenticaton

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dpsteacher.App
import org.dpsteacher.R
import org.dpsteacher.network.ApiStatus
import org.dpsteacher.network.RestClient
import org.dpsteacher.model.TeacherData

class AuthViewModel(private val restClient: RestClient) : ViewModel() {
     val errorMsg = MutableLiveData<String>()
     val loginTeacherData = MutableLiveData<TeacherData>()


    fun loginByTeacher(id: String, pass:String) {
        val params= HashMap<String, String>()
        params["registration_no"] = id
        params["password"] = pass
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().loginByTeacherAsync(params).await().let {
                    if (it.isSuccessful)
                        loginTeacherData.value = it.body()!!.data!!
                    else
                        errorMsg.value = ApiStatus.isCheckAPIStatus(it.code(), it.errorBody())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                errorMsg.value = App.appContext?.getString(R.string.no_internet_available)
            }
        }
    }
}
