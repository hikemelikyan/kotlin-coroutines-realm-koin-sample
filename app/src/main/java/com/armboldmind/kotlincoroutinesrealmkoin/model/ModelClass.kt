package com.armboldmind.kotlincoroutinesrealmkoin.model

import io.realm.RealmObject

open class ModelClass : RealmObject() {
    var completed: Boolean = false
    var id: Int = 0
    var title: String = ""
    var userId: Int = 0
}