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
import kotlinx.android.synthetic.main.adapter_students_list.view.*
import kotlinx.android.synthetic.main.teacher_fragment_student_list_info.*
import org.dpsteacher.R
import org.dpsteacher.model.StudentModel
import org.dpsteacher.network.Const
import org.dpsteacher.utils.TeacherSharedPref


class StudentsListInfoFragment : Fragment() {
    private val sp by lazy { TeacherSharedPref.instance }
    private lateinit var student: List<StudentModel>
    companion object {
        @JvmStatic
        fun instance(student: List<StudentModel>) =
            StudentsListInfoFragment().apply {
                this.student=student
            }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teacher_fragment_student_list_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doParse()
    }

   fun doParse(){
       if (student.isEmpty()) {
           empty_data.visibility = View.VISIBLE
       } else {
           val mAdapter = StudentsListAdapter(student)
           rv_stu_list.adapter = mAdapter
           empty_data.visibility = View.GONE
       }
   }
}

class StudentsListAdapter(var list: List<StudentModel> = listOf()) :
    RecyclerView.Adapter<StudentsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.adapter_students_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItems(model: StudentModel) {
            itemView.run {


                Picasso.get()
                    .load("${Const.ImageBaseUrl}/${model.studentAvatar}")
                    .into(tvStudentPic, object : Callback {
                        override fun onSuccess() {

                        }
                        override fun onError(e: Exception?) {
                            tvStudentPic.setImageResource(R.drawable.profile_pic)
                        }
                    })


                tv_stu_name.text = "${model.fname} ${model.lname}"
                tv_mess.text = "${model.classes.name}-${model.rollno}"
            }
        }
    }

}
