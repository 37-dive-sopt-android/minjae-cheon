package com.sopt.dive

import android.content.Intent

data class UserInfo(val id: String, val pw: String,
                    val nickname: String, val mbti: String) {

    constructor(data: Intent?) : this(
        id = data?.getStringExtra("ID") ?: "",
        pw = data?.getStringExtra("PW") ?: "",
        nickname = data?.getStringExtra("NICKNAME") ?: "",
        mbti = data?.getStringExtra("MBTI") ?: ""
    ) {}
//    fun setInfo(data: Intent?) {
//        id = data?.getStringExtra("ID") ?: ""
//        pw = data?.getStringExtra("PW") ?: ""
//        nickname = data?.getStringExtra("NICKNAME") ?: ""
//        mbti = data?.getStringExtra("MBTI") ?: ""
//    }
    fun setIntent(intent: Intent): Intent {
        intent.putExtra("ID", id)
        intent.putExtra("PW", pw)
        intent.putExtra("NICKNAME", nickname)
        intent.putExtra("MBTI", mbti)
        return intent
    }

    fun validate(id: String, pw: String): Boolean {
        return this.id == id && this.pw == pw
    }
}