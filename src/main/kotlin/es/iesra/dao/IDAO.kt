package es.iesra.dao

interface IDAO<T> {
    fun guardar(obj: T)
    fun obtenerTodas(): List<T>
    fun eliminar(id: Int): Boolean
    fun actualizar(obj: T): Boolean
}