@Regression
Feature: browserstack Demo App

  @smoke
  Scenario: BStack App File
    Given I am on the website 'https://www.bstackdemo.com'
    When I select a product and click on 'Add to cart' button
    Then the product should be added to cart


   Scenario: sample one more
     Given I am on the website 'https://www.bstackdemo.com'