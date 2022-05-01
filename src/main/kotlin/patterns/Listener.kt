package patterns

class Listener {
}

class SingleTonPattern private constructor() {

    companion object {
        private val instance: SingleTonPattern? = null
        @Synchronized
        fun  getInstance(): SingleTonPattern {
            println("init")
            return instance ?: SingleTonPattern()
        }
    }

    fun fun1(): Unit {
        println("one")
    }

    fun fun2(): Unit {
        println("two")
    }
}

fun main() {
    val pattern = SingleTonPattern.getInstance()
    pattern.fun1()
    pattern.fun2()
    val pattern1 = SingleTonPattern.getInstance()
    pattern1.fun2()
}