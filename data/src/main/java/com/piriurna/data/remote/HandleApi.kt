package com.piriurna.data.remote

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object HandleApi {

    suspend fun <T> safeApiCall(callFunction: suspend () -> T): T {
        return try{

            callFunction.invoke()

        }
        catch (ex: Exception){
            when(ex){
                is HttpException -> {
                    throw ex
                }
                is SocketTimeoutException -> throw ex//emitter.onError(ErrorType.TIMEOUT)
                is IOException -> throw ex//emitter.onError(ErrorType.NETWORK)
                else -> throw ex//

            }
        }
    }
}