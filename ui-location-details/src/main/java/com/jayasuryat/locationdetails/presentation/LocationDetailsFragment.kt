package com.jayasuryat.locationdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.AlphaAnim
import com.jayasuryat.base.anim.impl.TranslateAnim
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.locationdetails.databinding.FragmentLocationDetailsBinding
import com.jayasuryat.locationdetails.domain.models.Character
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class LocationDetailsFragment : BaseAbsFragment<LocationDetailsViewModel,
        FragmentLocationDetailsBinding>(FragmentLocationDetailsBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(::openCharacter)
    }

    override val viewModel: LocationDetailsViewModel by viewModels()

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
        if (hasLanded.get()) postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentLocationDetailsBinding.() -> Unit = {

        root.post(::animateViews)

        ivBack.shrinkOnClick(::navigateBack)

        rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 3)
            itemAnimator = FadeInUpAnimator(AccelerateInterpolator())
            adapter = characterListAdapter
        }
    }

    override fun setupObservers(): LocationDetailsViewModel.() -> Unit = {

        viewModel.loadLocationDetails(locationId = getLocationId())

        obsLocation.observe(viewLifecycleOwner) { location ->
            if (location == null) return@observe
            binding.apply {
                tvLocationName.text = location.name
                tvType.text = location.type
                tvDimension.text = location.dimension
                characterListAdapter.submitList(location.characters)
                (view?.parent as? ViewGroup)
                    ?.doOnPreDraw { startPostponedEnterTransition() }
            }
        }
    }

    private fun animateViews() {

        fun defaultAnimation() {

            val view = nullableBinding?.cvCharacters ?: return
            val view2 = nullableBinding?.cvInfo ?: return
            val view3 = nullableBinding?.cvName ?: return

            AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(200f)
                        .toCurrentPosition()
                        .build(view, view2, view3)
                }
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(view, view2)
                }
            }.start()
        }

        fun backAnimation() {

            val view = nullableBinding?.cvCharacters ?: return
            val view2 = nullableBinding?.cvInfo ?: return
            val view3 = nullableBinding?.cvName ?: return

            AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(-200f)
                        .toCurrentPosition()
                        .build(view, view2, view3)
                }
                addAnim {
                    AlphaAnim.builder()
                        .intermediateSteps(0f)
                        .build(view, view2, view3)
                }
            }.start()
        }

        if (hasLanded.compareAndSet(false, true)) defaultAnimation()
        else backAnimation()
    }

    private fun openCharacter(character: Character, image: View, name: View, container: View) {

        val extras = FragmentNavigatorExtras(
            image to "characterAvatar",
            name to "characterName",
            container to "characterContainer",
        )

        EventBus.getDefault()
            .post(OpenCharacter(characterId = character.id, extras))
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun getLocationId(): Long {
        return arguments?.getLong("locationId")
            ?: throw IllegalArgumentException("Location id nav argument missing")
    }
}