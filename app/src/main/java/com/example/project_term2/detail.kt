package com.example.project_term2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class detail : Fragment() {

    var title:String ?= null
    var image:String ?= null
    var description:String ?= null
    var benefit:String ?= null
    var science:String ?= null


    fun newInstance(title:String,image:String,description:String,benefit:String,science:String): detail {
        val fragment = detail()
        val bundle = Bundle()
        bundle.putString("title",title);
        bundle.putString("image",image);
        bundle.putString("description",description);
        bundle.putString("benefit",benefit);
        bundle.putString("science",science);

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle != null){
            this.image = bundle.getString("image").toString()
            this.title = bundle.getString("title").toString()
            this.description = bundle.getString("description").toString()
            this.benefit = bundle.getString("benefit").toString()
            this.science = bundle.getString("science").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_detail, container, false)

        val imageView : ImageView = view.findViewById(R.id.imageView)
        val titleView : TextView = view.findViewById(R.id.titleView)
        val descriptionView : TextView = view.findViewById(R.id.descriptionView)
        val benefitView : TextView = view.findViewById(R.id.benefitView)
        val scienceView : TextView = view.findViewById(R.id.scienceView)


        titleView.text = this.title
        descriptionView.text = this.description
        benefitView.text = this.benefit
        scienceView.text = this.science


        Glide.with(activity!!.baseContext).load(image).into(imageView)



        return view
    }

}