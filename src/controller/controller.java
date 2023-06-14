package controller;

import imp.algorithm;
import service.enroll;
import service.rules;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static dao.user.*;

//项目
public class controller extends JFrame {
    
    public controller() {
        //连接算法
        algorithm algorithm = new algorithm();

        //主框架jFrame
        Container container = getContentPane();
        setBackground(Color.BLUE);
        setLayout(null);
        setTitle("24_Games");
        setBounds(200, 200, 500, 450);


        //显示用户名字
        JLabel jLabel1 = new JLabel("用户:" + nowuser);
        container.add(jLabel1);
        jLabel1.setBounds(10, 10, 80, 25);

        //显示分数
        JLabel jLabel2 = new JLabel("分数:" + score);
        container.add(jLabel2);
        jLabel2.setBounds(390, 10, 80, 25);

        //显示关卡
        JLabel jLabel3=new JLabel("第"+guanka+"关");
        container.add(jLabel3);
        jLabel3.setBounds(210,10,90,25);

        //游戏内容
        JTextArea jTextArea1 = new JTextArea();
        container.add(jTextArea1);
        jTextArea1.setFont(new Font("宋体",Font.PLAIN,12));
        jTextArea1.setBounds(10, 50, 460, 70);

        //扑克牌
        JPanel jPanel = new JPanel();
        container.add(jPanel);
        jPanel.setBounds(10,120,460,105);
        jPanel.setLayout(new GridLayout(1,4,5,5));

        JLabel j1 = new JLabel();
        Icon icon1 = new ImageIcon("src/images/bg.png");
        j1.setIcon(icon1);
        j1.setHorizontalAlignment(SwingConstants.CENTER);
        j1.setOpaque(true);

        JLabel j2 = new JLabel();
        Icon icon2 = new ImageIcon("src/images/bg.png");
        j2.setIcon(icon2);
        j2.setHorizontalAlignment(SwingConstants.CENTER);
        j2.setOpaque(true);

        JLabel j3 = new JLabel();
        Icon icon3 = new ImageIcon("src/images/bg.png");
        j3.setIcon(icon3);
        j3.setHorizontalAlignment(SwingConstants.CENTER);
        j3.setOpaque(true);

        JLabel j4 = new JLabel();
        Icon icon4 = new ImageIcon("src/images/bg.png");
        j4.setIcon(icon4);
        j4.setHorizontalAlignment(SwingConstants.CENTER);
        j4.setOpaque(true);

        jPanel.add(j1);
        jPanel.add(j2);
        jPanel.add(j3);
        jPanel.add(j4);


        //用户输入
        JTextArea jTextArea2 = new JTextArea();
        container.add(jTextArea2);
        jTextArea2.setFont(new Font("宋体",Font.PLAIN,12));
        jTextArea2.setBounds(10, 230, 460, 50);


        //提交按钮
        JButton jButton1 = new JButton("提交");
        container.add(jButton1);
        jButton1.setBounds(10, 285, 80, 40);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans = jTextArea2.getText();
                if (algorithm.isVecnum(ans)){
                    JOptionPane.showMessageDialog(null,
                            "你对了,你这关得了"+guanka*5+"分",
                            "成功进入下一关",
                            JOptionPane.INFORMATION_MESSAGE);
                    addgrade();
                    jLabel2.setText("分数:" + score);
                    guanka++;
                    jLabel3.setText("第"+guanka+"关");
                    jTextArea1.setText("");
                    jTextArea2.setText("");
                    jTextArea1.append(nowuser+"用户。现在是第"+guanka+"关。" +
                            "\n使用下面的牌,用加、减、乘、除四种方法,把给出的数算成24并写在下面的文本框中:\n");
                }else{
                    JOptionPane.showMessageDialog(null,
                            "你错了",
                            "返回第一关",
                            JOptionPane.ERROR_MESSAGE);
                    guanka=1;
                    jLabel3.setText("第"+guanka+"关");
                    jTextArea1.setText("");
                    jTextArea2.setText("");
                    jTextArea1.append(nowuser+"用户。上次你输了,现在变回到是第"+guanka+"关。" +
                            "\n使用下面的牌,用加、减、乘、除四种方法,把给出的数算成24并写在下面的文本框中:\n");
                }
                algorithm.clearvec();
                algorithm.startAlgorithm();
                jTextArea1.append(algorithm.getN(0)+"  "+algorithm.getN(1)+"  "+algorithm.getN(2)+"  "+algorithm.getN(3));

                Icon icon1 = new ImageIcon("src/images/"+algorithm.getN(0)+".png");
                j1.setIcon(icon1);
                j1.setHorizontalAlignment(SwingConstants.CENTER);
                j1.setOpaque(true);

                Icon icon2 = new ImageIcon("src/images/"+algorithm.getN(1)+".png");
                j2.setIcon(icon2);
                j2.setHorizontalAlignment(SwingConstants.CENTER);
                j2.setOpaque(true);

                Icon icon3 = new ImageIcon("src/images/"+algorithm.getN(2)+".png");
                j3.setIcon(icon3);
                j3.setHorizontalAlignment(SwingConstants.CENTER);
                j3.setOpaque(true);

                Icon icon4 = new ImageIcon("src/images/"+algorithm.getN(3)+".png");
                j4.setIcon(icon4);
                j4.setHorizontalAlignment(SwingConstants.CENTER);
                j4.setOpaque(true);

            }
        });

        //开始按钮
        JButton jButton = new JButton("开始游戏");
        container.add(jButton);
        jButton.setBounds(10, 340, 140, 40);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea1.setText("");
                jTextArea2.setText("");
                jTextArea1.append("你好！\n"+nowuser+"用户,游戏开始了。现在是第"+guanka+"关。" +
                        "\n使用下面的牌,用加、减、乘、除四种方法,把给出的数算成24并写在下面的文本框中:\n");
                algorithm.startAlgorithm();
                jTextArea1.append(algorithm.getN(0)+"  "+algorithm.getN(1)+"  "+algorithm.getN(2)+"  "+algorithm.getN(3));

                Icon icon1 = new ImageIcon("src/images/"+algorithm.getN(0)+".png");
                j1.setIcon(icon1);
                j1.setHorizontalAlignment(SwingConstants.CENTER);
                j1.setOpaque(true);

                Icon icon2 = new ImageIcon("src/images/"+algorithm.getN(1)+".png");
                j2.setIcon(icon2);
                j2.setHorizontalAlignment(SwingConstants.CENTER);
                j2.setOpaque(true);

                Icon icon3 = new ImageIcon("src/images/"+algorithm.getN(2)+".png");
                j3.setIcon(icon3);
                j3.setHorizontalAlignment(SwingConstants.CENTER);
                j3.setOpaque(true);

                Icon icon4 = new ImageIcon("src/images/"+algorithm.getN(3)+".png");
                j4.setIcon(icon4);
                j4.setHorizontalAlignment(SwingConstants.CENTER);
                j4.setOpaque(true);
            }
        });

        //登录和注册按钮
        JButton jButton2 = new JButton("登录/注册");
        container.add(jButton2);
        jButton2.setBounds(120, 285, 120, 40);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                enroll enroll = new enroll(controller.this);
                enroll.setVisible(true);
            }
        });

        //排行榜按钮
        JButton jButton3 = new JButton("排行榜");
        container.add(jButton3);
        jButton3.setBounds(260, 285, 80, 40);
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                controller controller = new controller();
                rankusers(controller);
            }
        });

        //规则按钮
        JButton jButton4 = new JButton("规则");
        container.add(jButton4);
        jButton4.setBounds(370, 285, 80, 40);
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rules rules = new rules(controller.this);
                rules.setVisible(true);
            }
        });

        //退出按钮
        JButton jButton5 = new JButton("退出");
        container.add(jButton5);
        jButton5.setBounds(370, 340, 80, 40);
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeConnection();
                dispose();
            }
        });

        this.getContentPane().setBackground(Color.orange);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //主函数
    public static void main(String[] args) {
        dao.user.initConnection();//开启数据库连接
        new controller();//启动项目

    }
}


