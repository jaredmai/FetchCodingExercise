# Fetch Coding Exercise
This is the repository for the Fetch Rewards mobile coding exercise

## Goal
The goal of this project is to build an Android application in Kotlin that displays JSON data in a list.

The following requirements were given:
* JSON Objects include id, listId, and name
* Objects where the name is "" or "null" should be ignored
* List should be sorted first by ListId, then by Name


## Implementation
* The implementation of the project requirements is done in as simple as possible. I started by setting the main_activity XML file to have a ListView, the purpose of this is to display the JSON object in a list
* The next step was to create a Model that stores the three data entries in each object, this model is called DataModel.kt in the project
* From there we need to create an adapter, this adapter's purpose is to convert the data from the JSON objects into UI elements
* The final step is to add functionality to our Main Activty that loads and displays the JSON data.
* Labels are not included directly onto the UI, but for each object the data is displayed as id being the top element, listId as the middle element, and name as the bottom element.
