package be.svlandeg.oxyfit.io

import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions
import java.util.*

/**
 * Class that reads a CSV table and performs preprocessing and filtering.
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */
class InputReader(private val location: String, private val sep: Char)
{
    /* Read a CSV table and drop the missing values */
    fun read(): Table
    {
        val builder = CsvReadOptions.builder(location).separator(sep)
        return Table.read().csv(builder.build()).dropRowsWithMissingValues()
    }

    /* Filter the rows of the table according to the gear you have available. This assumes a format of "gear1, gear2" per cell in the input */
    fun filterGear(inputTable: Table, availableGear: Set<String>): Table
    {
        val lowerGear = availableGear.map { it.toLowerCase() }

        //println(inputTable.stringColumn("Gear").lowerCase().split(",").map { it.trim() })
        //val outputTable = inputTable.where(inputTable.stringColumn("Gear").lowerCase().asList().isIn(lowerGear))
        return inputTable
    }

    /* Obtain the different values for a column, assuming a format of value1, value2 per cell in the input */
    fun getValues(inputTable: Table, columnName: String): TreeSet<String>
    {
        var values = sortedSetOf<String>()

        for (row in inputTable)
        {
            values.addAll(row.getString(columnName).split(",").map { it.toLowerCase().trim() })
        }
        return values

    }
}
