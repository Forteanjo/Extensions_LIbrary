package sco.carlukesoftware.extensions

interface Mapper<in F, out T> {

    operator fun invoke(from: F): T

}
