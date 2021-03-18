package com.example.hackahtondemo.fragments.resultspage

import android.app.Application
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.library.baseAdapters.BR
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import com.example.hackahtondemo.R
import com.example.hackahtondemo.util.api.DemoApiService
import com.example.hackahtondemo.util.api.ServiceBuilder
import com.example.hackahtondemo.util.api.model.CollectionApiResponse
import com.example.hackahtondemo.util.callback.CustomCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class ResultsViewModel(
    application: Application,
    val callback: CustomCallback?
) : AndroidViewModel(application), Observable {

    private var mCallbacks = PropertyChangeRegistry()

    @get:Bindable
    var headerInfoText by Delegates.observable(application.getString(R.string.collecting_data)) { _, _, _ ->
        mCallbacks.notifyCallbacks(
            this,
            BR.headerInfoText,
            null
        )
    }

    @get:Bindable
    var progressVisibility by Delegates.observable(VISIBLE) { _, _, _ ->
        mCallbacks.notifyCallbacks(
            this,
            BR.progressVisibility,
            null
        )
    }

    init {
        getRemoteFromApi()
    }

    private fun getRemoteFromApi() {
        val request = ServiceBuilder.buildService(DemoApiService::class.java)
        val call = request.getMovies()

        call.enqueue(object : Callback<CollectionApiResponse> {
            override fun onResponse(
                call: Call<CollectionApiResponse>,
                response: Response<CollectionApiResponse>
            ) {
                if (response.isSuccessful) {
                    callback?.onDataCollected(response.body())
                    headerInfoText = "Collected data:"
                    progressVisibility = GONE

                    Toast.makeText(
                        getApplication(),
                        "API call is executed successfully!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<CollectionApiResponse>, t: Throwable) {
                Toast.makeText(getApplication(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }
}