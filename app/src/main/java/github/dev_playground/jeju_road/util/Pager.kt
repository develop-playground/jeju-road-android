package github.dev_playground.jeju_road.util

class Pager {

    private var pageIndex = START_PAGE_INDEX

    suspend fun load(predicate: suspend (Int) -> Boolean?) {
        val isSuccess = predicate(pageIndex)

        if (isSuccess == true) {
            increment()
        }
    }

    private fun increment() {
        pageIndex++
    }

    fun reset() {
        pageIndex = START_PAGE_INDEX
    }

    companion object {
        private const val START_PAGE_INDEX = 0
    }

}