package sco.carlukesoftware.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

//fun LocalDateTime.formatTo(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
//    return format(DateTimeFormatter.ofPattern(pattern))
//}
//
//fun LocalDate.isWeekend(): Boolean {
//    return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
//}
//fun LocalDate.isWeekday(): Boolean {
//    return !isWeekend()
//}
//fun LocalDateTime.toEpochMillis(): Long {
//    return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
//}
//fun Long.toLocalDateTime(): LocalDateTime {
//    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
//}
