# TODO-List
pet project
Самый первый ручной тест программы:
14:30:00: Executing ':Main.main()'...

> Task :compileJava
> Task :processResources NO-SOURCE
> Task :classes

> Task :Main.main()
Добро пожаловать в приложение TODO Лист!
Доступные команды:
addTask
showTasks
editTask
updateStatus
deleteTask
filterTasks
sortTasks
exit
addTask
Введите название таски: Java pet
Введите описание таски: Сделать пет проект по джаве
Введите дату YYYY-MM-DD: 2025-09-27
Таска успешно создана!
addTask
Введите название таски: Python pet
Введите описание таски: Сделать пет проект по питону
Введите дату YYYY-MM-DD: 2025-09-28
Таска успешно создана!
addTask
Введите название таски: C++
Введите описание таски: Не делать проект по с++
Введите дату YYYY-MM-DD: 2025-10-05
Таска успешно создана!
showTasks
Все задачи:
Task{id=3f890e56-8d16-4175-a1b2-fca43d67dd6b, title='Не делать проект по с++', description='C++', dueDate=2025-10-05, taskStatus=TODO}
Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}
Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=TODO}
editTask
Введите id таски: 3f890e56-8d16-4175-a1b2-fca43d67dd6b
Введите название: Делай!
Введите описание: делай!
Введите дату: 2025-09-25
Таска успешно изменена!
showTasks
Все задачи:
Task{id=3f890e56-8d16-4175-a1b2-fca43d67dd6b, title='Делай!', description='делай!', dueDate=2025-09-25, taskStatus=TODO}
Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}
Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=TODO}
updateStatus
Введите id таски: 049a2a74-4e8d-4c45-a44e-dfa8be944c03
Введите новый статус: IN_PROGRESS
Статус успешно обновлён!
Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}
deleteTask
Введите id таски: 3f890e56-8d16-4175-a1b2-fca43d67dd6b
Таска успешно удалена!
showTasks
Все задачи:
Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}
Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}
addTask
Введите название таски: Задача новая1
Введите описание таски: просто текст
Введите дату YYYY-MM-DD: 2025-09-30
Таска успешно создана!
addTask
Введите название таски: Задача новая 2
Введите описание таски: ne prosto text
Введите дату YYYY-MM-DD: 2025-11-09
Таска успешно создана!
filterTask
Введите статус для фильтра: IN_PROGRESS
[Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}]
sortTasks
Введите критерий для сортировки: Статус
[Task{id=4de13286-9aeb-4133-9b8a-79ee9ddc5411, title='ne prosto text', description='Задача новая 2', dueDate=2025-11-09, taskStatus=TODO}, Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}, Task{id=25ab7df2-96e4-41ae-a2e0-1f812c2ce304, title='просто текст', description='Задача новая1', dueDate=2025-09-30, taskStatus=TODO}, Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}]
[Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}, Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}, Task{id=25ab7df2-96e4-41ae-a2e0-1f812c2ce304, title='просто текст', description='Задача новая1', dueDate=2025-09-30, taskStatus=TODO}, Task{id=4de13286-9aeb-4133-9b8a-79ee9ddc5411, title='ne prosto text', description='Задача новая 2', dueDate=2025-11-09, taskStatus=TODO}]
sortTasks
Введите критерий для сортировки: Дата
[Task{id=049a2a74-4e8d-4c45-a44e-dfa8be944c03, title='Сделать пет проект по джаве', description='Java pet', dueDate=2025-09-27, taskStatus=IN_PROGRESS}, Task{id=1389e1a8-d2a8-4c1f-aeb4-2ce8ea89a2f8, title='Сделать пет проект по питону', description='Python pet', dueDate=2025-09-28, taskStatus=TODO}, Task{id=25ab7df2-96e4-41ae-a2e0-1f812c2ce304, title='просто текст', description='Задача новая1', dueDate=2025-09-30, taskStatus=TODO}, Task{id=4de13286-9aeb-4133-9b8a-79ee9ddc5411, title='ne prosto text', description='Задача новая 2', dueDate=2025-11-09, taskStatus=TODO}]
exit
До свидания!
[Incubating] Problems report is available at: file:///Users/vlad/IdeaProjects/TODO/build/reports/problems/problems-report.html

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.11/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 8m 48s
2 actionable tasks: 2 executed
14:38:49: Execution finished ':Main.main()'.
