Feature: Examples

  @test
  Scenario: Ingresar datos
    Given Navego a https://developers-qa.digital.interbank.pe/#/docs/ui/dividelo/sandbox
    Then  Cargo la información del DOM interBank
    And   Configuro el elemento JWTBox con texto Hola
    And   Configuro el elemento SKBox con texto Eduardo
    And   Configuro el elemento MontoBox con texto 10000
    And   tiempo espera: 3000
    






