package deu.cse.assetmanagementsystem.login;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

class signup extends JFrame implements ActionListener {

    JPanel panel;
    JLabel lblid, lblpw, lblname, lblpwcheck, lblemail, lbladdr;
    JButton b1, b2;
    JTextField txtid, txtname, txtemail, txtaddr;
    JPasswordField txtpw, txtpwch;

    signup() {
        setTitle("회원가입");
        setSize(500, 500 / 8 * 5);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        lblname = new JLabel("이름");
        lblid = new JLabel("ID");
        lblpw = new JLabel("비밀번호");
        lblpwcheck = new JLabel("비밀번호 확인");
        lblemail = new JLabel("EMAIL");
        lbladdr = new JLabel("주소");

        txtid = new JTextField(10);
        txtpw = new JPasswordField(10);
        txtpwch = new JPasswordField(10);
        txtname = new JTextField(10);
        txtemail = new JTextField(20);
        txtaddr = new JTextField(30);

        b1 = new JButton("가입");
        b2 = new JButton("지우기");

        panel.add(lblid);
        panel.add(txtid);
        panel.add(lblpw);
        panel.add(txtpw);
        panel.add(lblpwcheck);
        panel.add(txtpwch);
        panel.add(lblname);
        panel.add(txtname);
        panel.add(lblemail);
        panel.add(txtemail);
        panel.add(lbladdr);
        panel.add(txtaddr);
        panel.add(b1);
        panel.add(b2);

        add(panel);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String s = null;
            boolean isOk = false;
            BufferedWriter bw = new BufferedWriter(new FileWriter("members.txt", true));
            BufferedReader br = new BufferedReader(new FileReader("members.txt"));

            String txtid_string = txtid.getText();
            String txtpw_string = txtpw.getText();
            String txtpwch_string = txtpwch.getText();
            String txtname_string = txtname.getText();
            String txtemail_string = txtemail.getText();
            String txtaddr_string = txtaddr.getText();

            String password = txtpw.getText();
            String password_check = txtpwch.getText();

            // 공백, 빈칸, 비밀번호 예외처리
            if (txtid_string.contains(" ") || txtpw_string.contains(" ") || txtpwch_string.contains(" ") || txtname_string.contains(" ") || txtemail_string.contains(" ") || txtaddr_string.contains(" ") || txtid_string.isEmpty() || txtpw_string.isEmpty() || txtpwch_string.isEmpty() || txtname_string.isEmpty() || txtemail_string.isEmpty() || txtaddr_string.isEmpty()) {
                JOptionPane.showMessageDialog(null, "공백 및 빈칸이 존재합니다.");
            } else if (password.equals(password_check) == false) {
                JOptionPane.showMessageDialog(null, "비밀번호 불일치");
            } else if (e.getSource() == b1) {
                while ((s = br.readLine()) != null) {
                    String[] array = s.split("/");
                    if (array[0].equals(txtid.getText())) {
                        isOk = true;
                        break;
                    }
                }
                if (!isOk) {
                    if (txtid.getText().trim().length() == 0 || txtpw.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "ID와 비밀번호는 필수 입력 사항입니다.");
                    } else {
                        bw.write(txtid.getText() + "/");
                        bw.write(txtpw.getText() + "/");
                        bw.write(txtpwch.getText() + "/");
                        bw.write(txtname.getText() + "/");
                        bw.write(txtemail.getText() + "/");
                        bw.write(txtaddr.getText() + "\r\n");
                        bw.close();

                        JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
                        String folderName = txtid.getText();
                        File folder = new File(folderName);
                        if (!folder.exists()) {
                            folder.mkdir(); // 폴더 생성
                        }
                        dispose();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.");
                }

            } else if (e.getSource() == b2) {
                txtid.setText("");
                txtpw.setText("");
                txtpwch.setText("");
                txtname.setText("");
                txtemail.setText("");
                txtaddr.setText("");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "실패");
        }
    }

}
