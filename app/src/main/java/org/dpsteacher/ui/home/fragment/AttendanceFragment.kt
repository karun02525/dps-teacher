package org.dpsteacher.ui.home.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.alert_dialog_ask.view.*
import kotlinx.android.synthetic.main.alert_dialog_ok.view.*
import kotlinx.android.synthetic.main.fragment_attentance.*
import kotlinx.android.synthetic.main.teacher_adapter_atten.view.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.dpsteacher.R
import org.dpsteacher.model.AssignStudentsData
import org.dpsteacher.model.StudentModel
import org.dpsteacher.mvvm.ProfileViewModel
import org.dpsteacher.utils.*
import org.json.JSONArray
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel

class AttendanceFragment : Fragment() {

    private val sp by lazy { TeacherSharedPref.instance }
    private val viewModel: ProfileViewModel by viewModel()
    private var listAttendance = listOf<StudentModel>()
    var class_ID=""
    val teacher_ID="616716b42ba31655d71e2e93"

    companion object {
        @JvmStatic
        fun instance() = AttendanceFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attentance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideShowProgress(true)
        setupViewModel()
        viewModel.getProfile("616716b42ba31655d71e2e93")

        val date = getCurrentDateTime()
        val dateInString = date.toString("EEEE  MMMM dd, yyyy")
        tv_toolbar.text = " $dateInString"

        btnSubmit.setOnClickListener {
            alertDialog()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun alertDialog() {
        val v = this.layoutInflater.inflate(R.layout.alert_dialog_ask, null)
        val d = Dialog(requireActivity(), R.style.MaterialDialogSheet)
        d.setContentView(v)
        d.setCancelable(false)
        d.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        d.window!!.setGravity(Gravity.CENTER)
        d.show()

        v.tvTitle.text = "Attendance"
        v.tvMess.text = "Are you sure you want to submit attendance?"
        v.btnCancel.setOnClickListener { d.dismiss() }
        v.btnYes.setOnClickListener {
            d.dismiss()
            operationSubmit()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun alertOkDialog(mess: String) {
        val v = this.layoutInflater.inflate(R.layout.alert_dialog_ok, null)
        val d = Dialog(requireActivity(), R.style.MaterialDialogSheet)
        d.setContentView(v)
        d.setCancelable(false)
        d.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        d.window!!.setGravity(Gravity.CENTER)
        d.show()


        v.tvLevel.text = mess
        v.btnOk.setOnClickListener {
            d.dismiss()
        }

    }


    private fun setupViewModel() {

        viewModel.dataProfile.observe(this, Observer {
            hideShowProgress(false)
            listAttendance = it.student
            class_ID=it.teacher.classes.id
            if (listAttendance.isEmpty()) {
                empty_data.visibility = View.VISIBLE
            } else {
                val mAdapter = StudentsListAdapter(listAttendance)
                rv_stu_list.adapter = mAdapter
                empty_data.visibility = View.GONE
            }
        })

        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            context?.toast(it)
        })


        viewModel.success.observe(this, Observer {
            alertOkDialog(it)
            hideShowProgress(false)
        })
        viewModel.errorMsgSubmit.observe(this, Observer {
            alertOkDialog(it)
            hideShowProgress(false)
        })
        viewModel.errorMsg.observe(this, Observer {
            empty_data.visibility = View.VISIBLE
            activity?.toast(it)
            hideShowProgress(false)
        })
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }

    private fun operationSubmit() {


        val jsonArray = JSONArray()
        for (i in listAttendance) {
            // log("Data " + i.fname + " " + i.classes?.rollNo + " " + i.flag)
            val obj = JSONObject()
            obj.put("student_id", i.id)
            obj.put("att_type", i.flag)
            jsonArray.put(obj);
        }

        val studentsObj = JSONObject()
        studentsObj.put("teacher_id", teacher_ID)
        studentsObj.put("class_id", class_ID)
        studentsObj.put("attlist", jsonArray)
        log("Student $studentsObj")

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = studentsObj.toString().toRequestBody(mediaType)
        hideShowProgress(true)
        viewModel.submitAttendance(requestBody)
    }

}

//---------------------------------------------Adapter-------------------------------------------------
class StudentsListAdapter(var list: List<StudentModel> = listOf()) :
    RecyclerView.Adapter<StudentsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.teacher_adapter_atten, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        private var status = "A"
        fun bindItems(model: StudentModel) {
            itemView.run {
                tv_roll.text = model.rollno.toString()
                tv_name.text = "${model.fname} ${model.lname}"

                model.flag = "Present"
                radioGroup.setOnCheckedChangeListener { group, checkedId ->
                    val radio: RadioButton = group.findViewById(checkedId)
                    status = radio.text.toString()
                    if (status == "A") {
                        model.flag = "Present"
                    } else {
                        model.flag = "Apsent"
                    }
                }

            }
        }
    }

}


