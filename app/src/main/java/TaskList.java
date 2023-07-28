/*import java.time.LocalDate;
import java.util.ArrayList;

public abstract class TaskList {
    private String name;
    private LocalDate date;
    private ArrayList<Task> tasks;

    public TaskList(String taskListName) {
        this.name = taskListName;
        this.date = LocalDate.now();
        this.tasks = new ArrayList<Task>();
    }

    public String getName() {
        return this.name;
    }
    public LocalDate getDate() {
        return this.date;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public abstract void addTask(String task);

    public String getTask(int n) {
        int len = this.tasks.length;
        if (n < len && n >= 0) {
            return this.tasks.get(n).getName();
        } else {
            return "None";
        }
    }

    public void setTask(int n, String newName) {
        int len = this.tasks.length;
        if (n < len && n >= 0) {
            this.tasks.get(n).setName(newName);
        }
    }

    public abstract void removeTask(int n);

    public void clearList() {
        this.tasks.clear();
    }

    @Override
    public String toString() {
        String answer = this.name + "\n" + this.date + "\n -------------------- \n";
        for (int i = 0; i < this.tasks.length; i++) {
            answer += toString(i + 1) + ". " + this.tasks.get(i).getName() + "\n";
        }
        return answer;
    }
}*/