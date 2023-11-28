package br.com.johabfreitas.aularealm.model

import io.realm.kotlin.internal.ObjectIdImpl
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Usuario : RealmObject {

    @PrimaryKey
    var id: ObjectId = ObjectId()

    var nome = ""
    var idade = 0

}