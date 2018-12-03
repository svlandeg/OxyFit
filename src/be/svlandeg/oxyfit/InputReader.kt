package be.svlandeg.oxyfit

import tech.tablesaw.api.Table
import tech.tablesaw.api.IntColumn
import tech.tablesaw.io.csv.CsvReadOptions



fun main(args : Array<String>) {
    val builder = CsvReadOptions.builder("data/input/exercises.tab").separator('\t')
    val exTable = Table.read().csv(builder.build()).dropRowsWithMissingValues()

    // for now, assuming a simple sampling selection of 20 exercises
    val nrRows = 20
    var exSample = exTable.sampleN(nrRows);

    // shuffle the 20 selected rows randomly. use 1-based index because this will be shown to users.
    val indexList = (1..nrRows).toList().shuffled().toIntArray()
    val exIndex = IntColumn.create("Index", indexList)
    exSample.addColumns(exIndex)
    exSample = exSample.sortOn("Index")

    println(exSample)

}