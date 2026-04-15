package es.iesra.DAO

import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaVueloDAO(
    private val filePath: String = "data/reservas_vuelo.txt"
) : IDAO<ReservaVuelo> {

    private val file = File(filePath)

    init {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
    }

    override fun guardar(obj: ReservaVuelo) {
        file.appendText("${obj.id}|${obj.descripcion}|${obj.origen}|${obj.destino}|${obj.horaVuelo}\n")
    }

    override fun obtenerTodas(): List<ReservaVuelo> {
        return file.readLines()
            .filter { it.isNotBlank() }
            .map { linea ->
                val partes = linea.split("|")
                ReservaVuelo.creaInstancia(
                    partes[1],
                    partes[2],
                    partes[3],
                    partes[4]
                )
            }
    }

    override fun eliminar(id: Int): Boolean {
        val lista = obtenerTodas().toMutableList()
        val eliminado = lista.removeIf { it.id == id }

        if (eliminado) {
            file.writeText("")
            lista.forEach { guardar(it) }
        }

        return eliminado
    }

    override fun actualizar(obj: ReservaVuelo): Boolean {
        val lista = obtenerTodas().toMutableList()
        val index = lista.indexOfFirst { it.id == obj.id }

        if (index != -1) {
            lista[index] = obj
            file.writeText("")
            lista.forEach { guardar(it) }
            return true
        }

        return false
    }
}