/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Client.Client;
import LoginPage.LoginPage;

/**
 *
 * @author risha
 */


public class Main  {
    
     String Username ;
     String IP;
     String password;
     LoginPage loginpage;
     Client client;
     boolean LoginComplete;
     
     public Main()
     {
         LoginComplete = false;
         RunLoginPage();  
         
         
     }
     
     public void RunLoginPage()
     {
        loginpage = new LoginPage();
        loginpage.createLoginPage();
        this.Username = loginpage.Username;
        this.IP = loginpage.IP;    
        this.password = loginpage.password;
        client = new Client(Username,IP);
        
     }
     
     
    
    public static void main(String[] args)
    {
        
        new Main();
        
    }
    
}
