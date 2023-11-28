package br.com.johabfreitas.aularealm.database

import android.util.Log
import br.com.johabfreitas.aularealm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.Sort
import org.mongodb.kbson.ObjectId

class DatabaseRealm {

    private val configuracao = RealmConfiguration.Builder(
        schema = setOf(Usuario::class)
    )

    private val realm = Realm.open(configuracao.build())

    fun salvar(usuario: Usuario){

        realm.writeBlocking {
            copyToRealm(usuario)
        }

        Log.i("info_realm", "Salvo com sucesso")

    }

    fun listar() : RealmResults<Usuario>{

        //val lista =
        return realm
            //.query<Usuario>("nome == $0", "Johab")
            .query<Usuario>()
            //.sort("nome", Sort.ASCENDING)
            .find()

        //return lista
    }

    fun remover(id: ObjectId){
        realm.writeBlocking {
            val usuarioRemover = query<Usuario>("id == $0", id)
                .find().first()

            delete(usuarioRemover)
        }
    }

    fun atualizar(usuario: Usuario){
        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("id == $0", usuario.id)
                .find().first()

            usuarioAtualizar.nome= usuario.nome
            usuarioAtualizar.idade = usuario.idade
        }

    }
}