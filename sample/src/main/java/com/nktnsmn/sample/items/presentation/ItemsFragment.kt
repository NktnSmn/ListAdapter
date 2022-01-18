package com.nktnsmn.sample.items.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nktnsmn.listadapter.cellular.CellularListAdapter
import com.nktnsmn.listadapter.cellular.itemviewcell.BindingItemViewCell
import com.nktnsmn.listadapter.diff.item.ItemIdModel
import com.nktnsmn.listadapter.listitems.ListItems
import com.nktnsmn.listadapter.observer.ListItemsObserverAdapter
import com.nktnsmn.listadapter.viewholder.ViewHolder
import com.nktnsmn.sample.BR
import com.nktnsmn.sample.R
import com.nktnsmn.sample.base.BasePresenterFragment
import com.nktnsmn.sample.items.presentation.item.ColorItemVM
import com.nktnsmn.sample.items.presentation.item.ImageItemClickListener
import com.nktnsmn.sample.items.presentation.item.ImageItemVM
import com.nktnsmn.sample.items.presentation.item.attachment.AttachmentItemViewCell
import com.nktnsmn.sample.items.presentation.item.text.TextItemViewCell

class ItemsFragment : BasePresenterFragment<ItemsView, ItemsPresenter>(), ItemsView {

    override fun createPresenter(): ItemsPresenter = ItemsPresenterImpl(requireContext().applicationContext)

    override fun getPresenterView(): ItemsView = this

    private lateinit var refreshItemsView: SwipeRefreshLayout
    private lateinit var itemsListView: RecyclerView
    private lateinit var itemsAdapter: CellularListAdapter<ItemIdModel, ViewHolder<ItemIdModel>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsAdapter = CellularListAdapter(
            TextItemViewCell(),
            AttachmentItemViewCell(),
            BindingItemViewCell.forModel<ImageItemVM>(
                layoutResId = R.layout.image_list_item,
                modelBindingVariableId = BR.viewModel,
                globalBindingVariables = mapOf(BR.clickListener to presenter as ImageItemClickListener)
            ),
            BindingItemViewCell.forModel<ColorItemVM>(R.layout.color_list_item, BR.viewModel)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.items_fragment, container, false)
        refreshItemsView = rootView.findViewById<SwipeRefreshLayout>(R.id.refresh_items_view).apply {
            setOnRefreshListener { presenter.refreshItems() }
        }
        itemsListView = rootView.findViewById<RecyclerView>(R.id.items_list_view).apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun supportsPredictiveItemAnimations(): Boolean = false
            }
            adapter = itemsAdapter
        }
        return rootView
    }

    override fun setupItems(items: ListItems<ItemIdModel>) {
        items.observer = ListItemsObserverAdapter(itemsAdapter)
        itemsAdapter.items = items
    }

    override fun hideRefresher() {
        refreshItemsView.isRefreshing = false
        refreshAgain()
    }

    private fun refreshAgain() {
        refreshItemsView.postDelayed(
            {
                refreshItemsView.isRefreshing = true
                presenter.refreshItems()
            },
            300
        )
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}