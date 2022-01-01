# ProjectManagementSite
Built a project management site using Java, HTML and JavaScript and connected it all using AWS tools.
For our project site, all the views are displayed on one single page. These views are made visible by performing actions.
The three main views available are Project view, Task view, and Team view. Within the Project view, the user can create 
projects and add tasks and teammates to the project. The Team view displays all the teammates on the project and
what task each teammate is working on. Within the Task view, the user can assign a teammate to a task, unnassign a 
teammate to a task, and mark a task as complete. On the task view, when the user first initially goes to add a task, 
the user also needs to enter in the task number. This is because it was simpler for the system to ask the user to input the 
task number rather than internally calculate the task ranks. Another quirk in our project it that it takes a bit of time 
to refresh the task list and the teammate list. Although these actions may take a little extra time to fully execute, 
they are still able to successfully complete the request. 
