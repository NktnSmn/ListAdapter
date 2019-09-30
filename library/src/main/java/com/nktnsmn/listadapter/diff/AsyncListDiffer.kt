package com.nktnsmn.listadapter.diff

import android.support.annotation.UiThread
import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.util.UIThreadExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class AsyncListDiffer<T>(
    private val diffItemCallback: DiffUtil.ItemCallback<T>,
    private val onDiffCompletedCallback: OnDiffCompletedCallback<T>,
    private val calculateExecutorService: ExecutorService = Executors.newFixedThreadPool(2),
    private val applyExecutor: Executor = UIThreadExecutor()
) {

    interface OnDiffCompletedCallback<T> {

        @UiThread
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
        runningDiffs.add(calculateExecutorService.submit {
            val result = DiffUtil.calculateDiff(
                DiffCallback(
                    finalOldList,
                    finalNewList,
                    diffItemCallback
                )
            )
            applyExecutor.execute {
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