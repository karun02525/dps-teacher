package org.dpsteacher.model

data class SelectUserModel(val fname:String, val lname: String, val rollno:String, val avatar:String, var isActive:Boolean=false)