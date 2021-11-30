//@author Devin Fish, Maria Staubach, Evan Wood, Riley Smith
//@version 11/30/2021

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
class Main {
 public static void main(String args[]) {
   
   SwingUtilities.invokeLater(new Runnable() {
     public void run() {
        new Game();
     }
   });
 }
} 