Feature:  correct work toolService
  Scenario: Add new user in db from 3 csv file
    Given user path src/main/resources/user.csv city path src/main/resources/city.csv and country path src/main/resources/country.csv
    When toolService write data 4 FlatUserEntity in db
    Then toolService work correct and add new 4 entity