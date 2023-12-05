public class Activity
{
    private String name, description, achievement, goal, progressBar;
    private int completedDays;

    public Activity()
    {
        name = "";
        description = "";
        goal = "";
        completedDays = 0;
        progressBar = "";
    }

    public Activity(String name, String achievement)
    {
        this.name = name;
        this.achievement = achievement;
    }

    public Activity(String name, String description, String goal, int completedDays, String progressBar)
    {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.completedDays = completedDays;
        this.progressBar = progressBar;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAchievement()
    {
        return achievement;
    }

    public String getGoal()
    {
        return goal;
    }

    public void setGoal(String goal)
    {
        this.goal = goal;
    }

    public int getCompletedDays()
    {
        return completedDays;
    }

    public void setCompletedDays(int completedDays)
    {
        this.completedDays = completedDays;
    }

    public String getProgressBar()
    {
        return progressBar;
    }

    public void setProgressBar(String progressBar)
    {
        this.progressBar = progressBar;
    }

}