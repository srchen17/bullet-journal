[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)
<img width="1199" alt="GUI" src="https://github.com/CS-3500-OOD/pa05-ananya-stephanie-allison/assets/114843431/51dbec85-470f-47df-86ad-9a61b68774db">
<img width="1200" alt="Screen Shot 2023-06-21 at 2 26 07 PM" src="https://github.com/CS-3500-OOD/pa05-ananya-stephanie-allison/assets/114843431/d92d20b3-edde-4bfb-b9dc-daa3c8ee7f7b">

# Bullet Journal Pitch
This bullet journal program allows you to add weekly events and tasks, 
view them in a mini viewer, see statistics about your week, and even set a 
password. It's a great way to get to write down any quotes and notes you have,
and plan your week in a visually appealing way! You can pick a template for a bullet
journal to get started, then easily use a menubar to navigate adding 
tasks and events to your week. You can customize your week by setting
limits on tasks and events, and choosing the week start day. Furthermore, 
you can save your bullet journal to a file, and view a convenient task queue 
of all tasks left to be complete. 
This bullet journal has all the features to support easy planning every week! 


A full list of features includes:

A week view, where you can see all seven days of a week and associated events and tasks.
A menu in Edit to add events and tasks to certain days, with names, descriptions, and whether a task 
is complete. 
A way for a user to set a maximum number of tasks and events that a user can add, and 
a commitment warning when a user goes over that number.
A way to save and create a bullet journal file from a menu, and persist info about the 
journal in the files. 
A task queue / sidebar on the side of the week view, showing a list of all the 
tasks and if they're complete. 
A menu bar containing different operations on the bullet journal, such as 
adding tasks and events, saving, changing the start day of the week. 
A section where a user can write quotes and notes and persist them in the file. 
A section to show a weekly overview and statistics about the week: number tasks 
and number events, and percent tasks complete. 
Mini viewers for tasks and events, that will open new windows and show 
info about tasks and events (they will pop up when a task or event is clicked).
A way to change the week start date: by clicking the left/right arrows above 
the first day of the week, you can change the day the week starts.
Clickable links in commitments. 
A 'visual flourish' for the appearance of the bullet journal. 
A splash screen that shows at the beginning of the program, welcoming the 
user to the program. 
A privacy lock and way to set a password for a bullet journal. 
The ability to start a .bujo file from a template (weekly starters). 


## Keyboard shortcuts
Below is a total list of all keyboard shortcuts implemented for this applicaiton
- Ctrl/Cmd + N : Create new bullet journal
- Ctrl/Cmd + O : Open existing .bujo file
- Ctrl/Cmd + S : Save bullet journal
- Ctrl/Cmd + Shift + T : Open bullet journal from template
- Ctrl/Cmd + E : Add new event
- Ctrl/Cmd + T : Add new task
- Ctrl/Cmd + M : Set event/task maximums
- Left arrow key : Shift week starting day to the left
- Right arrow key : Shift week starting day to the right

# SOLID
Single Responsibility:
We utilized single responsibility by firstly, separating our files and classes 
into appropriate folders: model, view, and controller. We made sure that the 
files in model only dealt with updating the Week and information about the Week, 
that the controller only handled controlling what to do with user input and 
how to handle user events, and the View only focused on loading the view
the user sees. Class-wise, we used single responsibility by creating classes
that manage different parts of the bullet journal. For example, instead of 
having one class control all Dialogs, we created different classes implementing 
the same interface to split up the responsibility of controlling different
Dialogs, such as one class for an Add Event and one class for an Add Task Dialog. 

Open for extension, closed for modification: 
We applied the Open for extension, Closed for modification principle by utilizing 
interfaces in our program. By using these interfaces, instead of modifying existing 
code to make room for a new class, we could just create a class implementing 
an interface, and swap out an old implementation with a new implementation. 
For example, we made the Commitment interface, as a more general interface for 
tasks and for events. If we wanted to add a new Reminder class, we would not have to 
modify any of the existing code for parts of our project. Instead, we would just need
to create a new Reminder class, and have it implement Commitment. Then, any 
methods that take in a Commitment will still run with the new Reminder class, without
further modification, just extension. 

Liskov substitution principle: 
We applied the Liskov substitution principle by creating classes 
that have implementations of the interfaces, but do not include any extra 
public methods. This is important, so that objects of superclasses can
be substituted with objects of subclasses. For example, we created
a EntryDialogController interface with methods getName, getPassword,
and setSwap. Our classes implementing the EntryDialogController contain
only those methods, so that whenever an EntryDialogController is used, any 
of its subclasses can be substituted, since no other public methods 
are required for its full functionality. For example, if we wanted
to create a new way for users to enter and create a new bujo file, 
we could create a new class implementing EntryDialogController, then 
simply substitute it for EntryDialogController, and nothing should break. 

Interface segregation: 
We applied interface segregation when creating the interfaces for our 
controllers. For example, we created an IUploadController interface,
because it may be possible that we need to substitute in different 
classes that will upload different sets of days/times to a file, 
and because it would require different methods than the other controller
interfaces we already have. The IUploadController extends the EntryDialogController,
because we don't want classes implementing the EntryDialogController 
to be stuck with methods that only make sense for UploadControllers. 
Therefore, the UploadController class, which implements IUploadController,
only implements the methods it needs. 

Dependency inversion:
In our BujoControllerImpl constructor, we take in an IUploadController 
instead of an UploadController class. This is because we want a 
higher level class, the BujoControllerImpl, to depend on an abstraction,
and we also want the lower level class, UploadController, to depend on 
an abstraction. Because both BujoControllerImpl and UploadController depend on 
an interface, it allows for more flexibly (ex: in the future, if you
wanted to add a different type of upload controller, you could create 
a new class implementing the interface, and not have to change 
the code of BujoControllerImpl). 


# Extending the Program
One way we could extend our program to implement the Progress Bar feature would 
be to first, add the progress bars to the bujo.fxml file. We would need 
to make sure that our program had the necessary imports to support the 
progress bar. We would then create an interface for a progress bar controller,
(in case we want to display a different kind of progress bar in the future), and 
we would also create the ProgressBarController class, which implements the
above interface. We might create an observer class for the main BujoControllerImpl class, 
which the ProgressBarController would have a handle to. Then, whenever 
progress is made, a task is created or completed, etc., the ProgressBarController 
would be updated. In this way, we would be delegating any handling of the showing the
bars to the ProgressBarController. The ProgessBarController class might have a handle
to a list of tasks or events, or a list of completed tasks, and then have methods
that can use that information to send to the view. Its methods might include 
something like: updateProgressBar, checkIfProgressMade, and changeProgressColor. 

# Attributions
Open Bullet Journal image: https://www.istockphoto.com/vector/open-book-diary-with-bookmark-gm518037557-48997698
Closed Bullet Journal image: https://www.vectorstock.com/royalty-free-vector/brown-closed-the-book-with-a-red-bookmark-vector-3600722
Book opening video: https://www.youtube.com/watch?v=uVMJB3xOeZA
