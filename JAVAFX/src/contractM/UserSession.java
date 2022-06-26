package contractM;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public final class UserSession
{
    
    private static UserSession instance;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    
    private String userName;
    private Date getDateLogin;
    
    private UserSession(String userName, Date getDateLogin)
    {
        this.userName = userName;
        this.getDateLogin = getDateLogin;
    }
    
    public static UserSession getInstance(String userName, Date getDateLogin)
    {
        
        instance = new UserSession(userName, getDateLogin);
        
        return instance;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public Date getDate()
    {
        return getDateLogin;
    }
    
    public void cleanUserSession()
    {
        userName = null;// or null
    }
    
    @Override
    public String toString()
    {
        if (userName.length() < 7)
            return "     Welcome " + userName + "\n" + formatter.format(getDateLogin);
        else
            return "  Welcome " + userName + "\n" + formatter.format(getDateLogin);
            
    }
}
