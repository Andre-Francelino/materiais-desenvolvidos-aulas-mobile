package aula01

import java.text.NumberFormat

fun main(){

    val produtos = listOf(
        Produto(1, "PS4", 1700.0, 20, "Games"),
        Produto(2, "PS5", 4200.0, 10, "Games"),
        Produto(3, "Kotlin in Action", 80.0, 5, "Livros"),
        Produto(4, "Notebook Dell Vostro 15", 7200.0, 10, "Informática"),
        Produto(5, "Java Como Programar", 120.0, 0, "Livros"),
        Produto(6, "Xbox One", 1400.0, 0, "Games")
    )

    println("1 - Todos os produtos")
    val imprimir = fun(p: Produto){
        println(p)
    }

    val categoriaGames = fun(p: Produto) = p.categoria == "Games"

    produtos.forEach(imprimir)

    println("2 - Nomes dos produtos da categoria \"Games\"")
    produtos.filter(categoriaGames).forEach(imprimir)

    println("3 - Nomes dos produtos com preço inferior a R$ 2.000")
    produtos
        .filter { it.preco < 2000.0 }
        .forEach { println(it) }

    println("4 - Nomes dos produtos sem estoque")
    produtos
        .filter { it.estoque <= 0 }
        .forEach { println(it) }

    println("5 - Nomes dos produtos da categoria Livros e estoque abaixo de 10")
    produtos
        .filter { it.estoque <= 10 && it.categoria == "Livros" }
        .forEach { println(it) }
}


fun Double.format() : String{
    val formatter = NumberFormat.getCurrencyInstance()
    return formatter.format(this)
}

data class Produto(
    val codigo: Int,
    val nome: String,
    val preco: Double,
    val estoque: Int,
    val categoria: String
){
    override fun toString() = "Código: $codigo Nome: $nome Preço: ${preco.format()} Estoque: $estoque Categoria: $categoria"
}