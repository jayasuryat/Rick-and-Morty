package com.jayasuryat.base.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


public class ViewModelFactory<VM : BaseViewModel>(
    private val creator: () -> VM,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = creator() as T
}