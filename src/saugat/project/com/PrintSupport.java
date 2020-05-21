/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saugat.project.com;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.RepaintManager;

/**
 *
 * @author yuyutsu
 */
public class PrintSupport {
    private Component print_component;
 
    public static void printComponent(Component c) {
        new PrintSupport(c).doPrint();
    }
 
    public PrintSupport(Component comp) {
        this.print_component = comp;
    }
 
    public void doPrint() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((Printable) this);
        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
        }
    }
 
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            disableDoubleBuffering(print_component);
            print_component.paint(g2d);
            enableDoubleBuffering(print_component);
            return (PAGE_EXISTS);
        }
    }
 
    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }
 
    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    } 
}
