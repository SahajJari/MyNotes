package com.sahaj.mynotes.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.*

fun Context.showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun <T> lazyDeffered(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> = lazy {
    GlobalScope.async(start = CoroutineStart.LAZY) {
        block()
    }
}