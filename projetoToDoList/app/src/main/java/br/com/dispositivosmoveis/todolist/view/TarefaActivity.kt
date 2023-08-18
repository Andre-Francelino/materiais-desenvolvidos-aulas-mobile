package br.com.dispositivosmoveis.todolist.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import br.com.dispositivosmoveis.todolist.R
import br.com.dispositivosmoveis.todolist.data.TarefaDao
import br.com.dispositivosmoveis.todolist.model.Tarefa

class TarefaActivity : AppCompatActivity() {

    lateinit var editTextDescricao: EditText
    lateinit var buttonSalvar: Button
    lateinit var buttonExcluir: Button
    lateinit var spinnerCategoria: Spinner
    private lateinit var toolbar: Toolbar

    private var tarefa = Tarefa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_constraint)
        initComponents()
        initSpinnerCategorias()
        initListeners()
        initParams()
    }

    override fun onStart() {
        super.onStart()
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        toolbar.title = if (tarefa.id != 0L) getString(R.string.nova_tarefa) else getString(R.string.nova_tarefa)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    fun initParams() {
        val id = intent.getLongExtra("id", 0)
        val tarefaEncontrada = TarefaDao.find(id)
        if (tarefaEncontrada != null) {
            tarefa = tarefaEncontrada

            val categorias = resources.getStringArray(R.array.categorias)
            val selectedIndex = categorias.indexOf(tarefa.categoria)

            editTextDescricao.setText(tarefa.descricao)
            spinnerCategoria.setSelection(selectedIndex)
            buttonExcluir.visibility = View.VISIBLE
        } else {
            buttonExcluir.visibility = View.GONE
        }
    }

    fun initComponents() {
        editTextDescricao = findViewById(R.id.edit_text_descricao)
        buttonSalvar = findViewById(R.id.button_salvar)
        buttonExcluir = findViewById(R.id.button_excluir)
        spinnerCategoria = findViewById(R.id.spinner_categoria)
        toolbar = findViewById(R.id.toolbar)
    }

    private fun initSpinnerCategorias() {
        val categorias = resources.getStringArray(R.array.categorias)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            categorias,
        )

        spinnerCategoria.adapter = adapter
    }

    private fun initListeners() {
        buttonSalvar.setOnClickListener {
            tarefa.descricao = editTextDescricao.text.toString()
            tarefa.categoria = spinnerCategoria.selectedItem.toString()

            if (tarefa.id != 0L) {
                TarefaDao.editar(tarefa)
            } else {
                TarefaDao.inserir(tarefa)
            }

            Toast.makeText(this, R.string.mensagem_tarefa_adicionada, Toast.LENGTH_LONG).show()

            finish()
        }

        buttonExcluir.setOnClickListener {
            TarefaDao.excluir(tarefa)
            finish()
        }
    }
}
