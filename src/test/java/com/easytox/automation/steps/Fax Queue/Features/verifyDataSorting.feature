@bvt @smoke @uat
Feature: Verify data sorting

@positive 
Scenario: Verify data sorting 

Given User should be login to lab
When Click on '˅' down arrow icon on sorting column
Then Records should be displayed based on the asecending order of the selected sorting column

When Click on '^' Up arrow icon on sorting column
Then Records should be displayed based on the descending order of the selected sorting column