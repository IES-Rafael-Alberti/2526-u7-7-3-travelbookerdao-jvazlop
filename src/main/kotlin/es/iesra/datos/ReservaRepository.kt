package es.iesra.datos

import es.iesra.dominio.Reserva
import es.iesra.DAO.ReservaVueloDAO
import es.iesra.dominio.ReservaVuelo
/**
 * Implementación en memoria del repositorio de reservas.
 */
class ReservaRepository(
    private val vueloDAO: ReservaVueloDAO
) : IReservaRepository {

    override fun agregar(reserva: Reserva): Boolean {
        return if (reserva is ReservaVuelo) {
            vueloDAO.guardar(reserva)
            true
        } else {
            false
        }
    }

    override fun obtenerTodas(): List<Reserva> {
        return vueloDAO.obtenerTodas()
    }
}