Feature: Examples

  @test
  @prueba
  Scenario: Ingresar datos
    Given Navego a https://developers-qa.digital.interbank.pe/#/docs/ui/dividelo/sandbox
    Then  Cargo la información del DOM interBank
    And   Adjunto una captura de pantalla para informar: Home
    And   Configuro el elemento JWTBox con un nombre aleatorio
    And   Configuro el elemento SKBox con texto Eduardo
    And   Configuro el elemento MontoBox con texto 10000
    And   Adjunto una captura de pantalla para informar: HomePage
    And   Tiempo espera: 3000
    






