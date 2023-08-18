import java.text.NumberFormat

fun main(){
    val produtos = listOf(
        Produto(1, "Xbox", 1500.0, "Games", 0),
        Produto(2, "PS4", 2000.0, "Games", 5),
        Produto(3, "Koltin in Action", 120.0, "Livros", 10),
        Produto(4, "Java Como Programar", 100.0, "Livros", 2),
        Produto(5, "Notebook Dell XPS", 11000.0, "Informática", 10),
        Produto(6, "Macbook Pro", 15000.0, "Informática", 15),
        Produto(codigo = 7, nome = "Nintendo Switch", categoria = "Games")
    )

    /*
    val linguagens = listOf("Java", "Kotlin", "C#")
    val imprimirLinguagem = fun(l: String){
        println(l)
    }
    linguagens.forEach(imprimirLinguagem)*/

    println("A - Todos os produtos")
    produtos.forEach { item -> println(item) }

    println("B - Games")
    produtos
        .filter {  it.categoria == "Games"}
        .forEach { println(it) }

    /*
    for(p in produtos){
        if(p.categoria == "Games"){
            println(p.toString())
        }
    }*/
    println("C - Produtos < 2.000")
    for(p in produtos){
        if(p.preco < 2000.0){
            println(p)
        }
    }
    println("D - Sem estoque")
    for(p in produtos){
        if(p.estoque == 0){
            println(p)
        }
    }
    println("E - Livros e estoque < 10")
    for(p in produtos){
        if(p.estoque < 10 && p.categoria == "Livros"){
            println(p)
        }
    }
    println("F - Nomes dos produtos concatenados com a categoria. Ex: Xbox (Games)")
    produtos
        .map { p -> "${p.nome} (${p.categoria})"}
        .forEach {  println(it) }
}

fun Double.format() : String{
    val formatter = NumberFormat.getCurrencyInstance()
    return formatter.format(this)
}

data class Produto(
    val codigo: Int,
    val nome: String,
    val preco: Double = 0.0,
    val categoria: String = "Outros",
    val estoque: Int = 0
){
    override fun toString() =
       "Código: $codigo Nome: $nome Preço: ${preco.format()} Categoria: $categoria Estoque: $estoque"
}
