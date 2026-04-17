package es.iesra.datos.DAO

import es.iesra.dominio.ReservaVuelo
import java.io.File

class ReservaVueloDAO(
    private val filePath: String = "reservas_vuelo.csv"
) {

    private val file = File(filePath)

    init {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
            file.appendText("id,descripcion,origen,destino,hora\n")
        }
    }

    fun guardar(reserva: ReservaVuelo) {
        val linea = "${reserva.id},${reserva.descripcion},${reserva.origen},${reserva.destino},${reserva.horaVuelo}\n"
        file.appendText(linea)
    }

    fun obtenerTodas(): List<ReservaVuelo> {
        return file.readLines()
            .drop(1)
            .filter { it.isNotBlank() }
            .map { linea ->
                val partes = linea.split(",")

                ReservaVuelo.creaInstancia(
                    partes[1],
                    partes[2],
                    partes[3],
                    partes[4]
                )
            }
    }

    fun eliminar(id: Int): Boolean {
        val lista = obtenerTodas().toMutableList()
        val eliminado = lista.removeIf { it.id == id }

        if (eliminado) {
            file.writeText("id,descripcion,origen,destino,hora\n")
            lista.forEach { guardar(it) }
        }

        return eliminado
    }

    fun actualizar(reserva: ReservaVuelo): Boolean {
        val lista = obtenerTodas().toMutableList()
        val index = lista.indexOfFirst { it.id == reserva.id }

        if (index != -1) {
            lista[index] = reserva

            file.writeText("id,descripcion,origen,destino,hora\n")
            lista.forEach { guardar(it) }

            return true
        }

        return false
    }
}