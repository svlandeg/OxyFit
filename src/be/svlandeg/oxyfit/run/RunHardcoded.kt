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

    // val availableGear = SetOf<String>("None").toLowerCase()
    val availableGear = setOf("None", "Kettlebell", "Dumbbell", "2 dumbbells", "Any weight", "Fitness mat", "Speed rope")

    val reader = InputReader(inputFile, sep)
    val exTable = reader.read()
    println("read " + exTable.rowCount() + " rows")

    //val filteredTable = reader.filterGear(exTable, availableGear)
    //println("filtered down to " + filteredTable.rowCount() + " rows")

    /* print the gears, muscles and types present in the input */
    println()
    println("gears: " + reader.getValues(exTable, "Gear"))
    println("muscles: " + reader.getValues(exTable, "Muscles"))
    println("types: " + reader.getValues(exTable, "Type"))
    println()

    /* for now, assuming a simple sampling selection of 20 exercises */
    val nrRows = 15
    val exSample = RandomSelection(exTable, nrRows).process()
    println("randomly selected " + exSample.rowCount() + " rows")

    println(exSample)
}