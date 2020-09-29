# User Guide

## Features 

### List out all the tasks
**List** out all the tasks so that the user can see 
1. all the tasks currently;
1. the completion status of each task; and 
1. the index of each task so to be used for `delete` or `done`.

<br>

### Add 3 different types of tasks
The 3 different types of tasks that users can **add** are `Todos`, `Deadlines` and `Events`.

Task | Use Case 
---- | --------
Todo | Normal Task 
DeadLine| Task to be done **by** certain time/date 
Event | Task happening **at** certain time/date 

<br>

### Delete a task from the list
Tasks that are no longer relevant can be **deleted** from the list so that the user can focus on
more important task.

<br>

### Mark a task as done
Mark tasks which are already completed as **done** so that you can keep track of what else needs to be done.

<br>

### Find tasks from list containing certain terms
**Find** tasks from list whose description contains the *search term*. This function is not case specific.

<br><br>

## Usage

### `bye` - Exit Duke

Exits Duke and ends the program.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

<br>

### `list` - Shows all the tasks in the list

Shows all the tasks that are currently in the list along with their completion status.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] clean room
2. [E][✘] CS lecture  (at: Thurs 1200)
3. [D][✓] CG submission  (by: Tues 2359)
```

Possible Errors:
```
:( Oh no! List is empty.
```
The list is empty so there are no tasks to be displayed.

<br>

### `todo` - Adds a new Todo task

Adds a new todo task to the list with the given description.

Example of usage:

`todo run`

Expected outcome:
```
Got it. I've added this task:
  [T][✘] run
Now you have 4 tasks in the list
```

Possible Errors:
```
:( Oh no! Todo must follow the format: todo <description>
```
The given _todo_ command does not follow the correct format.

<br>

### `deadline` - Adds a new Deadline task 

Adds a new Deadline task to the list with the given description and date/time

Example of usage:

`deadline meeting preperation \by 30/09/2020 1000`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] meeting preperation  (by: 30/09/2020 1000)
Now you have 5 tasks in the list
```

Possible Errors:
```
:( Oh no! Deadline must follow the format: <description> /by <time/date> 
```
The given _deadline_ command does not follow the correct format.

<br>

### `event` - Adds a new Event task

Adds a new Event task to the list with the given description and date/time

Example of usage:

`event CS2113 Tutorial /at 02/10/2020 1200`

Expected outcome:
```
Got it. I've added this task:
  [E][✘] CS2113 Tutorial  (at: 02/10/2020 1200)
Now you have 6 tasks in the list
```

Possible Errors:
```
:( Oh no! Event must follow the format: <description> /at <time/date> 
```
The given _event_ command does not follow the correct format.

<br>

### `delete` - Deletes a task from the list

Deletes a task from the list based on the given index of the task

Example of usage:

`delete 3`

Expected outcome:
```
Noted. I've removed this task:
  [D][✓] CG submission  (by: Tues 2359)
Now you have 5 tasks in the list
```

Possible Errors:
```
:( Oh no! Delete must follow the format: delete <index>
```
The given _delete_ command does not follow the correct format.

```
:( Oh no! The index given is out of the range of the number of tasks.
```
The given index is out of the range and not within the number of tasks in the list.

<br>

### `done` - Marks a task from the list as done

Marks an undone task from the list as done

Example of usage:

`done 5`

Expected outcome:
```
Nice! I've marked this task as done:
  [E][✓] CS2113 Tutorial  (at: 02/10/2020 1200)
```

Possible Errors:
```
:( Oh no! Done follow the format: done <index>
```
The given _done_ command does not follow the correct format.

```
:( Oh no! The index given is out of the range of the number of tasks.
```
The given index is out of the range and not within the number of tasks in the list.

```
:( Oh no! The task has already been completed.
```
The task at the given index has already been completed previously.

<br>

### `find` - Find tasks from list

Finds task from list whose description matches the search term

Example of usage:

`find cs`

Expected outcome:
```
Here are the tasks in your list that contains 'cs'
2. [E][✘] CS lecture  (at: Thurs 1200)
5. [E][✓] CS2113 Tutorial  (at: 02/10/2020 1200)
8. [E][✘] CS lab  (at: Fri 1000)
```

Possible Errors:
```
:( Oh no! The search term cannot be found.
```
The search term given cannot be found in any of the tasks' descriptions.
