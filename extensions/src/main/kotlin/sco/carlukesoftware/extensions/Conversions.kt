package sco.carlukesoftware.extensions

fun Float.celsiusToFahrenheit() = (this * 9/5) + 32
fun Float.fahrenheitToCelsius() = (this - 32) * 5/9

fun Float.milesToKilometers() = this * 1.60934
fun Float.kilometresToMiles() = this * 0.621371

fun Float.poundsToKilograms() = this * 0.453592
fun Float.kilogramsToPounds() = this * 2.20462

fun Float.inchesToCentimetres() = this * 2.54
fun Float.centimetresToInches() = this * 0.39370
