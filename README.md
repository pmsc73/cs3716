cs3716
======
To run this project, open in eclipse, and run the file src/gui/View.java as an application. 

You will be presented with the initial interface. Select a course from the dropdown list, the number of students in that class will then be displayed below it. You can then select your preferred group size in the drownbox list below that. You can select any group size from 1 to n-1 where n is the number of students in the class. The final step is to choose how you want the groups be sorted. You can choose to sort them by GPA where the system will do its best to balance the groups by minimizing the standard deviation between the mean GPA of each group. Or you can choose to sort by preferences and specify which students MUST work together and which students CANNOT work together. When you are finished entering the project information, click the "Start Group Project" button.

Preferences Screen: In this screen you are presented with a complete list of students in the class. You can select multiple students at one time and select from the dropdown box "must work together" or "cannot work together" and click submit. Your preferences will be shown in the text area below. When you are finished entering your preferences, click the "next" button.

Group List Screen: In this screen you will be shown all the generated groups. Clicking on a group in the left panel will display all its members in the panel on the right. To move a student from one group to another: click on their name. select the group you want to move them to from the dropdown list below and click on the move button. The student will be moved to that group. If the group is currently full it will still move the student into that group but will present a warning that the group is full. When you are satisfied with the current groups, click the "finalize" button.

Finalized Groups Screen: In this final screen you are shown the finalized list of groups and their members. The groups are also outputted to a text file which can be viewed later.



