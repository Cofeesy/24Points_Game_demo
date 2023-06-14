package service;

import controller.controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//登录和注册
public class enroll extends JDialog{
        public enroll(controller frame) {
            super(frame, "登录/注册", true);
            Container container = getContentPane();

            //用户名框
            JLabel jLabel1 = new JLabel("用户名:");
            jLabel1.setBounds(20, 20, 60, 25);

            JTextField jTextField = new JTextField();
            jTextField.setBounds(120, 20, 100, 25);

            //密码框
            JLabel jLabel2 = new JLabel("密码:");
            jLabel2.setBounds(20, 60, 60, 25);

            JPasswordField jPasswordField = new JPasswordField();
            jPasswordField.setEchoChar('*');
            jPasswordField.setBounds(120, 60, 100, 25);

            //登录按钮
            JButton jButton = new JButton("登录");
            jButton.setBounds(90, 100, 120, 25);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller controller = new controller();
                    String name = jTextField.getText();
                    char ch[] = jPasswordField.getPassword();
                    String password = new String(ch);
                    if (dao.user.isuser(password, name)) {
                        dispose();
                        JOptionPane.showMessageDialog(null,
                                "登陆成功",
                                "登陆界面",
                                JOptionPane.INFORMATION_MESSAGE);
                        controller.dispose();
                        new controller();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "密码/账号错误",
                                "发生错误",
                                JOptionPane.ERROR_MESSAGE);
                        controller.dispose();
                    }
                }
            });

            //注册按钮
            JButton jButton1 = new JButton("注册");
            jButton1.setBounds(90, 140, 120, 25);
            jButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller controller = new controller();
                    String name = jTextField.getText();
                    char ch[] = jPasswordField.getPassword();
                    String password = new String(ch);
                    if (dao.user.isname(name)){
                        JOptionPane.showMessageDialog(null,
                                "用户已存在",
                                "发生错误",
                                JOptionPane.ERROR_MESSAGE);
                        controller.dispose();
                    }else {
                        dao.user.add(name, password, 0);
                        dispose();
                        JOptionPane.showMessageDialog(null,
                                "注册成功",
                                "成功注册新用户",
                                JOptionPane.INFORMATION_MESSAGE);
                        controller.dispose();
                        new controller();
                    }
                }
            });

            setLayout(null);
            container.add(jLabel1);
            container.add(jTextField);
            container.add(jLabel2);
            container.add(jPasswordField);
            container.add(jButton);
            container.add(jButton1);
            setBounds(250, 250, 250, 230);


        }
    }