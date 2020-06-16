package com.test.movietestapp.data.network.factory

import com.test.movietestapp.data.network.exception.RetrofitException
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class RxCallAdapterWrapper<R>(
    private val retrofit: Retrofit?,
    private val wrapped: CallAdapter<R, *>
) :
    CallAdapter<R, Any> {

    override fun responseType(): Type = wrapped.responseType()

    override fun adapt(call: Call<R>): Any {
        return convert(wrapped.adapt(call))
    }

    private fun convert(o: Any): Any {
        return when (o) {
            is Completable -> o.onErrorResumeNext { throwable ->
                Completable.error(
                    handleErrorToShow(
                        throwable
                    )
                )
            }
            is Single<*> -> o.onErrorResumeNext { throwable ->
                Single.error(
                    handleErrorToShow(
                        throwable
                    )
                )
            }
            else -> throw RuntimeException("Not allowed reactive call = $o")
        }
    }

    private fun handleErrorToShow(throwable: Throwable): RetrofitException {
        return asRetrofitException(throwable)
    }

    private fun asRetrofitException(throwable: Throwable): RetrofitException {
        if (throwable is HttpException) {
            val response = throwable.response()
            return RetrofitException.httpError(
                response.raw().request().url().toString(),
                response,
                retrofit
            )
        }

        if (throwable is TimeoutException ||
            throwable is ConnectException ||
            throwable is SocketTimeoutException ||
            throwable is UnknownHostException
        ) {
            return RetrofitException.networkError(IOException(throwable.message, throwable))
        }

        return RetrofitException.unexpectedError(throwable)
    }
}