package be.svlandeg.oxyfit.processing

import tech.tablesaw.api.IntColumn
import tech.tablesaw.api.Table

/**
 * Class that processes a raw set of exercises into a proper, timed program
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */
class RandomSelection(private val exTable: Table, private val nrRows: Int)
{
    fun process(): Table
    {
        var exSample = exTable.sampleN(nrRows)

        // shuffle the selected rows randomly. use 1-based index because this will be shown to users.
        val indexList = (1..nrRows).toList().shuffled().toIntArray()
        val exIndex = IntColumn.create("Index", indexList)
        exSample.addColumns(exIndex)
        exSample = exSample.sortOn("Index")
        return exSample
    }
}