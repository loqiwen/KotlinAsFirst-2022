@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1
// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/*
* Ячейка матрицы: row = ряд, column = колонка
*/
data class Cell(val row: Int, val column: Int)

/*
* Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
*/
interface Matrix<E> {
    /* Высота */
    val height: Int

    /* Ширина */
    val width: Int

    /*
    * Доступ к ячейке.
    * Методы могут бросить исключение, если ячейка не существует или пуста
    */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /*
    * Запись в ячейку.
    * Методы могут бросить исключение, если ячейка не существует
    */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/*
* Простая (2 балла)
*
* Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
* height = высота, width = ширина, e = чем заполнить элементы.
* Бросить исключение IllegalArgumentException, если height или width <= 0.
*/
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
    if (height <= 0 || width <= 0) throw IllegalArgumentException() else MatrixImpl(height, width, e)

/*
* Средняя сложность (считается двумя задачами в 3 балла каждая)
*
* Реализация интерфейса "матрица"
*/
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    val matrixCells = mutableMapOf<Cell, E>()

    init {
        for (i in 0 until height) {
            for (j in 0 until width) {
                matrixCells[Cell(i, j)] = e
            }
        }
    }

    override fun get(row: Int, column: Int): E {
        if (row in 0 until height && column in 0 until width) return matrixCells[Cell(row, column)]!!
        throw IllegalArgumentException()
    }

    override fun get(cell: Cell): E {
        if (cell.row in 0 until height && cell.column in 0 until width) return matrixCells[cell]!!
        throw IllegalArgumentException()
    }

    override fun set(row: Int, column: Int, value: E) {
        matrixCells[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        matrixCells[cell] = value
    }

    override fun equals(other: Any?) =
        other is MatrixImpl<*> && height == other.height && width == other.width && other.hashCode() == this.hashCode() && other.matrixCells == this.matrixCells

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0..height - 1) {
            sb.append("[")
            for (column in 0..width - 1) {
                val cellToFind = Cell(row, column)
                if (matrixCells.keys.contains(cellToFind)) sb.append(matrixCells.get(cellToFind).toString())
            }
            sb.append("]")
        }
        sb.append("]")
        return "$sb"
    }

    override fun hashCode(): Int {
        var result = 5
        result = result * 31 + height
        result = result * 31 + width
        // Something for elements...
        return result
    }
}