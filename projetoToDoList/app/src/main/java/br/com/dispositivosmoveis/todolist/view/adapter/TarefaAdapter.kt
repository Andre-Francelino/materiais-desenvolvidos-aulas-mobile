package br.com.dispositivosmoveis.todolist.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import br.com.dispositivosmoveis.todolist.R
import br.com.dispositivosmoveis.todolist.model.Tarefa

class TarefaAdapter(
    val context: Context,
    val tarefas: List<Tarefa>
    ) : BaseAdapter() {

    override fun getCount() = tarefas.size

    override fun getItem(position: Int) = tarefas[position]

    override fun getItemId(position: Int) = tarefas[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // pegar tarefa da posicao
        val tarefa = tarefas[position]
        // pegar o xml do template do item
        val view = LayoutInflater.from(context).inflate(R.layout.tarefa_item, null)
        // pegar o textView
        val textDescricao = view.findViewById<TextView>(R.id.text_view_descricao)
        // pegar o ImageView
        val imageViewIcone = view.findViewById<ImageView>(R.id.image_view_icone)
        // pegar checkbox
        val checkBoxConcluida = view.findViewById<CheckBox>(R.id.check_box_concluida)

        textDescricao.setText(tarefa.descricao)
        imageViewIcone.setImageResource(icon(tarefa))
        checkBoxConcluida.isChecked = tarefa.concluida

        return view
    }

    private fun icon(tarefa: Tarefa): Int {
        val icon = when (tarefa.categoria) {
            "Trabalho" -> R.drawable.ic_trabalho
            "Casa" -> R.drawable.ic_home
            else -> {
                R.drawable.ic_estudo
            }
        }
        return icon
    }
}