package com.troshchii.reddit.ui.newsdetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.setImageUrl
import com.troshchii.reddit.core.extensions.themeColor
import com.troshchii.reddit.databinding.NewsdetailsFragmentBinding

class NewsDetailsFragments : Fragment() {

    private val safeArgs: NewsDetailsFragmentsArgs by navArgs()

    private lateinit var binding: NewsdetailsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
//            drawingViewId = R.id.fragment_container
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NewsdetailsFragmentBinding.inflate(inflater, container, false)
        binding.image.transitionName = safeArgs.post.imageUrl
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image.setImageUrl(safeArgs.post.imageUrl)
    }
}