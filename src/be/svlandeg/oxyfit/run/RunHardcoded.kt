package be.svlandeg.oxyfit.run

import be.svlandeg.oxyfit.io.InputReader
import be.svlandeg.oxyfit.processing.RandomSelection

/**
 * Class that runs the pipeline with hardcoded values
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */
fun main(args : Array<String>) {
    val inputFile = "data/input/exercises.tab"
    val sep = '\t'

    val exTable = InputReader(inputFile, sep).read()
    println("read " + exTable.rowCount() + " rows")

    // for now, assuming a simple sampling selection of 20 exercises
    val nrRows = 20
    val exSample = RandomSelection(exTable, nrRows).process()
    println("randomly selected " + exSample.rowCount() + " rows")

    println(exSample)
}