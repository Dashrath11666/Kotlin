/**
 * Enum Classes
 * -----------------------------
 * - Group together a finite number of constant values.
 * - Enum Objects are constant values, cannot be changed once created.
 * - Each enum constant is a separate instance of enum class. they have properties ordinal & name, and have methods values() & valueOf().
 * - Assign own properties to each enum object using constructors.
 * - Enum class can implement interfaces, but cannot inherit abstract or open classes.
 * - We can use enum constants as anonymous classes.
 */

interface ICardCashBack {
    fun getCashBackValue(): Float
}


/** Enum constants are basically objects of the enum class type. i.e. SILVER is an object of class `CreditCardType` **/
enum class CreditCardType(val color: String, val id: String): ICardCashBack {
    SILVER("red", "1"){
        override fun getCashBackValue(): Float {
            return 0.02f
        }
                      },
    GOLD("blue", "2") {
        override fun getCashBackValue(): Float {
            return 0.3f
        }
    },
    PLATINUM("black", "3") {
        override fun getCashBackValue(): Float {
            return 1f
        }
    },
}

//fun main(args: Array<String>) {
//    val card = CreditCardType.GOLD
//    println("${card.ordinal}, ${card.name}")
//    println(card.color)
//    println(card.id)
//    val ans: Array<CreditCardType> = CreditCardType.values()
//    val gold = CreditCardType.valueOf("GOLD")
//}


/**
 * Sealed Classes
 * ----------------------------
 * 1. Basically used to restrict the types of some entities :- Similar to enum, but allows non-static implementations...
 *      ensures type safety by restricting the set of types at compile time only.
 * 2. The subclass of the sealed must be declared in the same file as that of the sealed class.
 * 3. The subclass can be anything, data class, normal class, sealed class, object class, sealed interface.
 * 4. A sealed class is implicitly an abstract class, which cannot be instantiated.
 * 5. By default, the constructor of sealed class is private, and cannot be made non-private.
 */

sealed class Shape

object Square : Shape()
class Rectangle: Shape()
class Circle: Shape()

fun checkShape(shape: Shape) {
    when(shape) {
        is Circle -> print("circle")
        is Square -> print("square")
        is Rectangle -> print("rectangle")
    }
}

//fun main(a: Array<String>) {
//    val shape = Circle()
//    checkShape(shape)
//}


/**
 * Inheritance
 * ------------------------------
 * -> It's a mechanism by which an object acquires all the properties of a parent class object
 * 1. By Default all classes are public and final(cannot be inherited)...... methods are also public and final.
 * 2. All the classes inherit from class [ ANY ]... which has 3 simple methods
 *              a. fun equals(): Boolean, b. fun toString(): String, c. fun hashCode(): Int
 */

interface Anime {
    fun eat()
    fun waggingTail() {
        println("wagging tail in interface")
    }
}

open class Animal(private val color: String) {

    open val breed: String = "None"

    open fun eat(){
        println("Eating")
    }
}

class Cat(val color: String): Animal(color) {
    fun doMeow() {
        println("Meow....!")
    }
}

class Dog(val color: String): Animal(color), Anime {

    override val breed = "Labrador"

    fun doBark() {
        println("Woof....!")
    }

    @Override
    override fun eat() {
        super<Animal>.eat()
        println("Eat, Woof, Eat, Woof......")
    }

    override fun waggingTail() {
        super.waggingTail()
        println("wagging tail in class")
    }
}

//fun main(a: Array<String>) {
//    val cat = Cat("red")
//    cat.eat()
//    println(cat.color)
//    val dog = Dog("blue")
//    dog.doBark()
//    dog.eat()
//    dog.waggingTail()
//}

/**
 * Visibility Modifiers
 * -------------------------------
 * 1. public - everywhere
 * 2. private - nowhere else but the containing class
 * 3. protected - only within a subclass
 * 4. internal - within the module
 */


/**
 * Abstract Class, Methods & Properties
 * ---------------------------------------------
 * 1. The role of abstract classes is just to provide a set of properties and methods
 * 2. Abstract classes are partially defined classes
 * 3. Abstract methods have no body.
 * 4. Abstract properties cannot be initialized.
 * 5. Abstract properties, methods, classes are open in nature.
 * 6. Cannot instantiate an abstract class, need to override all the abstract properties and methods.
 */

abstract class Person {
    val defaultName = "dummy"
    abstract val name: String
    fun defaultPrint() {
        println("printing inside abstract class")
    }

    open fun overridePrinting() {
        println("printing inside overridable method")
    }

    abstract fun eat()
}

class Indian: Person() {
    override val name: String = "IndianName"
    override fun eat() {
        println("Indian is eating")
    }

    override fun overridePrinting() {
        super.overridePrinting()
        println("printing inside overriding method")
    }

}

//fun main(a: Array<String>) {
//    val indian = Indian()
//    println("${indian.defaultName} and ${indian.name}")
//    indian.eat()
//    indian.defaultPrint()
//    indian.overridePrinting()
//}

/**
 * Interfaces
 * --------------------
 * 1. Interfaces are not classes, hence cannot be inst.
 * 2. Cannot initialize properties. i.e. can contain only abstract properties
 * 3. Can contain both abstract and normal methods
 */

