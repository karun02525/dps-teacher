package org.dpsteacher.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dpsteacher.App
import org.dpsteacher.R
import org.dpsteacher.model.DashboardData
import org.dpsteacher.network.ApiStatus
import org.dpsteacher.network.RestClient

class DashboardViewModel(private val restClient: RestClient) : ViewModel() {

     val errorMsg = MutableLiveData<String>()
     val dashboardData = MutableLiveData<DashboardData>()



    fun dashboard(parent_id: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                restClient.webServices().getDashboardAsync(parent_id).await().let {
                    if (it.isSuccessful)
                        dashboardData.value = it.body()!!.data!!
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
