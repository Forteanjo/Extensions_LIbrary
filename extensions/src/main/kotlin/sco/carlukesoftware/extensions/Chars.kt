package sco.carlukesoftware.extensions

/** carriage return - line feed sequence  */
const val CRLF: String = "\r\n"

/** US-ASCII CR, carriage return (13)  */
const val CR: Int = '\r'.code

/** US-ASCII LF, line feed (10)  */
const val LF: Int = '\n'.code

/** US-ASCII SP, space (32)  */
const val SP: Int = ' '.code

/** US-ASCII HT, horizontal-tab (9)  */
const val HT: Int = '\t'.code


val Char.isDigit: Boolean
    get() = this in '0'..'9'

/**
 * Returns `true` if the specified character falls into the US
 * ASCII character set (Unicode range 0000 to 007f).
 */
val Char.isASCII: Boolean
    get() = (0xFF80 and this.code) == 0

val Char.isExtendedASCII: Boolean
    get() = this.code in 128..255

val Char.isNativeC40: Boolean
    get() = (this == ' ') || (this in '0'..'9') || (this in 'A'..'Z')

val Char.isNativeText: Boolean
    get() = (this == ' ') || (this in '0'..'9') || (this in 'a'..'z')

val Char.isNativeX12: Boolean
    get() = this.isX12TermSep || (this == ' ') || (this in '0'..'9') || (this in 'A'..'Z')

val Char.isX12TermSep: Boolean
    get() = ((this == '\r') //CR
            || (this == '*')
            || (this == '>'))

val Char.isNativeEDIFACT: Boolean
    get() = this in ' '..'^'

val Char.isSpecialB256: Boolean
    get() = false //TODO NOT IMPLEMENTED YET!!!

/**
 * Returns `true` if the specified character is a whitespace
 * character (CR, LF, SP or HT).
 */
val Char.isWhitespace: Boolean
    get() = this.code == SP || this.code == HT || this.code == CR || this.code == LF
