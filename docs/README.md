# Ava User Guide

Hello Im Ava! Nice to meet you! I am a simple, user-friendly task management application that helps you keep track of your tasks. You can add, mark, unmark, delete, and find tasks with ease.

## Adding Todos

Adding a new Todo task is quick and easy. Just use the `todo` command followed by the task description.

**Example usage**: `todo Buy groceries`

This command adds a new task with no specific deadline or event associated with it.

**Expected Outcome**:

```
Got it. I've added this task: 
    [T][ ] buy groceries
Now you have 1 task(s) in the list.
```


## Adding Deadlines

Deadlines allow you to specify a task that needs to be completed by a certain date.

**Example usage**: `deadline Submit assignment /by 2025-03-15`

The `/by` argument allows you to set the deadline for your task.

**Expected Outcome**:

```
Got it. I've added this task:
  [D][ ] submit assignment (by: 2025-03-1)
Now you have 2 task(s) in the list.
```

## Adding Events

Events allow you to specify tasks that are associated with a specific time.

**Example usage**: `event Attend meeting /from 2025-03-15 10:00 /to 2025-03-15 12:00`

The `/from` argument allows you to set the specific start time of the event.

The `/to` argument allows you to set the specific end time of the event.

**Expected Outcome**:

```
Got it. I've added this task:
  [E][ ] attend meeting (from: 2025-03-15 10:00 to: 2025-03-15 12:00)
Now you have 3 task(s) in the list.
```

## Displaying Task List

The `list` command shows all the tasks in your list, categorized by their status (completed or pending).

**Example usage**: `list`

All tasks will be displayed with their type (Todo, Deadline, Event) and their respective details.

**Expected Outcome**:
```
1: [T][X] buy groceries
2: [D][ ] submit assignment (by: 2025-03-1)
3: [E][ ] attend meeting (from: 2025-03-15 10:00 to: 2025-03-15 12:00)
```


## Marking Tasks as Done

Use the `mark` command to mark a task as completed. Select the task to mark based on its index in the list, or its description.

**Example usage**: `mark 1` or 'mark buy groceries'

This command updates task's status to "completed."

**Expected Outcome**:
```
Nice! I've marked this task as done:
[T][X] buy groceries
```

## Unmarking Tasks

The `unmark` command allows you to reverse the completion status of a task. Select the task to mark based on its index in the list, or its description.

**Example usage**: `unmark 1` or 'unmark buy groceries'

The task will now be considered as pending again.

**Expected Outcome**:
```
OK, I've marked this task as not done yet:
  [T][ ] buy groceries
```

## Deleting Tasks

If you want to remove a task from the list, use the `delete` command. Select the task to mark based on its index in the list, or its description.


**Example usage**: `delete 2` or 'delete submit assignment'

This command will delete the task at the specified index from the list.

**Expected Outcome**:
```
Got it. I've removed this task:
  [D][ ] submit assignment (by: 2025-03-1)
Now you have 2 task(s) in the list.
```

## Finding Tasks

Use the `find` command to search for tasks that contain a specific keyword.

**Example usage**: `find meeting`

This command will return all tasks that contain the keyword in their description.

**Expected Outcome**:
```
1: [E][ ] attend meeting (from: 2025-03-15 10:00 to: 2025-03-15 12:00)
```

## Exiting the Application

When you're done with your task management session, you can use the `bye` command to exit the application.

**Example usage**: `bye`

This will close the application.

**Expected Outcome**:
```
Bye. Hope to see you again soon!
```

## Summary of Commands

- `todo <task_description>` - Adds a new Todo task.
- `deadline <task_description> /by <date>` - Adds a new Deadline task.
- `event <task_description> /at <datetime>` - Adds a new Event task.
- `list` - Displays the current list of tasks.
- `mark <task_index>` - Marks a task as completed.
- `unmark <task_index>` - Marks a task as incomplete.
- `delete <task_index>` - Deletes a task from the list.
- `find <keyword>` - Finds tasks matching a keyword.
- `bye` - Exits the application.

That's all for my User Guide. Enjoy managing your tasks efficiently!
