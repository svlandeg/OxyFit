package be.svlandeg.oxyfit.io

/**
 * This class defines a filter as a column name and a set of allowed values
 *
 * @author Sofie Van Landeghem (OxyKodit)
 */

data class ValueFilter(val columnName: String, val values: Set<String>)