# User Guide

## Features 

### List out all the tasks
List out all the tasks so that the user can see 
1. all the tasks currently;
1. the completion status of each task; and 
1. the index of each task so to be used for `delete` or `done`.

### Add 3 different types of tasks
The 3 different types of tasks that users can add are `Todos`, `Deadlines` and `Events`.

Task | Use Case 
---- | --------
Todo | Normal Task 
DeadLine| Task to be done **by** certain time/date 
Event | Task happening **at** certain time/date 

### Delete a task from the list
Tasks that are no longer relevant can be deleted from the list so that the user can focus on
more important task.

### Mark a task as done
Mark tasks which are already completed as done so that you can keep track of what else needs to be done




## Usage

### `list` - Shows all the tasks in the list

Shows all the tasks that are currently in the list along with their completion status

Example of usage: 

`list`

Expected outcome:

```
-----------------------------------------------------------------------
Here are the tasks in your list:
1. [T][✘] clean room
2. [E][✘] CS lecture  (at: Thurs 1200)
3. [D][✓] CG submission  (by: Tues 2359)
-----------------------------------------------------------------------
```

### `todo` - Adds a new Todo task

Adds a new todo task to the list with the given description

Example of usage:

`todo run`

Expected outcome:
```
-----------------------------------------------------------------------
Got it. I've added this task:
  [T][✘] run
Now you have 4 tasks in the list
-----------------------------------------------------------------------
```

### `deadline` - Adds a new Deadline task 

Adds a new Deadline task to the list with the given description and date/time

Example of usage:
`deadline meeting preperation \by 30/09/2020 1000`

Expected outcome:

```
-----------------------------------------------------------------------
Got it. I've added this task:
  [D][✘] meeting preperation  (by: 30/09/2020 1000)
Now you have 5 tasks in the list
-----------------------------------------------------------------------
```

### `event` - Adds a new Event task

Adds a new Event task to the list with the given description and date/time

Example of usage:
`event CS2113 Tutorial /at 02/10/2020 1200`

Expected outcome:
```
-----------------------------------------------------------------------
Got it. I've added this task:
  [E][✘] CS2113 Tutorial  (at: 02/10/2020 1200)
Now you have 6 tasks in the list
-----------------------------------------------------------------------
```
### `delete` - Deletes a task from the list

Deletes a task from the list based on the given index of the task

Example of usage:
`delete 3`

Expected outcome:
```
-----------------------------------------------------------------------
Noted. I've removed this task:
  [D][✓] CG submission  (by: Tues 2359)
Now you have 5 tasks in the list
-----------------------------------------------------------------------
```


### `done` - Marks a task from the list as done

Marks an undone task from the list as done

Example of usage:
`done 5`

Expected outcome:
```
-----------------------------------------------------------------------
Nice! I've marked this task as done:
  [E][✓] CS2113 Tutorial  (at: 02/10/2020 1200)
-----------------------------------------------------------------------
```




