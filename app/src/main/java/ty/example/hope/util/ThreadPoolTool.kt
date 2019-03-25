package ty.example.hope.util

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @description: ThreadPoolTool: kotlin中的单例模式
 **
 * @author: hope
 **
 * @create: 2019-03-19 14:29
 */
object ThreadPoolTool{

    private val MAX_THREAD_NUM: Int = 3
    private var executorService: ExecutorService

    init {
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM)
    }

    fun runTask(runnable: Runnable){
        executorService.execute(runnable)
    }

}