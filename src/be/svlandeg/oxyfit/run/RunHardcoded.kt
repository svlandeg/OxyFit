package be.svlandeg.oxyfit.run

import be.svlandeg.oxyfit.io.InputReader
import be.svlandeg.oxyfit.io.ValueFilter
import be.svlandeg.oxyfit.processing.RandomSelection

/**
 * Class that runs the pipeline with hardcoded values
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */
fun main(args : Array<String>) {
    val inputFile = "data/input/exercises.tab"
    val sep = '\t'

    /* STEP 0 : read input data */
    val reader = InputReader(inputFile, sep)
    val exTable = reader.read()
    println("read " + exTable.rowCount() + " rows")

    /* STEP 1 :print the gears, muscles and types present in the input */
    println()
    println("gears: " + reader.getValues(exTable, "Gear"))
    println("muscles: " + reader.getValues(exTable, "Muscles"))
    println("types: " + reader.getValues(exTable, "Type"))
    println()

    /* STEP 2: define the filters */

    // These are all current values
    // val availableGear = setOf("2 dumbbells", "any weight", "dumbbell", "fitness mat", "kettlebell", "none", "speed rope")
    // val availableMuscles = setOf("abs", "arms", "full body", "legs")
    // val availableTypes = setOf("cardio", "muscle exercise", "warming up")

    // ad-hoc user decisions
    val availableGear = setOf("any weight", "dumbbell", "fitness mat", "none")
    val availableMuscles = setOf("abs", "arms", "full body", "legs")
    val availableTypes = setOf("cardio", "muscle exercise", "warming up")

    val gearFilter = ValueFilter("Gear", availableGear)
    val muscleFilter = ValueFilter("Muscles", availableMuscles)
    val typeFilter = ValueFilter("Type", availableTypes)
    val filters = setOf(gearFilter, muscleFilter, typeFilter)

    /* STEP 3: do the filtering */
    val filteredTable = reader.filterValues(exTable, filters)
    println("filtered down to " + filteredTable.rowCount() + " rows")

    /* STEP 4: create the selection */
    /* for now, assuming a simple sampling selection of 20 exercises */
    val nrRows = 15
    val exSample = RandomSelection(filteredTable, nrRows).process()
    println("randomly selected " + exSample.rowCount() + " rows")

    println(exSample)
}