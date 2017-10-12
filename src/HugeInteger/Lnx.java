package HugeInteger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by liuyin14 on 2016/12/14.
 */
public class Lnx extends JFrame {
//    JLabel label, label1, label2, label3;
//    JTextField textField, textField1, textField2, textField3;
//    JButton button;
    public static void main(String[] args) {
        Lnx lnx = new Lnx();
        lnx.go();
    }
    public void go(){
        setTitle("lnx");
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setOpaque(false);
        contentPane.setLayout(new GridLayout(4,1,20,20));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(5,5,5));
        JLabel label = new JLabel("     Please enter a Number x to calculate ln(x):");
        label.setFont(new Font("楷体",Font.ROMAN_BASELINE,30));
        JTextField textField = new JTextField(7);
        textField.setFont(label.getFont());



        JButton button = new JButton("calculate!");
        button.setFont(label.getFont());
        topPanel.add(label);
        topPanel.add(textField);
        topPanel.add(button);
        contentPane.add(topPanel);

        JPanel middlePanel = new JPanel(new FlowLayout());
        JLabel middleLabel = new JLabel("OK");
        middleLabel.setFont(label.getFont());
        middlePanel.add(middleLabel);
        contentPane.add(middlePanel);

        JPanel bottomPanel = new JPanel(new GridLayout(3,2,40,40));
        JLabel label1 = new JLabel("        Taylor:");
        JLabel label3 = new JLabel("        Integration:");
        JLabel label5 = new JLabel("        SeriesFraction:");
        JLabel label2 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label6 = new JLabel();
        label1.setFont(label.getFont());
        label2.setFont(label.getFont());
        label3.setFont(label.getFont());
        label4.setFont(label.getFont());
        label5.setFont(label.getFont());
        label6.setFont(label.getFont());
        bottomPanel.add(label1);
        bottomPanel.add(label2);
        bottomPanel.add(label3);
        bottomPanel.add(label4);
        bottomPanel.add(label5);
        bottomPanel.add(label6);
        contentPane.add(bottomPanel);

        JPanel lastPanel = new JPanel(new FlowLayout());
        JButton tuichu = new JButton("Exit");
        tuichu.setFont(label.getFont());
        lastPanel.add(tuichu);
        contentPane.add(lastPanel);

        button.addActionListener(e -> {
            String a = textField.getText();
            if(!checkInput(a)) middleLabel.setText("WRONG INPUT!");
            else {
                middleLabel.setText("OK!");
                HugeNumber jbn = new HugeNumber(a);
                HugeNumber b = jbn.lnTaylor(30).getFirstNBits(30);
                label2.setText(b.display());
                HugeNumber c = jbn.lnIntegration(120).getFirstNBits(30);
                label4.setText(c.display());
                HugeNumber d = jbn.lnSeriesFraction(7).getFirstNBits(30);
                label6.setText(d.display());
            }
        });

        tuichu.addActionListener(e ->{
            System.exit(0);
        });


        setVisible(true);
    }

    public boolean checkInput(String a){
        int n = a.length();
        char b = a.charAt(0);
        if(b != '+' && b!= '-' && (b < '0' || b > '9')) return false;
        int cnt = 0;
        for(int i = 1; i < n; ++ i){
            if(a.charAt(i) < '0' || a.charAt(i) > '9'){
                if(a.charAt(i) == '.') ++ cnt;
                else return false;
            }
        }
        if (cnt > 1) return  false;
        HugeNumber tmp = new HugeNumber(a);
        HugeNumber one = new HugeNumber("1");
        HugeNumber oneHundred = new HugeNumber("100");
        if(tmp.compareAbs(tmp, one) == -1 || tmp.compareAbs(tmp,oneHundred) == 1) return false;
        return true;
    }
}
