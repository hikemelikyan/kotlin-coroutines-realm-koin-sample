package com.armboldmind.kotlincoroutinesrealmkoin.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.ActivityPhoneAuthBySmsBinding
import com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base.BaseActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneAuthActivity : BaseActivity() {
    private lateinit var mBinding: ActivityPhoneAuthBySmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_auth_by_sms)

        mBinding.btnAuth.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mBinding.numberLayout.text.toString(),
                60,
                TimeUnit.SECONDS,
                this,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        showMessageToast(p0)
                    }

                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        try {
                            showMessageToast(p0.smsCode!!)
                        } catch (e: Exception) {
                            showMessageToast("SMS is empty")
                        }
                        signInWithPhoneAuthCredential(p0)
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        showMessageToast(p0.message!!)
                    }
                }
            )
        }
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    // ...
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        showMessageToast(task.exception!!.message!!)
                    }
                }
            }
    }

}