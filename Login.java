public class Login extends Database
{
    
    public static boolean hosteller_login(String regno, String password)
    {
        boolean x=false;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=true;
                break;
            }
        }
        return x;
    }
    
    //To display name
    public static String getHostellerName(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=name[x];
        return s;
    }
    
    
    //To display registratin number
    public static String getRegistrationNumber(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=reg_no[x];
        return s;
    }
    
    //To display mess type
    public static String getMessType(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=mess_type[x];
        return s;
    }
    
    //To display room type
    public static String getRoomType(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=room_type[x];
        return s;
    }
    
    //To display hostel block
    public static String getHostelBlock(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=hostel_block[x];
        return s;
    }
    
    //To display degree
    public static String getDegree(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=degree[x];
        return s;
    }
    
    //To get phone number
    public static long getPhoneNumber(String regno, String password)
    {
        int x=0;
        long s=0;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=phone_number[x];
        return s;
    }
    
    //To display room number
    public static int getRoomNumber(String regno, String password)
    {
        int x=0;
        int s=0;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=room_no[x];
        return s;
    }
    
    //To get Laundry Number
    public static String getLaundrynumber(String regno, String password)
    {
        int x=0;
        String s;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]))
            {
                x=i;
                break;
            }
        }
        s=laundrynumber[x];
        return s;
    }
    
    //Change password
    public static boolean change_password(String regno, String password,String ol_pass,String newpass)
    {
        int x=-1;
        String[] updated_passwords = Database.passwords;
        boolean changed_pass=false;
        for(int i=0;i<5;i++)
        {
            if(regno.equalsIgnoreCase(reg_no[i]) && password.equalsIgnoreCase(passwords[i]) && ol_pass.equals(passwords[i]))
            {
                //x=i;
                updated_passwords[i]=newpass;
                changed_pass=true;
                break;
            }
        }
        setPasswords(updated_passwords);
        return changed_pass;
    }
    
    /*public static String[] getPasswords() {
        return Database.passwords;
    }*/
    
    public static void setPasswords(String[] password) {
        Database.passwords = password;
    }
    
    public static boolean admin_login(String adminno, String password)
    {
        boolean x=false;
        for(int i=0;i<5;i++)
        {
            if(adminno.equalsIgnoreCase(admin_username[i]) && password.equalsIgnoreCase(admin_passwords[i]))
            {
                x=true;
                break;
            }
        }
        return x;
    }
}