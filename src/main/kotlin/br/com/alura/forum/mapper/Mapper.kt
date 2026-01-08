package br.com.alura.forum.mapper

interface Mapper<T, U> {
    fun map(input: T): U
}