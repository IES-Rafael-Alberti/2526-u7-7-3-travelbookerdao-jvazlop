package es.iesra.DAO

interface IDAO<T> {
    fun guardar(obj: T)
    fun obtenerTodas(): List<T>
    fun eliminar(id: Int): Boolean
    fun actualizar(obj: T): Boolean
}