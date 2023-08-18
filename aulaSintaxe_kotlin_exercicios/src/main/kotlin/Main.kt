fun main(args: Array<String>){
    // Variáveis
    // normalmente devem ser inicializadas na declaração
    // não aceitam null por padrão
    // podemos usar var ou val
    // var = podemos modificar o valor
    // val = imutável, constante
    // recomendado sempre usar val e se for necessário modificar o valor da variável, aí sim mudamos para var
    // Definir o tipo explicitamente é opcional, já que o kotlin consegue identificar o tipo pelo valor atribuído
    val nome : String = "André"
    val idade: Int = 31
    val altura = 1.61
    val possuiCNH = true

    // arrays e listas
    // arrays = tamanho fixo
    // listas = dinamica
    // as listas podem ser mutáveis ou imutáveis
    val numeros: Array<Int> = arrayOf(1, 2, 3)
    val linguagens: List<String> = listOf("Java", "Kotlin", "Python", "JavaScript")

    // erro, nao conseguimos inserir em uma lista imutável
    //linguagens.add("PHP")
    //linguagens.add("Go")

    val livros: MutableList<String> = mutableListOf("Kotlin para Android", "Kotlin em Ação")
    livros.add("Java Como Programar")

    // estruturas de decisão
    if(possuiCNH){
        println("Possui habilitação")
    }
    else{
        println("Será multado.")
    }
    // podemos retornar um valor utilizando if
    val msg =
        if(possuiCNH)
            "Possui habilitação"
        else
            "Será multado"

    // when (decisão múltipla, similar ao switch-case)

    when(idade){
        in 20..30 -> { // intervalo de valores

        }
        40, 50 -> { // 40 ou 50

        }
        else -> {

        }
    }

    // repetição (for)
    for(l in linguagens){
        println(l)
    }
    // com índice
    for((i, l) in linguagens.withIndex()){
        println("$i - $l")
    }

    // chamando funções
    println("Soma: ${somar2(10,10)}")
}

// função
fun somar(x: Int, y: Int) : Int{
    return x + y
}

// expression funcion
fun somar2(x: Int, y: Int) = x + y
