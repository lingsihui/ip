# User Guide
DUKE is a task records management that can help you to keep track of
the tasks you have. Duke enables users to categorise the type of task 
into three different types: Todo, Deadline and Event; show the full list
of task, specific list of task according to date; find a specific task; 
mark or delete a specific task.This application uses a command line 
interface; this means that you operate the application by typing commands 
into a Command Box. 
* Features:
    * Add tasks into list: `event` `todo` `deadline`
    * Delete task: `delete`
    * Show tasks on a specific date `date`
    * Mark task as done `done`
    * Find tasks in the list `find`
    * Show all the tasks in the list `list`
    * Exit Duke `bye`

## Features 
This document shows you the basic features in DUKE, add, 
delete, find, date, done, list and exit commands. 
Note the following format used in this document:
<p>
e.g. `Command` 
A grey highlight (mark-up) indicates that this 
is a command that can be typed into the command line and
executed by the application. 
</p>
<p>

e.g. _**Important Information**_
The Bolded and italic words indicates important information.
</p>
<p> UPPER_CASE
The uppers words indicates the parameters to be 
supplied by the user. 

e.g. `todo DESCRIPTION, `DESCRIPTION' is a parameter 
which can be used as `todo read book`. 
</p>

###  Add tasks into list - `event` `todo` `deadline` 
This command adds a task to the task list according to the
type of command. 
<p>
format: 

`todo DESCRIPTION`,
`deadline DESCRIPTION /by DATE`,
`event DESCRIPTION /at DATE`

</p>
<p>
Example of usage: 

* `todo read book`,
* `deadline return book /by Monday`,
* `event meeting /at Sunday`,

Expected outcomes:

 ```
  Got it. I've added this task:
 	[T][✘] read book
 Now you have 1 task in the list.
 ```
   ```
   Got it. I've added this task:
 	[D][✘] return book  (by: Monday)
 Now you have 2 task in the list.
 ```
  ```
   Got it. I've added this task:
 	[E][✘] meeting  (at: Sunday)
 Now you have 3 task in the list.
 ```
 </p>
 
 _**Important tips**_ : 
 * For Event and Delete tasks: Accept dates in a format 
 yyyy-mm-dd format and print in a different format 
 MMM dd yyy. If date is not valid, error message
 will be shown : 
    * e.g. : `event meeting /at 2019-10-15`, 
     outcome: 
    ```
   Got it. I've added this task:
    	[E][✘] meeting  (at: Oct 15 2019)
    Now you have 3 task in the list.
   ```
    * e.g : `event meeting /at 2019-10-113`
    outcome: 
    ```
   OOPS! Invalid Date Input
    Input Date in this format YYYY-MM-DD
   ```
 * If any tasks have missing description, 
 an error message will be shown.:
    * e.g. : `event /at`, `todo`, `deadline /by`
    outcome : 
    ```
    OOPS! Description cannot be empty!
   ```
 * If event task has a missing /at, or a deadline task has 
 a missing /by: 
    * e.g. : `event` , `deadline`
    outcome : 
    ```
    OOPS! Invalid Event Input
   ``` 
   ```
   OOPS! Invalid Deadline Input
   ```
 
### Delete task - `delete`
This command deletes a task in the task list.
<p>
format: 

`delete TASK_NUMBER`,
</p>
Task number is according to the task number of the task in the list. 
<p>
Example of usage: 

* `delete 2` 
Expected outcomes:

* ```
  Noted I have removed this task!
   	[D][✓] return book  (by: Monday)
   Now you have 2 task in the list.
  ```
 </p>
 
 _**Important tips**_ : 
 * Invalid task to be deleted includes: 
 Missing task Number to be deleted,task Number is not a number and task Number parameter 
 larger than task size.  
    * e.g. : `done`, 
     outcome: 
    ```
    OOPS! Invalid task to Delete!
    ```
    
### Show tasks on a specific date - `date`
This command shows the deadline tasks and event tasks 
that is happening on the specified date.
<p>
format: 

`date yyy-MM-dd`,
</p>
Example of usage: 

* `date 2019-10-15` 

* Expected outcomes:
```
Here are the tasks in your list:
   1. [E][✘] meeting  (at: Oct 15 2019)
   2. [D][✘] meeting  (by: Oct 15 2019)
```
 
 _**Important tips**_ : 
* Date format has to be correct. 
    * e.g : `date 2019-10-113`
    outcome: 
    ```
    OOPS! Invalid Date Input
    Input Date in this format YYYY-MM-DD
    ```
* Shows that the list is empty when there is no 
event task or deadline task happening/ due on the date.:
    * outcome: 
    ```
    Your list is empty! :0
    ```
    
### Mark task as done - `done`
This command marks a task in the task list as done.
<p>
format: 

`done TASK_NUMBER`,
</p>
Task number is according to the task number of the task in the list. 
<p>
Example of usage: 

* `done 2` 
Expected outcomes:

* ```
  Nice! I've marked this task as done:
   	[D][✓] return book  (by: Monday)
  ```
 </p>
 
 _**Important tips**_ : 
 * Invalid task to be marked includes: 
 Missing task Number to be marked,task Number is not a number, and task Number parameter 
 larger than task size.  
    * e.g. : `mark 10`, task list size = 9
     outcome: 
    ```
    OOPS! Invalid task to Mark!
    ```
    
### Find tasks in the list - `find`
This command finds the specific task according to the user input.
<p>
format: 

`find PARAMETR_TO_FIND`,
</p>
Example of usage: 

* `find book` 

* Expected outcomes:
```
Here are the matching tasks in your list:
 1. [T][✘] read book
```
 
 _**Important tips**_ : 
* If there is no matching object as user input, 
it shows error message :
    * outcome: 
    ```
    OOPS! I cant find what you are looking for :(
    ```

### Show all the tasks in the list - `list`
This command shows all the task in the list.
<p>
format: 

`list`,
</p>
Example of usage: 

* `list` 

* Expected outcomes:
```
Here are the tasks in your list:
 1. [T][✘] read book
 2. [D][✓] return book  (by: Monday)
 3. [E][✘] meeting  (at: Sunday)
```
 
 _**Important tips**_ : 
* If there is no task in your list, 
it shows error message :
    * outcome: 
    ```
    Your list is empty! :0
    ```
    
### Exit Duke  Command
This command exit DUKE.
<p>
format: 

`bye`
</p>
Example of usage: 

* `bye` 

* Expected outcomes:
```
Bye. Hope to see you again soon!
```
 
    