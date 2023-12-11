public class Tasks {
    private String taskTitle;
    private int taskID;
    private String completionDate;
    
    public Tasks()
    {
        taskTitle = "";
        taskID = 0;
        completionDate = "";
    }
    
    public Tasks(int taskID,
                 String taskTitle,String completionDate)
    {
        this.taskTitle = taskTitle;
        this.taskID = taskID;
        this.completionDate = completionDate;
    }
    
    public Tasks(String taskTitle)
    {
        this.taskTitle = taskTitle;
    }
    
    public String getTaskTitle()
    {
        return taskTitle;
    }
    
    public int getTaskID()
    {
        return taskID;
    }
    
    public String getCompletionDate()
    {
        return completionDate;
    }
    
    public void setTaskTitle(String taskTitle)
    {
        this.taskTitle = taskTitle;
    }
    
    
}