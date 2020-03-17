package com.example.project_term2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authen = authen()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()




//        val ShowData = ShowData()
//        transaction.replace(R.id.show, ShowData,"fragment_ShowData")
//        transaction.addToBackStack("fragment_ShowData")

//        val comment = comment()
//        transaction.replace(R.id.layout,comment,"fragment_comment")
//        transaction.addToBackStack("fragment_comment")
//        transaction.commit()


//        val profile = profile()
//        val fm = supportFragmentManager
//        val transaction : FragmentTransaction = fm!!.beginTransaction()
//        transaction.replace(R.id.layout, profile,"fragment_profile")
//        transaction.addToBackStack("fragment_profile")
//        transaction.commit()

        debugHashKey()



    }

    override fun onBackPressed() {

        val manager = supportFragmentManager.findFragmentById(R.id.layout)

        if (manager is authen ) {
            finish()
        }else if(manager is  profile){
            finish()
        }
        else{
            super.onBackPressed();
        }
//ดักปุ่มย้อนกลับ
    }
    private fun debugHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.facebook_authen",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

}
