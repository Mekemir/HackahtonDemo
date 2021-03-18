package com.example.hackahtondemo.util.callback

import com.example.hackahtondemo.util.api.model.CollectionApiResponse

interface CustomCallback {

    fun onDataCollected(response: CollectionApiResponse?) {}
}