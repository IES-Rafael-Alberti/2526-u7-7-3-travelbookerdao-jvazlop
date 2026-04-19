package es.iesra.datos

import es.iesra.datos.DAO.ReservaVueloDAO
import es.iesra.datos.DAO.ReservaHotelDAO
import es.iesra.dominio.Reserva
import es.iesra.dominio.ReservaVuelo
import es.iesra.dominio.ReservaHotel

class ReservaRepository(
    private val vueloDAO: ReservaVueloDAO,
    private val hotelDAO: ReservaHotelDAO
) : IReservaRepository {

    override fun agregar(reserva: Reserva): Boolean {
        return when (reserva) {
            is ReservaVuelo -> {
                vueloDAO.guardar(reserva)
                true
            }
            is ReservaHotel -> {
                hotelDAO.guardar(reserva)
                true
            }
            else -> false
        }
    }

    override fun obtenerTodas(): List<Reserva> {
        val vuelos = vueloDAO.obtenerTodas()
        val hoteles = hotelDAO.obtenerTodas()

        return vuelos + hoteles
    }
}