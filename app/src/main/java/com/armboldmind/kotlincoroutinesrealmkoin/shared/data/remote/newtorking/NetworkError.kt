package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.armboldmind.kotlincoroutinesrealmkoin.KotlinCoroutinesRealmKoin
import com.armboldmind.kotlincoroutinesrealmkoin.R
import retrofit2.HttpException
import java.net.HttpURLConnection

class NetworkError(private var mError: Throwable) : Throwable(mError) {
    private val mContext = KotlinCoroutinesRealmKoin.getInstance()
    private var errorMessage = mContext.resources.getString(R.string.default_error_message)

    init {
        isAuthError()
    }

    private fun isAuthError() {
        if (mError is HttpException) {
            if ((mError as HttpException).code() == HttpURLConnection.HTTP_UNAUTHORIZED || (mError as HttpException).code() == HttpURLConnection.HTTP_FORBIDDEN) {
                errorMessage = "Unauthorized."
            }
        }
    }

    fun getAppErrorMessage(): String {
        return if (isInternetAvailable()) mContext.resources.getString(R.string.default_error_message)
        else mContext.resources.getString(R.string.network_error_message)
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
            false
        } else {
            connectivityManager?.activeNetworkInfo?.run {
                when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            }
            false
        }
    }
}