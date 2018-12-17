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

    /* Obtain the different values for a column, assuming a format of value1, value2 per cell in the input */
    fun getValues(inputTable: Table, columnName: String): TreeSet<String>
    {
        val values = sortedSetOf<String>()

        for (row in inputTable)
        {
            values.addAll(row.getString(columnName).split(",").map { it.toLowerCase().trim() })
        }
        return values

    }

    /* Filter the rows of the table according to the required filters. This assumes a format of "gear1, gear2" per cell in the input */
    fun filterValues(inputTable: Table, filters: Set<ValueFilter>): Table
    {
        var outputTable = inputTable.copy()
        //var rowsToDrop = arrayOf<Int>()
        val rowsToDrop: MutableList<Int> = ArrayList()
        for (row in inputTable)
        {
            var isOK = true
            for (filter in filters)
            {
                // only perform the check if everything is OK up until this filter
                if (isOK)
                    isOK = filter.values.containsAll(row.getString(filter.columnName).split(",").map { it.toLowerCase().trim() })
            }
            if (! isOK)
            {
                rowsToDrop.add(row.rowNumber)
            }
        }
        if (! rowsToDrop.isEmpty())
        {
            outputTable = outputTable.dropRows(*rowsToDrop.toIntArray())
        }
        return outputTable
    }

}
