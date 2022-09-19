@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in 0 until v.size){
        abs += v[i].pow(2)
    }
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.size else 0.0

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val sr = list.sum() / list.size
    for (i in 0 until list.size) {
        list[i] -= sr
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0.0
    for (i in 0 until p.size) {
        px += p[i] * x.toDouble().pow(i)
    }
    return px.toInt()
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    val flist = list.toList()
    return if (list.size <= 1) list
    else {
        for (i in 0 until list.size) {
            list[i] += flist.subList(0, i).sum()
        }

        list
    }
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var vn = n
    for (i in 2..n) {
        while (vn > 0) {
            if (vn % i == 0) {
                vn /= i
                list.add(i)
            } else break
        }
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var str = ""
    var vn = n
    for (i in 2..n) {
        while (vn > 0) {
            if (vn % i == 0) {
                vn /= i
                str += "$i*"
            } else break
        }
    }
    return str.substring(0, str.length - 1)
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var vn = n
    val list = mutableListOf<Int>()
    while (vn > 0) {
        list.add(0, vn % base)
        vn /= base
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var vn = n
    var str = ""
    while (vn > 0) {
        if (vn % base <= 9) {
            str = (vn % base).toString() + str
        } else {
            when (vn % base) {
                10 -> str = "a" + str
                11 -> str = "b" + str
                12 -> str = "c" + str
                13 -> str = "d" + str
                14 -> str = "e" + str
                15 -> str = "f" + str
                16 -> str = "g" + str
                17 -> str = "h" + str
                18 -> str = "i" + str
                19 -> str = "j" + str
                20 -> str = "k" + str
                21 -> str = "l" + str
                22 -> str = "m" + str
                23 -> str = "n" + str
                24 -> str = "o" + str
                25 -> str = "p" + str
                26 -> str = "q" + str
                27 -> str = "r" + str
                28 -> str = "s" + str
                29 -> str = "t" + str
                30 -> str = "u" + str
                31 -> str = "v" + str
                32 -> str = "w" + str
                33 -> str = "x" + str
                34 -> str = "y" + str
                35 -> str = "z" + str
            }
        }
        vn /= base

    }
    return str
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var decnum = 0
    for (i in 0 until digits.size) {
        decnum += digits[i] * base.toDouble().pow(digits.size - 1 - i).toInt()
    }
    return decnum
}


/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var decnum = 0
    for (i in 0 until str.length) {
        when (str[i]) {
            'a' -> decnum += 10 * base.toDouble().pow(str.length - i - 1).toInt()
            'b' -> decnum += 11 * base.toDouble().pow(str.length - i - 1).toInt()
            'c' -> decnum += 12 * base.toDouble().pow(str.length - i - 1).toInt()
            'd' -> decnum += 13 * base.toDouble().pow(str.length - i - 1).toInt()
            'e' -> decnum += 14 * base.toDouble().pow(str.length - i - 1).toInt()
            'f' -> decnum += 15 * base.toDouble().pow(str.length - i - 1).toInt()
            'g' -> decnum += 16 * base.toDouble().pow(str.length - i - 1).toInt()
            'h' -> decnum += 17 * base.toDouble().pow(str.length - i - 1).toInt()
            'i' -> decnum += 18 * base.toDouble().pow(str.length - i - 1).toInt()
            'j' -> decnum += 19 * base.toDouble().pow(str.length - i - 1).toInt()
            'k' -> decnum += 20 * base.toDouble().pow(str.length - i - 1).toInt()
            'l' -> decnum += 21 * base.toDouble().pow(str.length - i - 1).toInt()
            'm' -> decnum += 22 * base.toDouble().pow(str.length - i - 1).toInt()
            'n' -> decnum += 23 * base.toDouble().pow(str.length - i - 1).toInt()
            'o' -> decnum += 24 * base.toDouble().pow(str.length - i - 1).toInt()
            'p' -> decnum += 25 * base.toDouble().pow(str.length - i - 1).toInt()
            'q' -> decnum += 26 * base.toDouble().pow(str.length - i - 1).toInt()
            'r' -> decnum += 27 * base.toDouble().pow(str.length - i - 1).toInt()
            's' -> decnum += 28 * base.toDouble().pow(str.length - i - 1).toInt()
            't' -> decnum += 29 * base.toDouble().pow(str.length - i - 1).toInt()
            'u' -> decnum += 30 * base.toDouble().pow(str.length - i - 1).toInt()
            'v' -> decnum += 31 * base.toDouble().pow(str.length - i - 1).toInt()
            'w' -> decnum += 32 * base.toDouble().pow(str.length - i - 1).toInt()
            'x' -> decnum += 33 * base.toDouble().pow(str.length - i - 1).toInt()
            'y' -> decnum += 34 * base.toDouble().pow(str.length - i - 1).toInt()
            'z' -> decnum += 35 * base.toDouble().pow(str.length - i - 1).toInt()
            else -> decnum += str[i].toString().toInt() * base.toDouble().pow(str.length - i - 1).toInt()
        }
    }
    return decnum
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var vn = n
    var str = ""
    while (vn >= 1000) {
        str += 'M'
        vn -= 1000
    }
    var ch = 0
    while (vn >= 100) {
        ch++
        vn -= 100
    }
    when (ch) {
        in 1..3 -> str += "C".repeat(ch)
        4 -> str += "CD"
        5 -> str += "D"
        in 6..8 -> str += "D" + "C".repeat(ch - 5)
        9 -> str += "CM"
        else -> str = str
    }
    var cd = 0
    while (vn >= 10) {
        cd++
        vn -= 10
    }
    when (cd) {
        in 1..3 -> str += "X".repeat(cd)
        4 -> str += "XL"
        5 -> str += "L"
        in 6..8 -> str += "L" + "X".repeat(cd - 5)
        9 -> str += "XC"
        else -> str = str
    }
    when (vn) {
        in 1..3 -> str += "I".repeat(vn)
        4 -> str += "IV"
        5 -> str += "V"
        in 6..8 -> str += "V" + "I".repeat(vn - 5)
        9 -> str += "IX"
        else -> str = str
    }
    return str
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()