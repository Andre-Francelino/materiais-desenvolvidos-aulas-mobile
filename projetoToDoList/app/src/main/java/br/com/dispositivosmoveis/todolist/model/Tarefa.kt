package br.com.dispositivosmoveis.todolist.model

data class Tarefa(
    var id: Long = 0,
    var descricao: String = "",
    var categoria: String = "",
    var concluida: Boolean = false
){
    override fun toString(): String {
        return "$descricao ($categoria)"
    }
}