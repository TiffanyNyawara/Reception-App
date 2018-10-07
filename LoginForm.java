
package reeption;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author triad
 */
public class LoginForm {
    //Java Objects declaration
    JFrame myframe = new JFrame("Login");
    JPanel mypanel = new JPanel();
    
    JLabel usernameLabel = new JLabel("UserName: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel titleLabel = new JLabel();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton loginBtn = new JButton("Login");
    JButton clearBtn = new JButton("Clear");
    
    //Use of Reeption class 
    Reeption loginData = new Reeption();
    ResultSet Data;

    String usernameSession = null;
    public LoginForm(){
        LoginGUI();
    }
    //LoginGUI set's up the GUI of the login form
    private void LoginGUI(){
        mypanel.setLayout(null);
        mypanel.add(titleLabel);
        mypanel.add(usernameLabel);
        mypanel.add(passwordLabel);
        mypanel.add(username);
        mypanel.add(password);
        mypanel.add(loginBtn);
        mypanel.add(clearBtn);
        
        
        Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
        titleLabel.setFont(myFont.deriveFont(35F));
        titleLabel.setText("LOGIN");
        titleLabel.setForeground(Color.red);
        titleLabel.setBounds(100, 20, 400, 50);
        
        usernameLabel.setBounds(40, 80, 100, 30);
        username.setBounds(120, 80, 200, 30);
        passwordLabel.setBounds(40, 140, 100, 30);
        password.setBounds(120, 140, 200, 30);
        loginBtn.setBounds(120, 200, 100, 30);
        clearBtn.setBounds(250, 200, 100, 30);
        myframe.add(mypanel);
        
        loginBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            loginBtnActionPerformed(evt);
        });
        clearBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            clearBtnActionPerformed(evt);
        });
        
        myframe.setSize(500, 300);
        myframe.setVisible(true);
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Button Actions
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt){
        String sql = "SELECT * FROM login";
        ResultSet rsx = null;
         try{
        Connection conn = loginData.connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        rsx = stmt.executeQuery();

        }catch(SQLException ex){

        }
        Data = rsx;
        try {
            while((Data.next())){
                String user = Data.getString("username");
                String pass = Data.getString("password");
                char passx[] = pass.toCharArray();
                
                if(user.equals(username.getText())) {
                    if(Arrays.equals(passx, password.getPassword()) == true){
                        JOptionPane.showMessageDialog(null, "Hello " + user +", Welcome to the ReceptionApp");
                        usernameSession = user;
                        Client test = new Client(user);
                    }
                    else{
                         JOptionPane.showMessageDialog(null,"Invalid Passord or Username","Alert",JOptionPane.WARNING_MESSAGE);     
                    }
                }else{JOptionPane.showMessageDialog(null,"Invalid Passord or Username","Alert",JOptionPane.WARNING_MESSAGE);  }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt){
        password.setText("");
        username.setText("");
    }
    
}
