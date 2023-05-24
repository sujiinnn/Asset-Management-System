package deu.cse.assetmanagementsystem.EP_Decorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GUI extends JFrame {

    private EP_Component expenditureComponent;
    public JComboBox<String> categories;
    public JComboBox<String> EP_category;
    private JTable expenditureTable;
    private DefaultTableModel expenditureTableModel;
    private JButton deleteButton;

    public GUI(EP_Component expenditureComponent) {
        this.expenditureComponent = new EP_ConcreteDecorator(expenditureComponent);
        //로깅 기능을 추가한 데코레이터 생성
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("고정 지출/급여 내역 등록하기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        categories = new JComboBox();
        categories.setBounds(170, 30, 150, 30);
        categories.addItem("월세");
        categories.addItem("관리비");
        categories.addItem("월급");
        categories.addItem("교통비");
        categories.addItem("용돈");
        categories.addItem("공과금");
        categories.addItem("통신비");
        frame.add(categories);

        EP_category = new JComboBox();
        EP_category.setBounds(10, 30, 150, 30);
        EP_category.addItem("고정 지출");
        EP_category.addItem("급여 일정"); 
        frame.add(EP_category);

        deleteButton = new JButton("삭제");
        deleteButton.setBounds(160, 120, 100, 30);
        frame.add(deleteButton);

        expenditureTableModel = new DefaultTableModel();
        expenditureTableModel.addColumn("지출/급여");
        expenditureTableModel.addColumn("카테고리");
        expenditureTableModel.addColumn("금액");
        expenditureTable = new JTable(expenditureTableModel);
        JScrollPane tableScrollPane = new JScrollPane(expenditureTable);
        tableScrollPane.setBounds(320, 10, 300, 410);
        frame.add(tableScrollPane);

        expenditureTable.setDefaultEditor(Object.class, null); //테이블의 임의 수정을 막음

        JLabel label = new JLabel("금액 입력 : ");
        label.setBounds(10, 80, 200, 30);
        frame.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(100, 80, 150, 30);
        frame.add(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        JButton button = new JButton("등록");
        button.setBounds(40, 120, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = textField.getText();
                String category = (String) categories.getSelectedItem();
                String EP = (String) EP_category.getSelectedItem();
                expenditureComponent.registerExpenditure(amount, EP, category);
                textField.setText("");
                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "금액을 입력해 주세요.");
                } else {
                    expenditureTableModel.addRow(new Object[]{EP, category, amount});
                }
            }
        });

        // 삭제 버튼에 ActionListener 추가
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 선택된 행의 인덱스 가져오기
                int selectedRow = expenditureTable.getSelectedRow();

                // 선택된 행이 있는지 확인하고 삭제
                if (selectedRow != -1) {
                    // 테이블 모델에서 해당 인덱스의 행 삭제
                    expenditureTableModel.removeRow(selectedRow);

                    // 해당 값(텍스트 파일에서) 가져오기
                    String deletedValue = ""; // 삭제된 값을 저장할 변수

                    try {
                        // 텍스트 파일 읽기
                        BufferedReader reader = new BufferedReader(new FileReader("expenditure_log.txt"));
                        StringBuilder sb = new StringBuilder();
                        String line;

                        // 행 번호와 선택된 행의 인덱스 비교하여 해당 값을 가져오기
                        int rowNumber = 0;
                        while ((line = reader.readLine()) != null) {
                            if (rowNumber != selectedRow) {
                                sb.append(line).append(System.lineSeparator());
                            } else {
                                deletedValue = line;
                            }
                            rowNumber++;
                        }
                        reader.close();

                        // 삭제된 값을 제외한 나머지 내용으로 텍스트 파일 다시 쓰기
                        FileWriter writer = new FileWriter("expenditure_log.txt");
                        writer.write(sb.toString());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // 삭제된 값을 확인하거나 처리할 수 있습니다.
                    System.out.println("삭제 내역 -> " + deletedValue);
                }
            }
        });

        frame.add(button);

        frame.setSize(650, 500);
        frame.setVisible(true);

        EP_Component expenditureComponent = new EP_ConcreteComponent("expenditure_log.txt");
         
    }

}
