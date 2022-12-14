
# Routine Randomizer

The main task of the application is to issue random tasks from a list pre-created by the user.
At the moment, the user can add an unlimited number of tasks. The maximum number of characters is 45. The main screen displays a button for getting a task, as well as current tasks (if they exist).
A user can have up to 3 active tasks at the same time. If there are no tasks or too many active tasks, a corresponding notification will be displayed. To decline a task, the user must watch a short commercial.
Having completed the task, the user marks it in the column on the main page. After that, the task goes into hibernation for one day (the user cannot select a sleeping or active task).
After the sleep time expires, the task is automatically switched to the active mode. Various sleep times will be added in future updates. The design of the application will also be changed.

## Tech Stack
- language - Kotlin
- database - Room
- navigation - Compose navigation componet
- dependency injection - Hilt
- RecyclerView with DiffUtill
- test - JUnit
- coroutines + LiveData

## Screenshots

![Main page](https://yapx.ru/album/VLWB3)
![Add new task](https://yapx.ru/album/VLWFQ)
![Get random task](https://yapx.ru/album/VLWJG)

