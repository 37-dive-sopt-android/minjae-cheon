package com.sopt.dive

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(val username: String = "", val pw: String = "",
                    val name: String = "", val email: String = "",
                    val age: Int = 0)

data class UserUIInfo(
    val text: String, val label: String,
    val value: String, val onChange: (String) -> Unit,
    val inputVisibility: Boolean)