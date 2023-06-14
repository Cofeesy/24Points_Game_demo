package service;

import controller.controller;
import javax.swing.*;
import java.awt.*;

//游戏规则窗口
public class rules extends JDialog{
        public rules(controller frame) {
            super(frame, "规则", true);
            Container container = getContentPane();

            JTextArea jTextArea = new JTextArea();
            container.add(jTextArea);
            jTextArea.append("本游戏为：24点" +
                    "\n排行榜为：历史排行榜" +
                    "\n具体规则如下：" +
                    "\n给出4个不同的数字（牌）,所给数字均为有整数 (1至13之间),用加、减、乘、" +
                    "\n除四种方法并且用（）将每一步计算步骤都套进去，把给出的数算成24,答对者会相应加分,连续答对会提高" +
                    "\n难度,相应的也会得到更高的分数,答错便会从第一关重新开始。所得分数会相应的加进当前账号的历史成绩"+
                    "\n中。列算式例如这样:((K-A)*4)/2");
            jTextArea.setFont(new Font("微软雅黑",Font.PLAIN,14));
            setLayout(new FlowLayout());
            setBounds(250, 250, 700, 240);
        }
    }

