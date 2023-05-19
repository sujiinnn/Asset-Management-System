package deu.cse.assetmanagementsystem.category_Factory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author 이가연
 */
public class CategoryManager_gui extends JFrame {
    
    private JComboBox<CategoryFactory_creator> comboBox;
    private JComboBox<Category_product> registeredComboBox;
    
    public CategoryManager_gui() {
        // GUI 구성 요소 초기화
        comboBox = new JComboBox<>();
        registeredComboBox = new JComboBox<>();
        JButton registerButton = new JButton("등록");
        JButton deleteButton = new JButton("삭제");
        JButton renewal_category = new JButton("카테고리 갱신");

        // 팩토리 메소드로 카테고리 추가
        comboBox.addItem(new FoodCategoryFactory_cc());
        comboBox.addItem(new ShoppingCategoryFactory_cc());
        comboBox.addItem(new SavingCategoryFactory_cc());
        comboBox.addItem(new BeautyCategoryFactory_cc());
        comboBox.addItem(new CulturallifeCategoryFactory_cc());
        comboBox.addItem(new LivingCategoryFactory_cc());
        comboBox.addItem(new MedicalCategoryFactory_cc());
        comboBox.addItem(new TransportationCategoryFactory_cc());

        // 등록 버튼 이벤트 처리
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //콤보박스에서 카테고리를 선택 -> 새로운 콤보박스에 카테고리 등록
                CategoryFactory_creator selectedFactory = comboBox.getItemAt(comboBox.getSelectedIndex());
                Category_product category = selectedFactory.createCategory();

                //중복으로 등록되었는지 검사 후 카테고리 등록
                boolean isAlreadyRegistered = false;
                for (int i = 0; i < registeredComboBox.getItemCount(); i++) {
                    Category_product registeredCategory = registeredComboBox.getItemAt(i);
                    if (registeredCategory.getName().equals(category.getName())) {
                        isAlreadyRegistered = true;
                        break;
                    }
                }
                
                if (isAlreadyRegistered) {
                    JOptionPane.showMessageDialog(CategoryManager_gui.this, "이미 등록된 카테고리입니다.", "중복 등록", JOptionPane.WARNING_MESSAGE);
                } else {
                    registeredComboBox.addItem(category);  //선택한 카테고리 등록
                }
                
            }
        });

        // 삭제 버튼 이벤트 처리
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category_product selectedCategory = registeredComboBox.getItemAt(registeredComboBox.getSelectedIndex());
                registeredComboBox.removeItem(selectedCategory);
            }
        });
        
        renewal_category.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    BufferedWriter a = new BufferedWriter(new FileWriter("addedcategory.txt", true));
                    for (int i = 0; i < registeredComboBox.getItemCount(); i++) {
                        a.write(registeredComboBox.getItemAt(i) + "/");
                    }
                    a.write("\n");
                    a.close();
                    JOptionPane.showMessageDialog(null, "카테고리 갱신 완료~");
                    //dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "갱신 실패");
                }
            }
        });

        // 프레임에 구성 요소 추가
        JPanel panel = new JPanel(new FlowLayout());
        
        panel.add(comboBox);
        
        panel.add(registerButton);
        
        panel.add(registeredComboBox);
        
        panel.add(deleteButton);
        
        panel.add(renewal_category);
        
        add(panel);
        
        setTitle("카테고리 관리");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
}
