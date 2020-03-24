package kz.app.anatoliy.common.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kz.app.anatoliy.common.R

abstract class BaseActivity : AppCompatActivity() {

    fun replaceFragment(
        fragment: Fragment,
        @IdRes layoutId: Int = R.id.container,
        addToBackStack: Boolean = true
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(layoutId, fragment)
            .apply { if (addToBackStack) addToBackStack(fragment::class.java.simpleName) }
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}