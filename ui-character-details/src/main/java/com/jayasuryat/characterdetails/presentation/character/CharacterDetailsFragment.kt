package com.jayasuryat.characterdetails.presentation.character

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.TranslateAnim
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.NavigateBack
import com.jayasuryat.characterdetails.OpenCharacterEpisodes
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.databinding.FragmentCharacterDetailsBinding
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Gender.*
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Species.*
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Status.Alive
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Status.Dead
import com.jayasuryat.characterdetails.presentation.UiUtils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class CharacterDetailsFragment : BaseAbsFragment<CharacterDetailsViewModel,
        FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    override val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun setupViews(): FragmentCharacterDetailsBinding.() -> Unit = {

        binding.root.post(::animateViews)

        cvBack.shrinkOnClick(::navigateBack)

        cvCurrentLocation.shrinkOnClick { }
        cvOriginLocation.shrinkOnClick { }
        cvEpisodes.shrinkOnClick(::onEpisodesClicked)
    }

    override fun setupObservers(): CharacterDetailsViewModel.() -> Unit = {

        obsCharacter.observe(viewLifecycleOwner, ::loadUi)
    }

    private fun loadUi(character: CharacterDetails?) {

        if (character == null) return

        binding.apply {

            ivCharacter.loadImage(character.image)
            tvName.text = character.name

            @IdRes val speciesImageId = when (character.species) {
                Alien -> R.drawable.icon_alien
                Human -> R.drawable.icon_user
                Humanoid -> R.drawable.icon_robo
                Other -> R.drawable.icon_paw
            }

            @IdRes val genderImageId = when (character.gender) {
                Female -> R.drawable.icon_gender_female
                Male -> R.drawable.icon_gender_male
                Genderless -> R.drawable.icon_gender_neutral
                Unknown -> R.drawable.icon_gender_neutral // TODO Need to add a diff icon
            }

            @IdRes val statusColorId = when (character.status) {
                Alive -> R.color.green
                Dead -> R.color.red
                CharacterDetails.Status.Unknown -> R.color.grey
            }

            ivSpecies.setImageResource(speciesImageId)
            ivGender.setImageResource(genderImageId)
            cvStatus.setCardBackgroundColor(ContextCompat.getColor(root.context, statusColorId))

            tvSpecies.text = character.species.name
            tvGender.text = character.gender.name
            tvStatus.text = character.status.name

            if (!character.type.isNullOrEmpty()) {
                tvType.show()
                tvType.text = character.type
            }

            tvCurrentLocationValue.text = character.location?.name
            tvCurrentLocationDimension.text = character.location?.dimension

            tvOriginLocationValue.text = character.origin?.name
            tvOriginLocationDimension.text = character.location?.dimension
        }
    }

    private fun animateViews() {

        fun enterAnim() {

            val view1 = nullableBinding?.cvCurrentLocation ?: return
            val view2 = nullableBinding?.cvOriginLocation ?: return
            val view3 = nullableBinding?.cvEpisodes ?: return

            val anim = AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(200f)
                        .toCurrentPosition()
                        .setInterpolator(OvershootInterpolator())
                        .build(view1, view2, view3)
                }
            }

            view1.postDelayed({
                view1.show(); view2.show(); view3.show();
                anim.start()
            }, 300)
        }

        fun backAnim() {

            val view1 = nullableBinding?.cvCurrentLocation ?: return
            val view2 = nullableBinding?.cvOriginLocation ?: return
            val view3 = nullableBinding?.cvEpisodes ?: return

            val anim = AnimHelper.create {
                addAnim {
                    TranslateAnim.builder()
                        .fromVerticalDelta(-96f)
                        .toCurrentPosition()
                        .setDuration(128)
                        .build(view1, view2, view3)
                }
            }

            view1.postDelayed({
                view1.show(); view2.show(); view3.show();
                anim.start()
            }, 60)
        }

        if (hasLanded.compareAndSet(false, true)) enterAnim()
        else backAnim()
    }

    private fun onEpisodesClicked() {

        val characterId = (arguments?.get("id") as Long?) ?: return

        val event = OpenCharacterEpisodes(
            characterId = characterId,
        )

        EventBus.getDefault().post(event)
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)
}