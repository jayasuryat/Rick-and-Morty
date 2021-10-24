package com.jayasuryat.locationlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.AlphaAnim
import com.jayasuryat.base.anim.impl.TranslateAnim
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.locationlist.NavigateBack
import com.jayasuryat.locationlist.OpenLocation
import com.jayasuryat.locationlist.databinding.FragmentLocationListBinding
import com.jayasuryat.locationlist.domain.model.Location
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class LocationListFragment : BaseAbsFragment<LocationListViewModel,
        FragmentLocationListBinding>(FragmentLocationListBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val locationListAdapter: LocationListAdapter by lazy {
        LocationListAdapter(::openCharacter)
    }

    override val viewModel: LocationListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentLocationListBinding.() -> Unit = {

        binding.clRoot.post(::handleAnimation)

        ivBack.shrinkOnClick(::navigateBack)

        rvLocationsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = SlideInUpAnimator(DecelerateInterpolator())
            adapter = locationListAdapter
        }
    }

    override fun setupObservers(): LocationListViewModel.() -> Unit = {

        obsLocationsList.observe(viewLifecycleOwner) { characters ->
            uiScope.launch { locationListAdapter.submitData(characters) }
            (view?.parent as? ViewGroup)?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun handleAnimation() {

        fun defaultAnimation() {

            AnimHelper.create {
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(binding.ivBack, binding.rvLocationsList)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromHorizontalDelta(-100f)
                        .toCurrentPosition()
                        .build(binding.ivBack)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(200f)
                        .toCurrentPosition()
                        .build(binding.rvLocationsList)
                }
            }.start()
        }

        fun backAnimation() {

            AnimHelper.create {
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(binding.rvLocationsList)
                }
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(-164f)
                        .toCurrentPosition()
                        .build(binding.rvLocationsList)
                }
            }.start()
        }

        if (hasLanded.compareAndSet(false, true)) defaultAnimation()
        else backAnimation()
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun openCharacter(location: Location, name: View, container: View) {

        val extras = FragmentNavigatorExtras(
            name to "locationName",
            container to "locationContainer",
        )

        EventBus.getDefault()
            .post(OpenLocation(locationId = location.id, extras))
    }
}