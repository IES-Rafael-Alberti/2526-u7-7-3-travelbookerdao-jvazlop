package es.iesra.datos

import es.iesra.dominio.Reserva
import es.iesra.datos.dao.ReservaVueloDAO
import es.iesra.datos.dao.ReservaHotelDAO
import es.iesra.dominio.ReservaVuelo
import es.iesra.dominio.ReservaHotel

/**
 * Implementación en memoria del repositorio de reservas.
 */
class ReservaRepository(
    private val vueloDAO: ReservaVueloDAO,
    private val hotelDAO: ReservaHotelDAO
) : IReservaRepository

    override fun agregar(reserva: Reserva): Boolean {
        when (reserva) {
            is ReservaVuelo -> vueloDAO.guardar(reserva)
            is ReservaHotel -> hotelDAO.guardar(reserva)
        }
        return true
    }

    override fun obtenerTodas(): List<Reserva> {
        return vueloDAO.obtenerTodas() + hotelDAO.obtenerTodas()
    }
}