
Feature:  Servicios Rest en Reserva de Coleccion

  @Servicios
  Scenario Outline: Prueba de Servicios Booking
    Given Iniciamos con la generacion de token
    When Creamos una reserva de coleccion "<caso_prueba>"
    Then Modificamos la reserva de coleccion
    And Obtenemos todas las reservas de colecion
    And Eliminamos la reserva de coleccion
    Examples:
      | caso_prueba |
      | 1           |
