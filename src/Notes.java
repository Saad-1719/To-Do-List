public class Notes
{
    private String notesName;
    private String notesDescription;
    
    public Notes(
            String notesName,
            String notesDescription
    )
    {
        this.notesName = notesName;
        this.notesDescription = notesDescription;
    }
    
    public String getNotesName()
    {
        return notesName;
    }
    
    public void setNotesName(String notesName)
    {
        this.notesName = notesName;
    }
    
    public String getNotesDescription()
    {
        return notesDescription;
    }
    
    public void setNotesDescription(String notesDescription)
    {
        this.notesDescription = notesDescription;
    }
}
