/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.assetmanagementsystem.admin;

/**
 *
 * @author 이가연
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import deu.cse.assetmanagementsystem.category_Factory.*;

public class admin extends JFrame {

    public admin() {
        JPanel p = new JPanel(new FlowLayout());

        JButton AddCategory = new JButton("카테고리 등록하기");
        JButton MemberMGMT = new JButton("회원 관리");

        p.add(AddCategory);
        p.add(MemberMGMT);

        add(p);
        setSize(500, 500);
        setTitle("관리자 모드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

         AddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //카테고리 추가
                new CategoryManager_gui();
            }
        });

          MemberMGMT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원정보관리
            }
        });
    }
}