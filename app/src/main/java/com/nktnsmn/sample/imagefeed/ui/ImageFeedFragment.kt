package com.nktnsmn.sample.imagefeed.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.base.listitems.impl.base.BaseUpdatableListItems
import com.nktnsmn.listadapter.base.updatecallback.AdapterListItemsUpdateCallback
import com.nktnsmn.listadapter.base.viewholder.BindableViewHolder
import com.nktnsmn.listadapter.cellular.CellularListAdapter
import com.nktnsmn.listadapter.cellular.DefaultCellularListAdapter
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.sample.BR
import com.nktnsmn.sample.R
import com.nktnsmn.sample.base.BasePresenterFragment
import com.nktnsmn.sample.imagefeed.ui.listitem.ImageListItemVM

class ImageFeedFragment : BasePresenterFragment<ImageFeedView, ImageFeedPresenter>(), ImageFeedView {

    override fun createPresenter(): ImageFeedPresenter = ImageFeedPresenterImpl()

    override fun getPresenterView(): ImageFeedView = this

    private lateinit var refreshImageFeedView: SwipeRefreshLayout
    private lateinit var imageFeedListView: RecyclerView
    private lateinit var imageFeedAdapter: CellularListAdapter<BindableViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageFeedAdapter = DefaultCellularListAdapter(
            ItemViewCell(
                ImageListItemVM::class.java,
                R.layout.image_list_item,
                BR.ImageListItemVM,
                R.layout.image_list_item,
                BR.ImageListItemClickListener to presenter
            )
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_image_feed, container, false)
        refreshImageFeedView = rootView.findViewById<SwipeRefreshLayout>(R.id.refresh_image_feed_view).apply {
            setOnRefreshListener { presenter.refreshImageFeed() }
        }
        imageFeedListView = rootView.findViewById<RecyclerView>(R.id.image_feed_list_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imageFeedAdapter
        }
        return rootView
    }

    override fun setupImageFeedItems(imageFeedItems: BaseUpdatableListItems<IdentifiableItem>) {
        imageFeedItems.bindUpdateCallback(AdapterListItemsUpdateCallback(imageFeedAdapter))
        imageFeedAdapter.items = imageFeedItems
    }

    override fun hideRefresher() {
        refreshImageFeedView.isRefreshing = false
    }

    override fun showToast(message: String) {
        Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}