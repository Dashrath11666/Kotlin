package fundamentals

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * Coroutines are lightweight threads.
 * Like threads - they can run in parallel, wait for each other, and communicate with each other.
 */

fun threadFun() {
    println("start main thread = ${Thread.currentThread().name}")
    thread {
        println("inside new thread, name = ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("ending new thread, name = ${Thread.currentThread().name}")
    }
    println("end main thread = ${Thread.currentThread().name}")
}

fun simpleCoroutine() { /** Executes in main thread **/

    println("start main thread = ${Thread.currentThread().name}")

    GlobalScope.launch { // let's say executes on thread T1

        println("inside coroutine thread = ${Thread.currentThread().name}")
//        Thread.sleep(1000) // blocks this entire thread. instead, use delay()
        delay(1000) /** the coroutine is suspended, but the thread T1 is free **/

        println("ending coroutine thread = ${Thread.currentThread().name}") // may execute on thread T1 or any other thread.
    }
//    Thread.sleep(2000) // thread blocking call of course
    runBlocking { // creates a coroutine on main thread, and hence blocks the current thread i.e. the thread it operates on.
        delay(2000)
    }
    println("end main thread = ${Thread.currentThread().name}")
}

//fun main() {
//    threadFun()
//    simpleCoroutine()
//}

/**
 * Coroutine Builders
 * --------------------------
 *    launch, async, runBlocking
 */

// launch
// 1. inherits the thread & coroutine scope of the immediate parent coroutine.
// 2. does not block the current thread

suspend fun launchCoroutines() {
     // Creates a coroutine in the globalScope or app scope
    GlobalScope.launch {
        // File Download
        // Play Music
    }

    // Creates a coroutine in the local scope
    coroutineScope {
        val job = launch {
            // Login
            // Computation
        }
        job.join()
        job.cancel()
    }
}