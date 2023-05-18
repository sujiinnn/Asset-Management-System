package deu.cse.assetmanagementsystem.user;


import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddAccount extends JFrame {
    static String fName; //변수 선언
    public AddAccount() {
        JPanel p = new JPanel();

        //라벨 이름, 붙이기
        JLabel name = new JLabel("계좌이름");
        JLabel bank_name = new JLabel("은행");
        JLabel account = new JLabel("계좌번호");
        JLabel balance = new JLabel("현재잔액");

        add(name);
        add(bank_name);
        add(account);
        add(balance);

        //텍스트 필드, 붙이기
        TextField name_text = new TextField();
        TextField bank_name_text = new TextField();
        TextField account_text = new TextField();
        TextField balance_text = new TextField();

        add(name_text);
        add(bank_name_text);
        add(account_text);
        add(balance_text);

        //등록 버튼, 붙이기
        JButton save = new JButton("등록");
        JButton cancel = new JButton("취소");

        add(save);
        add(cancel);

        //라벨, 텍스트필드, 버튼 위치 지정
        name.setBounds(40, 10, 70, 40);
        bank_name.setBounds(40, 50, 70, 40);
        account.setBounds(40, 90, 70, 40);
        balance.setBounds(40, 130, 70, 40);

        name_text.setBounds(180, 10, 200, 40);
        bank_name_text.setBounds(180, 50, 200, 40);
        account_text.setBounds(180, 90, 200, 40);
        balance_text.setBounds(180, 130, 200, 40);

        save.setBounds(125, 330, 80, 30);
        cancel.setBounds(240, 330, 80, 30);

        //페널 설정
        add(p);
        setSize(500, 500);
        setTitle("계좌번호 등록");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //계좌 텍스트필드에 숫자만 입력 가능하게
        account_text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        balance_text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        //등록 시 이벤트처리

        save.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            // 텍스트 필드의 값을 String으로 가져오기
            String name = name_text.getText();
            String bnk_name = bank_name_text.getText();
            String acc = account_text.getText();
            String blc = balance_text.getText();
            int acc_blc = Integer.parseInt(blc);

            // 파일 이름을 계좌이름으로 지정하여 BufferedWriter 객체 생성
            String folderName = fName;
            String fileName = folderName + "/" + name + ".txt";

            File file = new File(fileName);
            if (file.exists()) {
                JOptionPane.showMessageDialog(null, "이미 존재하는 계좌이름입니다. 다른 이름을 입력해주세요.");
                return; // 중복된 계좌이름인 경우 함수를 종료하고 더 이상 진행하지 않음    
            }
            BufferedWriter bos = new BufferedWriter(new FileWriter(fileName, true));
            // 텍스트필드에 공백 포함시 예외처리
            // 공백이 없을 시 파일에 값 저장
            if (bnk_name.contains(" ") || acc.contains(" ") || bnk_name.isEmpty() || acc.isEmpty()) {
                JOptionPane.showMessageDialog(null, "은행명과 계좌번호에 공백이 포함되어서는 안됩니다.");
            } else {
                bos.write(bank_name_text.getText() + "/");
                bos.write(account_text.getText() + "/");
                bos.write(acc_blc + "\r\n");
                bos.close();
                JOptionPane.showMessageDialog(null, "계좌 등록 완료");
                // 다음 창
                dispose();
            }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "계좌 등록 실패");
                }
            }
    });
                }
    public static void setUser(String ID){
        fName = ID; //전역변수를 위한 함수선언
    }
}