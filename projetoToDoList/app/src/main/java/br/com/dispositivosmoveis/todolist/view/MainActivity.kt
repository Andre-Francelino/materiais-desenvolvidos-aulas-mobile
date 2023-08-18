package br.com.dispositivosmoveis.todolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import br.com.dispositivosmoveis.todolist.R
import br.com.dispositivosmoveis.todolist.data.TarefaDao
import br.com.dispositivosmoveis.todolist.model.Tarefa
import br.com.dispositivosmoveis.todolist.view.adapter.TarefaAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var imageViewTask: ImageView
    lateinit var textViewSemTarefas: TextView
    lateinit var listViewTarefas: ListView
    lateinit var buttonNovaTarefa: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_contraint)
        initComponents()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        initTarefas()
    }

    private fun initComponents() {
        listViewTarefas = findViewById(R.id.list_view_tarefas)
        textViewSemTarefas = findViewById(R.id.text_view_sem_tarefas)
        imageViewTask = findViewById(R.id.image_view_task)
        buttonNovaTarefa = findViewById(R.id.fab_nova_tarefa)
    }

    private fun initListeners(){
        buttonNovaTarefa.setOnClickListener {
            val intent = Intent(this, TarefaActivity::class.java)
            startActivity(intent)
        }
        listViewTarefas.setOnItemClickListener { _, view, _, id ->
            val tarefa = TarefaDao.find(id)
            if(tarefa != null){
                tarefa.concluida = !tarefa.concluida
                TarefaDao.editar(tarefa)
                view.findViewById<CheckBox>(R.id.check_box_concluida).isChecked = tarefa.concluida
            }
        }
        listViewTarefas.onItemLongClickListener = AdapterView.OnItemLongClickListener{ _, _, _, id ->
            val intent = Intent(this, TarefaActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            true
        }
    }

    private fun initTarefas(){
        // 1 - pegar lista de tarefas do TarefasDao
        // 2 - Criar adapter do listView
        // 3 - Atribuir adapter ao listview
        val tarefas = TarefaDao.findAll()

        if(tarefas.isNotEmpty()){
            val adapter = TarefaAdapter(this, tarefas)

            listViewTarefas.adapter = adapter

            textViewSemTarefas.visibility = View.GONE
            imageViewTask.visibility = View.GONE
            listViewTarefas.visibility = View.VISIBLE
        }
        else{
            imageViewTask.visibility = View.VISIBLE
            textViewSemTarefas.visibility = View.VISIBLE
            listViewTarefas.visibility = View.GONE
        }
    }
}