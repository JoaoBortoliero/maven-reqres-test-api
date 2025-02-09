@run
Feature: Gerenciamento de usuários

@CP001
  Scenario Outline: CT001_Validar criacao de usuario com sucesso
    Given crio usuario com <name> e <job>
    When realizo requisicao
    Then informa sucesso na operacao <operation>

    Examples:
      | name       | job      | operation |
      | "morpheus" | "leader" | "create" |

  Scenario Outline: CT002_Validar registro de usuario com sucesso
    Given registro usuario com <email> e <password>
    When realizo requisicao
    Then informa sucesso na operacao <operation>

    Examples:
      | email                | password | operation   |
      | "eve.holt@reqres.in" | "pistol" | "register" |

  Scenario Outline: CT003_Validar registro de usuario sem sucesso
    Given registro usuario com <email>
    When realizo requisicao
    Then informa falha na operacao

    Examples:
      | email         |
      | "sydney@fife" |

  Scenario Outline: CT004_Validar login com sucesso
    Given login usuario com <email> e <password>
    When realizo requisicao
    Then informa sucesso na operacao <operation>

    Examples:
      | email                | password     | operation |
      | "eve.holt@reqres.in" | "cityslicka" | "login"  |

  Scenario Outline: CT005_Validar login sem sucesso
    Given login usuario com <email>
    When realizo requisicao
    Then informa falha na operacao

    Examples:
      | email          |
      | "peter@klaven" |

  Scenario Outline: CT006_Validar busca usuario por id com sucesso
    Given usuario com identificador <id>
    When realizo requisicao
    Then mostra usuario com identificador <id>

    Examples:
      | id |
      | 2  |

  Scenario Outline: CT007_Validar busca usuario por id sem sucesso
    Given usuario com identificador <id>
    When realizo requisicao
    Then nao localiza usuario

    Examples:
      | id   |
      | 1098 |