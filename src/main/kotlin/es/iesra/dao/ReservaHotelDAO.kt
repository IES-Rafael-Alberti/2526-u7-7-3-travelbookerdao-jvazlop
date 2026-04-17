package es.iesra.datos.dao

import es.iesra.dominio.ReservaHotel
import java.io.File

class ReservaHotelDAO(
    private val filePath: String = "data/reservas_hotel.csv"
) {

    private val file = File(filePath)

    init {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()

            // Cabecera CSV
            file.appendText("id,descripcion,ubicacion,numeroNoches\n")
        }
    }

    fun guardar(reserva: ReservaHotel) {
        val linea = "${reserva.id},${reserva.descripcion},${reserva.ubicacion},${reserva.numeroNoches}\n"
        file.appendText(linea)
    }

    fun obtenerTodas(): List<ReservaHotel> {
        return file.readLines()
            .drop(1)
            .filter { it.isNotBlank() }
            .map { linea ->
                val partes = linea.split(",")

                ReservaHotel.creaInstancia(
                    partes[1],
                    partes[2],
                    partes[3].toInt()
                )
            }
    }

    fun eliminar(id: Int): Boolean {
        val lista = obtenerTodas().toMutableList()
        val eliminado = lista.removeIf { it.id == id }

        if (eliminado) {
            file.writeText("id,descripcion,ubicacion,numeroNoches\n")
            lista.forEach { guardar(it) }
        }

        return eliminado
    }

    fun actualizar(reserva: ReservaHotel): Boolean {
        val lista = obtenerTodas().toMutableList()
        val index = lista.indexOfFirst { it.id == reserva.id }

        if (index != -1) {
            lista[index] = reserva

            file.writeText("id,descripcion,ubicacion,numeroNoches\n")
            lista.forEach { guardar(it) }

            return true
        }

        return false
    }
}