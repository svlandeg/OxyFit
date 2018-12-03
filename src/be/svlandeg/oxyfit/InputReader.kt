package be.svlandeg.oxyfit

import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions

fun main(args : Array<String>) {
    val builder = CsvReadOptions.builder("data/input/exercises.tab").separator('\t')
    val x = Table.read().csv(builder.build())

    println(x)
}