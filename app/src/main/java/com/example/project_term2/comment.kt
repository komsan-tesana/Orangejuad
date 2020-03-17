package com.example.project_term2


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.FacebookSdk.getApplicationContext
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class comment : Fragment() {
    val CLUBS = arrayOf("1","2","3","4","5")
    var Name : String = ""

    fun newInstance(name: String): comment {

        val comment = comment()
        val bundle = Bundle()
        bundle.putString("Name", name)
        comment.setArguments(bundle)

        return comment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            Name = bundle.getString("Name").toString()

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_comment, container, false)





        val comment_btn = view.findViewById<Button>(R.id.comment_btn)


        val textdiao = view.findViewById<TextView>(R.id.textdiao)
        val score_choice = view.findViewById<TextView>(R.id.score_choice)

        val comment_input = view.findViewById<EditText>(R.id.comment_input)
        val tvName = view.findViewById<TextView>(R.id.tvName)

        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()




        tvName.setText(Name)

        val mUsersRef = mRootRef.child("User")
        val mMessagesRef = mRootRef.child("Comment")

        comment_btn.setOnClickListener {
            //setValue() เป็นการ write หรือ update ข้อมูล ไปยัง path ที่เราอ้างถึงได้ เช่น users/<user-id>/<username>
            //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages

            mUsersRef.child("id-60160155").setValue(Name)

            val key = mMessagesRef.push().key
            val postValues: HashMap<String, Any> = HashMap()

            postValues["username"] = "60160155"
            postValues["text"] = comment_input.text.toString()
            postValues["score"] = score_choice.text.toString()

            val childUpdates: MutableMap<String, Any> = HashMap()

            childUpdates["$key"] = postValues

            mMessagesRef.updateChildren(childUpdates)


        }


        textdiao.setOnClickListener {


            val builder: AlertDialog.Builder =  AlertDialog.Builder(this.context)
            builder.setTitle("โปรดเลือกระดับคะแนนคำวิจารณ์")
            builder.setSingleChoiceItems(CLUBS,0) {
    
        dialog: DialogInterface?, which: Int -> score_choice.text = CLUBS[which]

            }


//
//            val builder: AlertDialog.Builder =  AlertDialog.Builder(this.context)
//            builder.setMessage("รับอะไรเพิ่มไหม?")
//
//
//
//            builder.setNegativeButton("รับ",
//                DialogInterface.OnClickListener{ dialog, id ->
//                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                })
//
//            builder.setPositiveButton("ไม่รับ",
//                DialogInterface.OnClickListener{ dialog, id ->
//                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                })
          builder.show();






        }



        return view
    }









}
