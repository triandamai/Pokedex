/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */



package app.trian.pokedex.data.common

import android.util.Log
import app.trian.pokedex.data.model.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeApiCall(call: () -> HttpResponse): Response<T> {
    return try {
        val response = call.invoke()
        if (response.status.value in 200..209) {
            val data = response.body<BaseResponse<T>>()
            Response.Result(data.data)
        } else {
            val data = response.body<BaseResponse<List<Any>>>()
            Response.Error(data.message, data.code)
        }
    }catch (e:Exception){
        Log.e("HAHA",e.message.orEmpty())
        Response.Error(e.message.orEmpty())
    }
}
