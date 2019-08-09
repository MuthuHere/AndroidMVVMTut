package com.muthu.androidmvvm.data.network

import com.muthu.androidmvvm.util.APIExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiResponse {

  public  suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))

                } catch (e: JSONException) {
                }

            }
            message.append("\n")
            message.append("Error code {${response.code()}}")

            throw APIExceptions(message.toString())

        }
    }
}