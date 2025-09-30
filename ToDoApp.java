package Todoapp;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class ToDoApp extends JFrame {
    private DefaultListModel<String> model;
    private JList<String> taskList;
    private JTextField inputField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton clearButton;

    private static final String DATA_DIR = "data";
    private static final String TASK_FILE = DATA_DIR + File.separator + "tasks.txt";

    public ToDoApp() {
        super("To-Do App");
        initComponents();
        loadTasks();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(480, 420);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveTasks();
                dispose();
                System.exit(0);
            }
        });
    }

    private void initComponents() {
        
        model = new DefaultListModel<>();
        taskList = new JList<>(model);
        taskList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        taskList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(taskList);

        
        inputField = new JTextField();
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        addButton = new JButton("Add");
        addButton.setMnemonic(KeyEvent.VK_A);

        
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear All");

        
        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        bottomPanel.add(deleteButton);
        bottomPanel.add(clearButton);

        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(8, 8));
        cp.add(topPanel, BorderLayout.NORTH);
        cp.add(scrollPane, BorderLayout.CENTER);
        cp.add(bottomPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(e -> addTaskFromInput());
        inputField.addActionListener(e -> addTaskFromInput()); // Enter key adds

        deleteButton.addActionListener(e -> deleteSelectedTasks());
        clearButton.addActionListener(e -> clearAllTasks());

        
        InputMap im = taskList.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = taskList.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteTask");
        am.put("deleteTask", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTasks();
            }
        });

        
        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int idx = taskList.locationToIndex(e.getPoint());
                    if (idx >= 0) editTask(idx);
                }
            }
        });
    }

    private void addTaskFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        model.addElement(text);
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    private void deleteSelectedTasks() {
        int[] selected = taskList.getSelectedIndices();
        if (selected.length == 0) {
            JOptionPane.showMessageDialog(this, "Select at least one task to delete.", "No selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int resp = JOptionPane.showConfirmDialog(this, "Delete selected task(s)?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resp != JOptionPane.YES_OPTION) return;

        
        for (int i = selected.length - 1; i >= 0; i--) {
            model.removeElementAt(selected[i]);
        }
    }

    private void editTask(int index) {
        String current = model.getElementAt(index);
        String updated = JOptionPane.showInputDialog(this, "Edit task:", current);
        if (updated != null) {
            updated = updated.trim();
            if (!updated.isEmpty()) {
                model.set(index, updated);
            }
        }
    }

    private void clearAllTasks() {
        if (model.isEmpty()) return;
        int resp = JOptionPane.showConfirmDialog(this, "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            model.clear();
        }
    }

    
    private void loadTasks() {
        try {
            Path dir = Path.of(DATA_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path file = Path.of(TASK_FILE);
            if (!Files.exists(file)) {
                
                return;
            }
            List<String> lines = Files.readAllLines(file);
            for (String l : lines) {
                if (!l.isBlank()) model.addElement(l);
            }
        } catch (IOException e) {
            showError("Failed to load tasks: " + e.getMessage());
        }
    }

    
    private void saveTasks() {
        try {
            Path dir = Path.of(DATA_DIR);
            if (!Files.exists(dir)) Files.createDirectories(dir);
            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(TASK_FILE))) {
                for (int i = 0; i < model.size(); i++) {
                    writer.write(model.getElementAt(i));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            showError("Failed to save tasks: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoApp app = new ToDoApp();
            app.setVisible(true);
        });
    }
}

