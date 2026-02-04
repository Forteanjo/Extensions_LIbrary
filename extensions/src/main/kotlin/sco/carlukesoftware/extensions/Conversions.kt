package sco.carlukesoftware.extensions

/**
 * Converts this [Float] value from Celsius to Fahrenheit.
 *
 * @return The temperature in Fahrenheit as a [Float].
 */
fun Float.celsiusToFahrenheit() = (this * 9/5) + 32
/**
 * Converts a temperature value from Fahrenheit to Celsius.
 *
 * @return The temperature in Celsius as a [Float].
 */
fun Float.fahrenheitToCelsius() = (this - 32) * 5/9

/**
 * Converts this [Float] value from miles to kilometers.
 *
 * @return The distance in kilometers as a [Float].
 */
fun Float.milesToKilometers() = this * 1.60934
/**
 * Converts this [Float] value from kilometres to miles.
 *
 * @return The distance in miles as a [Float].
 */
fun Float.kilometresToMiles() = this * 0.621371

/**
 * Converts this [Float] value from pounds to kilograms.
 *
 * @return The weight in kilograms as a [Float].
 */
fun Float.poundsToKilograms() = this * 0.453592
/**
 * Converts this [Float] value from kilograms to pounds.
 *
 * @return The weight in pounds as a [Float].
 */
fun Float.kilogramsToPounds() = this * 2.20462

/**
 * Converts this [Float] value from inches to centimetres.
 *
 * @return The length in centimetres as a [Float].
 */
fun Float.inchesToCentimetres() = this * 2.54
/**
 * Converts this [Float] value from centimetres to inches.
 *
 * @return The length in inches as a [Float].
 */
fun Float.centimetresToInches() = this * 0.39370
