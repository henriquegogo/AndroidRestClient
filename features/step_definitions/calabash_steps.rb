require 'calabash-android/calabash_steps'

Given(/^I am on matches screen$/) do
  element_exists("* id:'matchesListView'")
end
