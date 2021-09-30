package com.jayasuryat.characterlist.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.CircleRevealHelper
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterlist.NavigateBack
import com.jayasuryat.characterlist.OpenCharacter
import com.jayasuryat.characterlist.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


@AndroidEntryPoint
class CharacterListFragment : BaseAbsFragment<CharacterListViewModel,
        FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val characterListAdapter: CharactersListAdapter by lazy {
        CharactersListAdapter(::openCharacter)
    }

    override val viewModel: CharacterListViewModel by viewModels()

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
        postponeEnterTransition()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews(): FragmentCharacterListBinding.() -> Unit = {

        binding.clRoot.post(::revealRoot)
        animateViews()

        ivBack.shrinkOnClick(::navigateBack)

        rvCharactersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
        }
    }

    override fun setupObservers(): CharacterListViewModel.() -> Unit = {

        obsIsDataLoading.observe(viewLifecycleOwner) { isLoading ->
            //binding.pbLoading.toggleVisibility(isLoading == true)
        }

        obsCharactersList.observe(viewLifecycleOwner) { characters ->
            characterListAdapter.submitList(characters)
            binding.rvCharactersList.show()
            startPostponedEnterTransition()
        }
    }

    private fun revealRoot() {

        val startX = arguments?.get("x")?.toString()?.toIntOrNull() ?: 0
        val startY = arguments?.get("y")?.toString()?.toIntOrNull() ?: 0

        CircleRevealHelper.Builder(binding.clRoot)
            .setStartPoint(startX.toDouble(), startY.toDouble())
            .setFarthestPointFromStartAsEnd()
            .build()
            .animation
            .start()
    }

    private fun animateViews() {

        val animDuration: Long = 300
        val animInterpolator = DecelerateInterpolator()

        ObjectAnimator.ofFloat(binding.ivBack, "alpha", 0f, 0.0f, 1f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.start()

        ObjectAnimator.ofFloat(binding.rvCharactersList, "alpha", 0f, 0.0f, 1f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.start()

        TranslateAnimation(-100f, 0f, 0f, 0f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.run { binding.ivBack.startAnimation(this) }

        TranslateAnimation(0f, 0f, 200f, 0f)
            .apply {
                duration = animDuration
                interpolator = animInterpolator
            }.run { binding.rvCharactersList.startAnimation(this) }
    }

    private fun navigateBack() = EventBus.getDefault().post(NavigateBack)

    private fun openCharacter(character: CharacterDef, sharedView: View) {

        val extras = FragmentNavigatorExtras(sharedView to "characterAvatar")

        viewModel.onItemClicked(character)

        EventBus.getDefault()
            .post(OpenCharacter(characterId = character.id, extras))
    }
}