Feature: Listar partidas

  Scenario: Como usuário eu devo acessar a tela inicial
    Given I am on matches screen
    Then I should see "Placar GE"

  Scenario: Como usuário eu devo visualizar as partidas
    Given I am on matches screen
    Then I should see "Acompanhe em tempo real"
    And I should see "Mineirão"
    And I should see "Cruzeiro"
    And I should see "Fluminense"

  Scenario: Quando eu clicar, devo acessar o link
    Given I am on matches screen
    When I touch the "Acompanhe em tempo real" text
    Then I wait for the view with id "webView" to appear
