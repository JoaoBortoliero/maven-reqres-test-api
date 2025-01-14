@run
Feature: Realizar cadastro de usuario

@CP001
  Scenario Outline: CT001_Validar criacao de usuario com sucesso
    Given crio usuario com <name> e <job>
    When realizo requisicao
    Then informa sucesso na criacao

    Examples:
      | name  | job  |
      | "morpheus" | "leader" |

  Scenario Outline: CT002_Validar registro de usuario com sucesso
    Given registro usuario com <email> e <password>
    When realizo requisicao
    Then informa sucesso no registro

    Examples:
      | email  | password  |
      | "eve.holt@reqres.in" | "pistol" |

  Scenario Outline: CT003_Validar registro de usuario sem sucesso
    Given registro usuario com <email>
    When realizo requisicao
    Then informa falha na operacao

    Examples:
      | email  |
      | "sydney@fife" |

  Scenario Outline: CT004_Validar login com sucesso
    Given login usuario com <email> e <password>
    When realizo requisicao
    Then informa sucesso no login

    Examples:
      | email  | password  |
      | "eve.holt@reqres.in" | "cityslicka" |

  Scenario Outline: CT005_Validar login sem sucesso
    Given login usuario com <email>
    When realizo requisicao
    Then informa falha na operacao

    Examples:
      | email  |
      | "peter@klaven" |