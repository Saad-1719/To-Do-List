public class Tasks
{
    private String taskTitle;
    private int taskID;
    private String completionDate;
private String addedTime;
    public Tasks()
    {
        taskTitle = "";
        taskID = 0;
        addedTime="";
        completionDate = "";
    }

    public String getAddedTime()
    {
        return addedTime;
    }

    public Tasks(int taskID, String taskTitle, String completionDate)
    {
        this.taskTitle = taskTitle;
        this.taskID = taskID;
        this.completionDate = completionDate;
    }
    public Tasks(int taskID,String taskTitle, String completionDate,String addedTime)
    {
        this.taskID=taskID;
        this.taskTitle = taskTitle;
        this.addedTime=addedTime;
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