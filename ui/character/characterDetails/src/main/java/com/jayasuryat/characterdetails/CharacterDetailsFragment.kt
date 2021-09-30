package com.jayasuryat.characterdetails

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.UiUtils.loadImage
import com.jayasuryat.characterdetails.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


@AndroidEntryPoint
class CharacterDetailsFragment : BaseAbsFragment<CharacterDetailsViewModel,
        FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    override val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun setupViews(): FragmentCharacterDetailsBinding.() -> Unit = {

        animateViews()

        ivBack.shrinkOnClick(::navigateBack)
    }

    override fun setupObservers(): CharacterDetailsViewModel.() -> Unit = {

        obsCharacter.observe(viewLifecycleOwner) { character ->
            if (character == null) return@observe
            binding.ivCharacter.loadImage(character.image)
            binding.tvName.text = character.name
        }
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun animateViews() {

        val animDuration: Long = 300
        val animInterpolator = DecelerateInterpolator()

        ObjectAnimator.ofFloat(binding.cvInfo, "alpha", 0f, 0.0f, 1f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.start()

        ObjectAnimator.ofFloat(binding.ivCharacter, "alpha", 0f, 0.0f, 1f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.start()

        TranslateAnimation(0f, 0f, 400f, 0f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.run { binding.cvInfo.startAnimation(this) }
    }
}