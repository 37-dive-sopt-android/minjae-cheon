package com.sopt.dive

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(val id: String = "", val pw: String = "",
                    val nickname: String = "", val mbti: String = "") {

    fun validate(id: String, pw: String): Boolean {
        return this.id == id && this.pw == pw
    }
}