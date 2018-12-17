package be.svlandeg.oxyfit.io

import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions

/**
 * Class that reads a CSV table and does minimal preprocessing
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */
class InputReader(private val location: String, private val sep: Char)
{
    // Read a CSV table and drop the missing values
    fun read(): Table
    {
        val builder = CsvReadOptions.builder(location).separator(sep)
        return Table.read().csv(builder.build()).dropRowsWithMissingValues()
    }
}
