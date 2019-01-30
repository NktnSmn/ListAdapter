package com.nktnsmn.listadapter.base.diff.async

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.diff.DiffCallback
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class AsyncListDiffer<T>(
    private val diffItemCallback: DiffUtil.ItemCallback<T>,
    private val onDiffCompletedCallback: OnDiffCompletedCallback<T>,
    private val mainThreadExecutorService: ExecutorService = Executors.newFixedThreadPool(2),
    private val uiThreadExecutor: Executor = UIThreadExecutor()
) {

    interface OnDiffCompletedCallback<T> {

        fun onDiffCompleted(newList: List<T>, diffResult: DiffUtil.DiffResult)
    }

    private val runningDiffs: MutableList<Future<*>> = mutableListOf()
    private var maxScheduledGeneration: Int = 0

    fun release() {
        cancelAllDiffs()
    }

    fun calculateDiffAsync(oldList: List<T>, newList: List<T>) {
        val finalOldList: List<T> = ArrayList(oldList)
        val finalNewList: List<T> = ArrayList(newList)
        val runGeneration = ++maxScheduledGeneration
        runningDiffs.add(mainThreadExecutorService.submit {
            val result = DiffUtil.calculateDiff(
                DiffCallback(
                    finalOldList,
                    finalNewList,
                    diffItemCallback
                )
            )
            uiThreadExecutor.execute {
                removeCompletedDiffs()
                if (maxScheduledGeneration == runGeneration) {
                    onDiffCompletedCallback.onDiffCompleted(finalNewList, result)
                }
            }
        })
    }

    private fun cancelAllDiffs() {
        val runningDiffsIterator = runningDiffs.listIterator()
        while (runningDiffsIterator.hasNext()) {
            val runningDiff = runningDiffsIterator.next()
            runningDiff.cancel(true)
            runningDiffsIterator.remove()
        }
    }

    private fun removeCompletedDiffs() {
        val runningDiffsIterator = runningDiffs.listIterator()
        while (runningDiffsIterator.hasNext()) {
            if (runningDiffsIterator.next().isDone) {
                runningDiffsIterator.remove()
            }
        }
    }
}