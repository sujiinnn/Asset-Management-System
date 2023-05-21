/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.assetmanagementsystem.member_mgmt;

/**
 *
 * @author 이가연
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import deu.cse.assetmanagementsystem.admin.*;
import deu.cse.assetmanagementsystem.login.*;

/**
 *
 * @author 이가연
 */
public class member_mgmt extends JFrame {

    private DefaultListModel<String> memberListModel;
    private JList<String> memberList;

    public member_mgmt() {
        setTitle("회원 관리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        memberListModel = new DefaultListModel<>();
        memberList = new JList<>(memberListModel);

        JButton withdrawButton = new JButton("탈퇴");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = memberList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String selectedMember = memberListModel.getElementAt(selectedIndex);
                    String[] memberInfo = selectedMember.split(", ");
                    String memberId = memberInfo[0];
                    if (removeMember(memberId)) {
                        memberListModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(member_mgmt.this, "회원 탈퇴가 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(member_mgmt.this, "회원 탈퇴에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(member_mgmt.this, "회원을 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(memberList), BorderLayout.CENTER);
        panel.add(withdrawButton, BorderLayout.SOUTH);

        getContentPane().add(panel);
        pack();
        setVisible(true);

        loadMembers();
    }

    private boolean removeMember(String memberId) {
        List<String> members = loadMembers();
        if (members != null) {
            for (int i = 0; i < members.size(); i++) {
                String member = members.get(i);
                String[] memberInfo = member.split(", ");
                String existingMemberId = memberInfo[0];
                if (existingMemberId.equals(memberId)) {
                    members.remove(i);
                    return saveMembers(members);
                }
            }
        }
        return false;
    }

    private List<String> loadMembers() {
        List<String> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("members.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                members.add(line);
            }
            memberListModel.addAll(members);
            memberListModel.addElement("↖↖↖↖현재 회원 목록↖↖↖↖");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    private boolean saveMembers(List<String> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.txt"))) {
            for (String member : members) {
                writer.write(member);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}