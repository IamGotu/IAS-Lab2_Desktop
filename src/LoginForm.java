import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Mark John Jopia
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_lpassword = new javax.swing.JLabel();
        jLabel_lemail = new javax.swing.JLabel();
        jLabel_login = new javax.swing.JLabel();
        jLabel_register_link = new javax.swing.JLabel();
        textField_lemail = new java.awt.TextField();
        passwordField_lpassword = new javax.swing.JPasswordField();
        button_login = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_lpassword.setText("Password");

        jLabel_lemail.setText("Email");

        jLabel_login.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
        jLabel_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_login.setText("Login");

        jLabel_register_link.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_register_link.setText("Don't have an account? Click here to register.");
        jLabel_register_link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_register_linkMouseClicked(evt);
            }
        });

        textField_lemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_lemailActionPerformed(evt);
            }
        });

        passwordField_lpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordField_lpasswordActionPerformed(evt);
            }
        });

        button_login.setLabel("Login");
        button_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_register_link, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_lpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jLabel_lemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_login, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(textField_lemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField_lpassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel_login)
                .addGap(18, 18, 18)
                .addComponent(jLabel_lemail)
                .addGap(2, 2, 2)
                .addComponent(textField_lemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_lpassword)
                .addGap(2, 2, 2)
                .addComponent(passwordField_lpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_register_link, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_register_linkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_register_linkMouseClicked
        
        // Create an instance of RegisterForm
        RegisterForm registerForm = new RegisterForm();
        
        // Make the RegisterForm visible
        registerForm.setVisible(true);
        
        // Close the current form
        this.dispose();
    }//GEN-LAST:event_jLabel_register_linkMouseClicked

    private void textField_lemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_lemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_lemailActionPerformed

    private void passwordField_lpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordField_lpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordField_lpasswordActionPerformed

    private void button_loginActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the email and password from the text fields
        String email = textField_lemail.getText();
        String password = passwordField_lpassword.getText();
    
        if (!email.isEmpty() && !password.isEmpty()) {
            // Call the FirebaseAuth.loginUser method
            JSONObject response = FirebaseAuth.loginUser(email, password);
            if (response != null) {
                // Login successful
                String idToken = response.getString("idToken"); // Get the ID token from the response
    
                // Fetch user data
                JSONObject userData = FirebaseAuth.getUserData(idToken);
                if (userData != null) {
                    boolean emailVerified = userData.getJSONArray("users").getJSONObject(0).getBoolean("emailVerified");
                    if (emailVerified) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        // Open the DashboardForm and pass the user's email
                        DashboardForm dashboardForm = new DashboardForm(email);
                        dashboardForm.setVisible(true);
                        this.dispose(); // Close the current login form
                    } else {
                        JOptionPane.showMessageDialog(this, "Please verify your email before logging in.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to fetch user data. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Please check your email and password.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Email and password cannot be empty!");
        }
    }//GEN-LAST:event_button_loginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button_login;
    private javax.swing.JLabel jLabel_lemail;
    private javax.swing.JLabel jLabel_login;
    private javax.swing.JLabel jLabel_lpassword;
    private javax.swing.JLabel jLabel_register_link;
    private java.awt.TextField textField_lemail;
    private javax.swing.JPasswordField passwordField_lpassword;
    // End of variables declaration//GEN-END:variables
}
