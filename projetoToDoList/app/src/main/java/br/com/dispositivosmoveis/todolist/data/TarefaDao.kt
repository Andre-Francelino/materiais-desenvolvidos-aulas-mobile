package br.com.dispositivosmoveis.todolist.data

import br.com.dispositivosmoveis.todolist.model.Tarefa

object TarefaDao {
    private val tarefas = mutableListOf<Tarefa>()

    fun inserir(tarefa: Tarefa) {
        tarefa.id = tarefas.size + 1L
        tarefas.add(tarefa)
    }

    fun editar(tarefa: Tarefa) {
        val tarefaEncontrada = find(tarefa.id)
        if (tarefaEncontrada != null) {
            tarefaEncontrada.descricao = tarefa.descricao
            tarefaEncontrada.concluida = tarefa.concluida
        }
    }

    fun excluir(tarefa: Tarefa) {
        val index = tarefas.indexOfFirst { it.id == tarefa.id}
        tarefas.removeAt(index)
    }

    fun findAll(): List<Tarefa> {
        return tarefas
    }

    fun find(id: Long): Tarefa? {
        return tarefas.find { tarefa -> tarefa.id == id }
    }
}
