package service;

import controller.controller;

import javax.swing.*;
import java.awt.*;

//排行榜窗口
public class rankings extends JDialog {
    public rankings(controller frame) {
        super(frame, "排行榜", true);
        setLayout(new FlowLayout());
        setBounds(250, 250, 250, 250);
    }
}
