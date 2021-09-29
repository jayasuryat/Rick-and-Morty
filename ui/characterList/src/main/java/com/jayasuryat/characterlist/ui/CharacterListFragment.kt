package com.jayasuryat.characterlist.ui

import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.jayasuryat.base.arch.BaseAbsFragment
import com.jayasuryat.base.show
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.base.toggleVisibility
import com.jayasuryat.characterlist.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.absoluteValue
import kotlin.math.hypot


@AndroidEntryPoint
class CharacterListFragment : BaseAbsFragment<CharacterListViewModel,
        FragmentCharacterListBinding>(FragmentCharacterListBinding::inflate) {

    private val characterListAdapter: CharactersListAdapter by lazy { CharactersListAdapter() }

    override val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    override fun setupViews(): FragmentCharacterListBinding.() -> Unit = {

        binding.clRoot.post(::revealRoot)

        ivBack.shrinkOnClick { findNavController().popBackStack() }

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

        val animView: View = binding.clRoot

        val startX = arguments?.get("x")?.toString()?.toIntOrNull() ?: 0
        val startY = arguments?.get("y")?.toString()?.toIntOrNull() ?: 0

        val endX = animView.width
        val endY = animView.height

        val length = (endX - startX).absoluteValue.toDouble()
        val width = (endY - startY).absoluteValue.toDouble()

        val finalRadius = hypot(length, width).toFloat()

        val anim = ViewAnimationUtils
            .createCircularReveal(animView, startX, startY, 0f, finalRadius)

        anim.duration = 300
        anim.interpolator = LinearInterpolator()

        anim.start()
    }
}