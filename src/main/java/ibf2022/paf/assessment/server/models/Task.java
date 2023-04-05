package ibf2022.paf.assessment.server.models;

import java.sql.Date;

// TODO: Task 4

public class Task {
    private Integer taskId;
    private String description;
    private Integer priority;
    private Date dueDate;
    private String user_id;
    
    public Integer getTaskId() {return taskId;}
    public void setTaskId(Integer taskId) {this.taskId = taskId;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Integer getPriority() {return priority;}
    public void setPriority(Integer priority) {this.priority = priority;}

    public Date getDueDate() {return dueDate;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

    public String getUser_id() {return user_id;}
    public void setUser_id(String user_id) {this.user_id = user_id;}

    @Override
    public String toString(){
        return "taskId: %d, Description: %s, priority: %d, dueDate:%s, user_id:%s"
				.formatted(taskId, description, priority, dueDate.toString(), user_id);
    }
}
