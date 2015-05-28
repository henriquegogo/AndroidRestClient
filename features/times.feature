Feature: Listar times

  Scenario: Como usuário eu devo poder ver os times
    Given I am on matches screen
    When I swipe left
    And I should see "Cruzeiro"
    And I should see "Fluminense"
    And I should see "Botafogo"
    And I should see "Vitória"
    And I should see "Santos"
    And I should see "Figueirense"

  Scenario: Como usuário eu devo poder filtrar por time
    Given I am on matches screen
    Then I should see "Cruzeiro"
    And I should see "Fluminense"
    And I should see "Botafogo"
    And I should see "Figueirense"
    When I swipe left
    And I press "Cruzeiro"
    Then I should see "Cruzeiro"
    And I should see "Fluminense"
    And I should not see "Botafogo"
    And I should not see "Figueirense"
