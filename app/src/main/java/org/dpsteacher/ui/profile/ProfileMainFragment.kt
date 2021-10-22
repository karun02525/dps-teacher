package org.dpsteacher.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.teacher_fragment_profile.*
import org.dpsteacher.R
import org.dpsteacher.model.DataProfileModel
import org.dpsteacher.model.StudentModel
import org.dpsteacher.model.TeacherModel
import org.dpsteacher.mvvm.ProfileViewModel
import org.dpsteacher.ui.profile.fragments.OwnProfileFragment
import org.dpsteacher.ui.profile.fragments.StudentsListInfoFragment
import org.dpsteacher.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileMainFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModel()

    companion object {
        @JvmStatic
        fun instance() = ProfileMainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teacher_fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideShowProgress(true)
        viewModel.getProfile("6171bdbbe6efc3a63bede3e3")

    }


    private fun setupViewModel() {
        viewModel.dataProfile.observe(this, Observer {
            hideShowProgress(false)
            dataParse(it)
        })

        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            context?.toast(it)
        })
    }

    private fun dataParse(it: DataProfileModel) {
        initViewPage(it.teacher,it.student)
    }

    @SuppressLint("SetTextI18n")
    private fun initViewPage(teacher: TeacherModel, student: List<StudentModel>) {
        tabViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> OwnProfileFragment.instance(teacher)
                    1 -> StudentsListInfoFragment.instance(student)
                    else -> OwnProfileFragment.instance(teacher)
                }
            }
        }
        tabViewPager.currentItem = 0
        TabLayoutMediator(tabLayout, tabViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Own Profile"
                1 -> "Students List"
                else -> {
                    null
                }
            }
        }.attach()

    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}
