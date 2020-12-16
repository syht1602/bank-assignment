package com.backbase.assignment.ui.bases

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.backbase.assignment.ui.db.databaseModule
import com.backbase.assignment.ui.di.gsonModule
import com.backbase.assignment.ui.di.viewModelModule
import com.backbase.assignment.ui.interfaces.IBaseActivity
import com.backbase.assignment.ui.network.networkModule
import com.backbase.assignment.ui.repositories.movieModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Create Base activity for data binding and view model binding
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(),
    IBaseActivity {
    private lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@BaseActivity)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    movieModule,
                    databaseModule,
                    gsonModule
                )
            )
        }
        //Set portrait mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayout())
        onCreateInit()
    }

    /**
     * Must declare a layout for any activity
     */
    @LayoutRes
    abstract fun getLayout(): Int

    override fun onCreateInit() {}

    open fun isInternetAvailable(): Boolean {
        var isConnected = false
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                activeNetworkInfo?.run {
                    isConnected = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        if (!isConnected) {
            Toast.makeText(this, "Internet error", Toast.LENGTH_SHORT).show()
        }
        return isConnected
    }
}
