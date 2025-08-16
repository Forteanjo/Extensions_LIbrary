package sco.carlukesoftware.extensions

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
inline fun doWithApi(sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT == sdkCode) {
        block()
    }
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
inline fun doWithAtLeastApi(sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= sdkCode) {
        block()
    }
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
inline fun doWithAtMostApi(sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT <= sdkCode) {
        block()
    }
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
inline fun doWithHigherApi(sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT > sdkCode) {
        block()
    }
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
inline fun doWithLowerApi(sdkCode: Int, block: () -> Unit) {
    if (Build.VERSION.SDK_INT < sdkCode) {
        block()
    }
}
