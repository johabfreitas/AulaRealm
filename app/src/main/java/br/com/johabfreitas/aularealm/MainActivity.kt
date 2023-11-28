package br.com.johabfreitas.aularealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.johabfreitas.aularealm.database.DatabaseRealm
import br.com.johabfreitas.aularealm.databinding.ActivityMainBinding
import br.com.johabfreitas.aularealm.model.Usuario
import org.mongodb.kbson.ObjectId

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val realm by lazy {
        DatabaseRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener{

            val nomeRecuperado = binding.editNome.text.toString()

            val usuario = Usuario().apply {
                nome = nomeRecuperado
                idade = 10
            }
            realm.salvar(usuario)
        }

        binding.btnListar.setOnClickListener {

            val lista = realm.listar()

            var textoLista = ""
            lista.forEach{usuario ->
                textoLista += "${usuario.nome} - idade: ${usuario.idade} \n"
                Log.i("info_realm", "id: ${usuario.id} - ${usuario.nome}")
            }
            binding.textResultado.text = textoLista
        }

        binding.btnRemover.setOnClickListener {

            //6565e8c071943a174ecc99b5
            val id = ObjectId("6565e8c071943a174ecc99b5")
            realm.remover(id)
        }

        binding.btnAtualizar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()
            val idSelecionado = ObjectId("65662bdd2c31867fca2ebf0c")
            val usuario = Usuario().apply {
                id = idSelecionado
                nome = nomeRecuperado
                idade = 40
            }
            realm.atualizar(usuario)
        }

    }
}