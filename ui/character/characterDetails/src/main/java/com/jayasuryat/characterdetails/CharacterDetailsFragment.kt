package com.jayasuryat.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.anim.AnimHelper
import com.jayasuryat.base.anim.impl.AlphaAnim
import com.jayasuryat.base.anim.impl.TranslateAnim
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.hide
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.base.toggleVisibility
import com.jayasuryat.characterdetails.UiUtils.loadImage
import com.jayasuryat.characterdetails.databinding.FragmentCharacterDetailsBinding
import com.jayasuryat.data.models.domain.Character
import com.jayasuryat.data.models.domain.Character.Gender.*
import com.jayasuryat.data.models.domain.Character.Species.*
import com.jayasuryat.data.models.domain.Character.Status.Alive
import com.jayasuryat.data.models.domain.Character.Status.Dead
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.atomic.AtomicBoolean


@AndroidEntryPoint
class CharacterDetailsFragment : BaseAbsFragment<CharacterDetailsViewModel,
        FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    private val hasLanded: AtomicBoolean = AtomicBoolean(false)

    private val episodesAdapter: EpisodeListAdapter by lazy { EpisodeListAdapter(::onEpisodeClicked) }

    override val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (hasLanded.get()) postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentCharacterDetailsBinding.() -> Unit = {

        binding.root.postDelayed(::animateViews, 300)

        rvEpisodes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodesAdapter
        }

        cvBack.shrinkOnClick(::navigateBack)

        cvLocation.shrinkOnClick { }

        cvEpisodes.setOnClickListener {
            cvEpisodes.isClickable = false
            rvEpisodes.show()
            ivExpand.hide()
        }
    }

    override fun setupObservers(): CharacterDetailsViewModel.() -> Unit = {

        obsCharacter.observe(viewLifecycleOwner, ::loadUi)

        obsEpisodes.observe(viewLifecycleOwner) { episodes ->
            episodesAdapter.submitList(episodes)
            binding.cvEpisodes.toggleVisibility(!episodes.isNullOrEmpty())
            (view?.parent as? ViewGroup)
                ?.doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    private fun loadUi(character: Character?) {

        if (character == null) return

        binding.apply {

            ivCharacter.loadImage(character.image)
            tvName.text = character.name

            @IdRes val speciesImageId = when (character.speciesType) {
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
                Character.Status.Unknown -> R.color.grey
            }

            ivSpecies.setImageResource(speciesImageId)
            ivGender.setImageResource(genderImageId)
            cvStatus.setCardBackgroundColor(ContextCompat.getColor(root.context, statusColorId))

            tvSpecies.text = character.speciesName
            tvGender.text = character.gender.name
            tvStatus.text = character.status.name
            tvLocationValue.text = character.location.name
        }
    }

    private fun onEpisodeClicked(episode: EpisodeData, name: View) {

        val extras = FragmentNavigatorExtras(name to "episodeName")

        val event = OpenEpisode(
            episodeId = episode.episodeId,
            extras = extras,
        )

        EventBus.getDefault().post(event)
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun animateViews() {

        val view = nullableBinding?.clExtraInfo ?: return

        AnimHelper.create {
            addAnim {
                TranslateAnim.builder()
                    .fromVerticalDelta(200f)
                    .toCurrentPosition()
                    .build(view)
            }
            addAnim {
                AlphaAnim.builder()
                    .intermediateSteps(0f)
                    .build(view)
            }
        }.start()

        view.show()
    }
}