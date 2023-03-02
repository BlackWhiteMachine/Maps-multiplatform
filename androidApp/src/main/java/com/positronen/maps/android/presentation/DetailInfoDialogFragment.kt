package com.positronen.maps.android.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.positronen.maps.domain.model.PointType
import com.positronen.maps.domain.model.detail.ChannelEventDetail
import com.positronen.maps.utils.Logger
import com.positronen.maps.android.utils.toPx
import com.positronen.maps.android.R
import com.positronen.maps.android.databinding.BottomSheetLayoutBinding
import com.positronen.maps.presentation.detail.DetailViewModel
import kotlin.math.roundToInt

class DetailInfoDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val INTENT_TYPE_TEXT_PLAIN: String = "text/plain"
        private const val DIVIDE_SIZE: Int = 8
        const val ID_AGR: String = "ID"
        const val POINT_TYPE_AGR: String = "POINT_TYPE"
    }

//    @Inject
    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: BottomSheetLayoutBinding
    private lateinit var viewModel: DetailViewModel

    private lateinit var imageAdapter: DetailInfoImageAdapter

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetLayoutBinding.bind(inflater.inflate(R.layout.bottom_sheet_layout, container))

//        (activity?.application as EventsApplication).component.inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getString(ID_AGR, "")
        val pointType = PointType.values()[requireArguments().getInt(POINT_TYPE_AGR)]

        imageAdapter = DetailInfoImageAdapter()
        binding.imagesRecyclerView.adapter = imageAdapter
        binding.imagesRecyclerView.addItemDecoration(DividerItemDecoration(DIVIDE_SIZE.toPx.roundToInt()))

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        viewModel.onViewInit(id, pointType)
        binding.initListeners()
        viewModel.initSubscribers()
    }

    private fun BottomSheetLayoutBinding.initListeners() {
        shareImageView.setOnClickListener {
            viewModel.onShareAddressClicked()
        }
    }

    private fun DetailViewModel.initSubscribers() {
        lifecycleScope.launchWhenStarted {
            dataFlow.collect { placeDetailModel ->
                with(binding) {
                    val typePointIcon = when (placeDetailModel.pointType) {
                        PointType.PLACE -> R.drawable.ic_baseline_home_work_24
                        PointType.EVENT -> R.drawable.ic_baseline_event_24
                        PointType.ACTIVITY -> R.drawable.ic_baseline_sports_24
                        PointType.CLUSTER -> 0
                    }
                    val compoundDrawablePadding = resources.getDimension(R.dimen.horizontal_padding_medium)
                    titleTextView.setCompoundDrawablesWithIntrinsicBounds(typePointIcon, 0, 0, 0)
                    titleTextView.compoundDrawablePadding = compoundDrawablePadding.roundToInt()
                    titleTextView.text = placeDetailModel.name
                    val spannedDescription = Html.fromHtml(placeDetailModel.description, Html.FROM_HTML_MODE_LEGACY)
                    descriptionTextView.text = spannedDescription
                    descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
                    val infoUrl = placeDetailModel.infoUrl
                    if (infoUrl != null) {
                        infoUrlTextView.makeLink(infoUrl)
                    } else {
                        infoUrlTextView.text = null
                    }
                    addressTextView.text = placeDetailModel.location.address
                    imageAdapter.setItems(placeDetailModel.images)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            showProgressBarFlow.collect { isLoaderShowing ->
                binding.loaderProgressBar.isVisible = isLoaderShowing
            }
        }

        lifecycleScope.launchWhenStarted {
            eventFlow.collect { event ->
                when (event) {
                    is ChannelEventDetail.ShareText -> shareText(event)
                }
            }
        }
    }

    private fun TextView.makeLink(link: String) {
        val infoUrlSpan = SpannableString(link)
        infoUrlSpan.setSpan(
            object : ClickableSpan() {
                override fun onClick(view: View) {
                    try {
                        val myIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        startActivity(myIntent)
                    } catch (error: ActivityNotFoundException) {
                        Logger.exception(error)
                    }
                }
            },
            0,
            infoUrlSpan.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = infoUrlSpan
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun shareText(event: ChannelEventDetail.ShareText) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = INTENT_TYPE_TEXT_PLAIN
        intent.putExtra(Intent.EXTRA_TEXT, event.text)
        startActivity(Intent.createChooser(intent, null))
    }
}