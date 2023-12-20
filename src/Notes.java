public class Notes
{
    private String notesName;
    private int notesID;
    private String addedDate;
    private String notesDescription;
    
    public Notes()
    {
        notesName="";
        notesDescription="";
        notesID=0;
        addedDate ="";
    }
    
    public Notes(int notesID,
                 String notesName, String notesDescription,String addedDate)
    {
        this.notesName = notesName;
        this.notesID = notesID;
        this.addedDate = addedDate;
        this.notesDescription = notesDescription;
    }
    
    public Notes(String notesName, String notesDescription)
    {
        this.notesName = notesName;
        this.notesDescription = notesDescription;
    }
    public Notes(int notesID, String notesName, String notesDescription)
    {
        this.notesID=notesID;
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
    
    public int getNotesID()
    {
        return notesID;
    }
    
    public String getAddedDate()
    {
        return addedDate;
    }
    
    public void setNotesDescription(String notesDescription)
    {
        this.notesDescription = notesDescription;
    }
}
