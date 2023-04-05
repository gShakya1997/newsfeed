package com.gunjan.newsfeed.core.utils

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController

object NavigationRedirection {
    /**
     * Replace activity container with fragment
     *
     * @param activity          - Require Activity
     * @param fragmentContainer - Fragment Container View
     * @param fragment          - Action id
     */
    fun replaceFragmentContainerWithFragment(
        activity: Activity?,
        fragmentContainer: Int,
        fragment: Int
    ) = findNavController(activity!!, fragmentContainer).navigate(fragment)

    /**
     * Enables to redirect fragment with or without args
     * Accepts NavDirections as well as action id
     *
     * @param v      - View
     * @param action - Action with action id as well as safe args
     */
    fun navigateToFragment(v: View, action: Int) {
        findNavController(v).navigate(action)
//        if (Int::class.java == action.javaClass) {
//            findNavController(v!!).navigate((action as Int))
//        } else findNavController(v!!).navigate((action as NavDirections))
    }

    /**
     * Navigation with bundle
     *
     * @param v      - View
     * @param action - Action
     * @param bundle - Bundle
     */
    fun navigateWithBundle(v: View?, action: Int, bundle: Bundle?) =
        findNavController(v!!).navigate(action, bundle)

    /**
     * Navigation with NavController
     *
     * @param navController - NavController
     * @param action        - Action id
     */
    fun navigateWithNavController(navController: NavController, action: Int) =
        navController.navigate(action)

    /**
     * Same as back press
     *
     * @param v - View
     */
    fun navigateBack(v: View?) = findNavController(v!!).navigateUp()

    /**
     * Setup for NavController
     *
     * @param activity          - Activity
     * @param fragmentContainer - Fragment Container for the activity
     * @return NavController
     */
    fun setupNavController(activity: Activity?, fragmentContainer: Int): NavController =
        findNavController(activity!!, fragmentContainer)
}