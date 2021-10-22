package org.dpsteacher.ui.profile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_profile_details.view.*
import kotlinx.android.synthetic.main.adapter_students_list.view.*
import kotlinx.android.synthetic.main.teacher_fragment_own_profile.*
import org.dpsteacher.R
import org.dpsteacher.model.TeacherModel
import org.dpsteacher.network.Const
import org.dpsteacher.utils.TeacherSharedPref


class OwnProfileFragment : Fragment() {
    private val sp by lazy { TeacherSharedPref.instance }
    private lateinit var teacher: TeacherModel
    companion object {
        @JvmStatic
        fun instance(teacher: TeacherModel) =
            OwnProfileFragment().apply {
                this.teacher=teacher
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teacher_fragment_own_profile, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list: ArrayList<OwnProfileModel> = arrayListOf()


        Picasso.get()
            .load("${Const.ImageBaseUrl}/${teacher.teacherAvatar}")
            .into(iv_teacher_pic, object : Callback {
                override fun onSuccess() {

                }
                override fun onError(e: Exception?) {
                    iv_teacher_pic.setImageResource(R.drawable.profile_pic)
                }
            })


        tv_teacher_name.text = teacher.fname +" "+teacher.lname
        tv_tsname.text = teacher.classes.name

        list.run {
            add(OwnProfileModel("Assign Class", teacher.classes.name?:""))
            add(OwnProfileModel("Reg No","---"?:""))
            add(OwnProfileModel("Gender",teacher.gender?:""))
            add(OwnProfileModel("Date of birth",teacher.dob?:""))
            add(OwnProfileModel("Mobile number", teacher.mobile?:""))
            add(OwnProfileModel("Email ID ",teacher.email?:""))
            add(OwnProfileModel("Qualification", teacher.qualification?:""))
            add(OwnProfileModel("Parent's name", teacher.parentName?:""))
            add(OwnProfileModel("Address", teacher.address?:""))
            add(OwnProfileModel("Post Office", teacher.postOffice?:""))
            add(OwnProfileModel("Dist", teacher.distc?:""))
            add(OwnProfileModel("State", teacher.state?:""))
            add(OwnProfileModel("Pin code", teacher.pincode?:""))

        }
        val mAdapter = OwnProfileAdapter(list)
        rv_TeacherOwnInfo.adapter = mAdapter
    }
}

data class OwnProfileModel(val key: String, val value: String)

class OwnProfileAdapter(var list: List<OwnProfileModel> = listOf()) :
    RecyclerView.Adapter<OwnProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.adapter_profile_details, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: OwnProfileModel) {
            itemView.run {
                tv_title.text = model.key
                tv_name.text = model.value
            }
        }
    }


}
