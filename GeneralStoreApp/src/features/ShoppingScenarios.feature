Feature: Verify the different functionality of General Store App

@TC1
Scenario: Verify toast error message displayed for wrong inputs
Given User launches General store app
When User submits with blank User Name
Then toast error message is displayed
And User completes the task

@TC2
Scenario: Shop items by scrolling to specific product and add to cart
  Given User launches General store app
  When User fills form
  Then User lands to Home Page
  And User selects "Jordan 6 Rings" product and adds to cart
  And User completes the task

@TC3
Scenario: Validate the total amount matches for the selection items's amount sum
  Given User launches General store app
  When User fills form
  Then User lands to Home Page
  And User selects "multiple" product and adds to cart
  And User verifies total amount is correct
  And User completes the task

@TC4
Scenario: Validate the gestures of Long Press and navigate to WebView
  Given User launches General store app
  When User fills form
  Then User lands to Home Page
  And User selects "Air Jordan 4 Retro" product and adds to cart
  And User verifies total amount is correct
  And User navigates to Web View
  And User completes the task

@TC5
Scenario: Automate Hybrid App with context switch
  Given User launches General store app
  When User fills form
  Then User lands to Home Page
  And User selects "Jordan Lift Off" product and adds to cart
  And User verifies total amount is correct
  And User navigates to Web View
  And User completes the task