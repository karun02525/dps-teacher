package org.dpsteacher.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.popwoot.carouselbanner.Banner
import kotlinx.android.synthetic.main.teacher_fragment_home.*
import org.dpsteacher.R
import org.dpsteacher.model.MenuModel
import org.dpsteacher.utils.ImageFactory
import org.dpsteacher.utils.TeacherSharedPref
import org.dpsteacher.utils.startNewActivity

class HomeFragment : Fragment() {
    var list: ArrayList<MenuModel> = arrayListOf()
    private val sp by lazy { TeacherSharedPref.instance }

    companion object {
        var checkedPosition = 0

        @JvmStatic
        fun instance() = HomeFragment()
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.teacher_fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        banner()
        marqueeText.text = createMarqueeText();
        marqueeText.requestFocus()

        initRecy()
    }

    @SuppressLint("SetTextI18n")
    private fun initRecy() {
    }


    private fun createMarqueeText(): String {
        val text = StringBuilder()
        text.append("Dear parents Greetings of the day Hope you all are doing well. Stay healthy & Safe")
        text.append("This is to inform you that school has done some changes in the *FEE payments*…")
        return text.toString()
    }

    fun banner() {

        val list = ArrayList<String>()
        Banner.init(ImageFactory())

        list.add("https://www.voicesofyouth.org/sites/voy/files/images/2019-03/school.jpg")
        list.add("https://mk0digitallearn7ttjx.kinstacdn.com/wp-content/uploads/2021/02/Bihar-Schools.jpg")
        list.add("https://resize.indiatvnews.com/en/resize/newbucket/715_-/2020/12/school-reopen-1608305639.jpg")
        list.add("https://images.indianexpress.com/2021/01/pic-3-5-1.jpg")
        list.add("https://img.etimg.com/thumb/width-640,height-480,imgsize-226937,resizemode-1,msid-78175438/news/politics-and-nation/centre-red-flags-school-dropout-rate-vacant-seats-in-bihar-girls-schools/school-girls-bccl-new.jpg")


        banner.setOnCarouselItemChangeListener {
            //                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
        }


        banner.setOnCarouselItemClickListener { position, url ->
            //   Toast.makeText(this@MainActivity, url, Toast.LENGTH_LONG).show()
        }

        //banner.initBanner(list)
        banner.initBanner(list) //Full screen banner no 2

    }

}

