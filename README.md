# ToDoApp  

✅ **Java Swing To-Do App**  

📌 **Objective**  

A simple To-Do List Application built with Java Swing.  
This project demonstrates GUI programming, event handling, persistence, and file I/O in Java.  

---

🚀 **Features**  

1.➕ Add Task → Add tasks via text field or Enter key  

2.❌ Delete Task → Remove selected tasks (button or Delete key)  

3.🧹 Clear All → Remove all tasks with confirmation  

4.✏️ Edit Task → Double-click a task to update it  

5.💾 Persistence → Tasks saved in data/tasks.txt and reloaded on next run  

6.🎛️ Keyboard Shortcuts → Enter to add, Delete to remove  

---

🛠️ **Tech Stack**  

Language: Java (JDK 17+)  

GUI Toolkit: Swing  

IDE: Eclipse (or IntelliJ / VS Code)  

Persistence: File I/O (tasks.txt)  

---

📂 **Project Structure (Eclipse)**  
ToDoApp/  
 ├── .classpath  
 ├── .project  
 ├── src/  
 │    └── Todoapp/  
 │          └── ToDoApp.java  
 ├── data/  
 │    └── tasks.txt   # persisted tasks (auto-created)  
 └── README.md  

 ---

 ▶️ **How to Run**  
 Run in Eclipse  

1.Create a new Java Project ToDoApp.  

2.Create package todoapp.  

3.Add ToDoApp.java.  

4.Run → Run As → Java Application.  

---

📝 **Sample UI**  
 **------------------------------------------------------  
|  [ Enter task here...               ] [ Add ]        |  
|------------------------------------------------------|  
| - Buy groceries                                       |  
| - Finish Java project                                 |  
| - Prepare for interview                               |  
|                                                      |  
|                                                      |  
|                                                      |  
|------------------------------------------------------|  
|             [ Delete ]   [ Clear All ]                |  
 ------------------------------------------------------**  

 ---

 🎯 **Learning Outcomes**  

1.Build a GUI application using Java Swing  

2.Implement event handling (buttons, key bindings, mouse double-click)  

3.Manage tasks with JList + DefaultListModel  

4.Implement file persistence with BufferedWriter and BufferedReader  

5.Practice modular code with methods for add, delete, edit, clear, load, save  

---

🔮 **Future Improvements**  

1.Add checkboxes for completed tasks  

2.Add priority & due dates with a custom Task object  

3.Export tasks to CSV/JSON  

4.Migrate GUI to JavaFX for modern design  