interface MyInterfaceListener { /** cannot create an instance of an interface **/
    val name: String /** Properties are abstract by default **/
    //val msg: String = "error"   // property init is now allowed inside interfaces
    fun onClick()    /** Methods are abstract by default **/
    fun onTouch() { /** Normal methods are public and open by default **/
        println("printing on touch")
    }
}

class MyButton: MyInterfaceListener {
    override val name: String = "Sample Button"
    override fun onClick() {
        TODO("Not yet implemented")
    }
}

//fun main(a: Array<String>) {
//    val btn = MyButton()
//    println(btn.name)
//    btn.onTouch()
//    btn.onClick()
//}

/**
 * Data Classes
 * --------------------------
 * - Simple class, used to hold data/state and contains std functionality.
 * - Internally contains following methods
 * - can have only property arguments, and at least one argument
 *  a. fun toString(): String, b. fun hashCode(): Int, c. fun equals(): Boolean, d. fun copy(), e. fun component() -> fn corresponding to the properties.
 */


/**
 * Object Declarations & Companion Objects
 * -------------------------------------------
 * - Singleton Class -> only object of the class exists in the entire app, cannot create more than one.
 * - We achieve this in java using ```static variables and static methods```
 *
 *  - Use keyword - ```object```
 *  - Kotlin internally creates a class and an object, i.e. instantiates.
 *  - Cannot have constructors
 */

open class SomeSuperClass(val id: String) {
    open fun superMethod() {
        println("printing in super method.")
    }
}

object Customer: SomeSuperClass("1") {
    const val name: String = "dummy_name" // behaves like a static variable
    init {
        println("inside init")
    }
    fun registerCustomer() { // behaves like a static method
        println("registering customer")
    }

    override fun superMethod() {
        super.superMethod() // super method can now be accessed wo creating an object.
        println("after superMethod")
    }
}

class CustomerV2(val id: String) {
    companion object {

        const val name: String = "dummy_name2" // behaves like static var

        @JvmStatic // for the jvm to understand that it's a static method.
        fun tryOfCustomers(): List<String> {
            return listOf("Indian")
        }
    }
}

//fun main(a: Array<String>) {
//    println(Customer.name)
//    Customer.registerCustomer()
//    Customer.superMethod()
//    println(CustomerV2.name)
//    CustomerV2.tryOfCustomers()
//}


/**
 * Lambdas & Higher Order Functions
 * -------------------------------------
 */

interface MyInterface {
    fun execute(s: Int);
}

class Program {
    fun addTwoNums(a: Int, b: Int, action: MyInterface) {
        val s = a + b
        action.execute(s)
    }

    fun addTwoNums(a: Int, b: Int, action: (Int) -> Unit) {
        val s = a + b
        action(s)
    }

    fun addTwoNums(a: Int, b: Int, action: (Int, Int) -> Unit){
        action(a, b)
    }

    fun reverse(s: String, func: (String) -> String) {
        println(s)
    }
}

//fun main(a: Array<String>) {
//    var result = 0
//    val p = Program()
//    p.addTwoNums(10, 10, object : MyInterface {
//        override fun execute(s: Int) {
//            println(s)
//        }
//    })
//
//    val showSum: (Int) -> Unit = { sum -> println(sum) }
//    p.addTwoNums(20, 30, showSum)
//    p.addTwoNums(1, 2) { x, y -> result = x + y } // `closures` :- able to modify outside variables
//    println(result)
//    p.reverse("Hello") { it.reversed() } // use `it` when there is only one variable
//}


/**
 * Collections
 * ------------------
 * 1. Arrays - `Mutable` but has `Fixed Size`
 * 2. Collections
 *    a. Immutable : listOf(), mapOf(), setOf()
 *    b. Mutable : mutableListOf(), ArrayList(), arrayListOf()
 *                 mutableSetOf(), hashSetOf()
 *                 HashMap(), hashMapOf(), mutableMapOf()
 *
 *   imp funs = filter, map
 *   predicates = Conditions that return true of false
 */


fun main() {
    val a = Array<Int>(10) { 0 }
    a[0] = 10
    a[9] = 9
    for (e in a) println(e)

    for (i in 0..(a.size.minus(1))) println(a[i])
}


/**
 * Null safety operators
 *  ? , ?. , ?: , !!
 */

/**
 *  "lateinit" keyword
 *  - Promise to the compiler that it's value will be initialized before using.
 *  1. can use it only with mutable variables
 *  2. use with non-nullable data types only
 *  // private lateinit var ans: String
 */

/**
 * "Lazy" Initialization
 * ---------------------------
 * - prevent unnecessary initialization of vars
 * - not initialized until used
 * - after first access it is stored in a cache
 */

/**
 * Backing Property
 * ------------------
 */
class Student {
    /**
     * since it's public variable, it can be mutated outside also,
     * but we want to mutate it only inside this class, hence expose a non-mutable variable
     */
    private val _hobbies = mutableListOf<String>() // backing field
    val hobbies: List<String> // backing property
    get() = _hobbies

    fun addHobby(hobby: String) {
        _hobbies.add(hobby)
    }
}

/**
 * Scope Functions
 * ----------------------
 *  1. with - returns lambda result, context object = this
 *  2. let - returns lambda result, context object = it
 *  3. apply - returns context object, context object = this
 *  4. run - returns lambda result, context object = this.....  with + let = run
 *  5. also - returns context object, context object = it
 */












