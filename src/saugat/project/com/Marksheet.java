/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saugat.project.com;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author yuyutsu
 */
public class Marksheet extends javax.swing.JFrame {
    
    public Marksheet() {
        initComponents();
        Getvalue();
        // /Get_all_data_from_excel(file_Path);     
        if(transfer_type.equals("Print all")){
            Get_all_data_from_excel(file_Path);
            int i;
            BufferedImage[] allasimage = new BufferedImage[1000];
            for (i=0;i<10;i++){   
                Set_data_to_pannel(i);
                allasimage[i] = generateimage();             
            }
            for(i=0;i<10;i++){
                try {
                    ImageIO.write(allasimage[i], "png", new File("/home/yuyutsu/",String.valueOf(i)));
                } catch (IOException ex) {
                    Logger.getLogger(Marksheet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }        
        else if (transfer_type.equals("Print")){
            Get_single_data_from_excel(required_id, file_Path);
            Set_data_to_pannel(0);
            Save("/home/yuyutsu/",String.valueOf(required_id));
            JOptionPane.showMessageDialog(this,"File saved.");
            }    
        else if (transfer_type.equals("Save")){
            Get_single_data_from_excel(required_id, file_Path);
            Set_data_to_pannel(0);
            Save("/home/yuyutsu/",String.valueOf(required_id));
            JOptionPane.showMessageDialog(this,"File saved.");
            }    
        else{
            JOptionPane.showMessageDialog(this,"If you can see this message, system may is not working."); 
            System.exit(0);
        }
    }
    String transfer_type="null";
    int required_id=-1;
    String file_Path="null";
    private void Getvalue(){
     //getting value from home_form
     try{
     file_Path=Home_Form.jLabel1.getText();
     String required_id_tem=Home_Form.jLabel9.getText();
     transfer_type=Home_Form.jLabel10.getText();
     //if print all option is coosed, required_id is not required
     if (transfer_type.equals("Print all")){
       required_id = -1;  
       JOptionPane.showMessageDialog(rootPane,"Hello");
     }
     else{
        try {required_id = Integer.parseInt(required_id_tem);}
        catch (NumberFormatException e){JOptionPane.showMessageDialog(this,"Selected  S.N. is incorrect. Please check if data format inside file is correct ot not.");}
     }
     }
     catch(NullPointerException e){
        JOptionPane.showMessageDialog(this,"Error getting data.");
        System.exit(0);
     }
    }        
    
    String class_name="";
    String[] sn = new String[1000];
    String[] symbol=new String[1000];
    String[] student_name=new String[1000];
    
    String sub1="";
    String[] sub1_th=new String[1000];
    String[] sub1_pr=new String[1000];
    String[] sub1_gr=new String[1000];
    String[] sub1_gp=new String[1000];
    
    String sub2="";
    String[] sub2_th=new String[1000];
    String[] sub2_pr=new String[1000];
    String[] sub2_gr=new String[1000];
    String[] sub2_gp=new String[1000];
    
    String sub3= "";
    String[] sub3_th=new String[1000];
    String[] sub3_pr=new String[1000];
    String[] sub3_gr=new String[1000];
    String[] sub3_gp=new String[1000];
    
    String sub4="";
    String[] sub4_th=new String[1000];
    String[] sub4_pr=new String[1000];
    String[] sub4_gr=new String[1000];
    String[] sub4_gp=new String[1000];
    
    String sub5="";
    String[] sub5_th=new String[1000];
    String[] sub5_pr=new String[1000];
    String[] sub5_gr=new String[1000];
    String[] sub5_gp=new String[1000];
    
    String sub6="";
    String[] sub6_th=new String[1000];
    String[] sub6_pr=new String[1000];
    String[] sub6_gr=new String[1000];
    String[] sub6_gp=new String[1000];
    
    String sub7="";
    String[] sub7_th=new String[1000];
    String[] sub7_pr=new String[1000];
    String[] sub7_gr=new String[1000];
    String[] sub7_gp=new String[1000];
    
    String sub8="";
    String[] sub8_th=new String[1000];
    String[] sub8_pr=new String[1000];
    String[] sub8_gr=new String[1000];
    String[] sub8_gp=new String[1000];
    
    String sub9="";
    String[] sub9_th=new String[1000];
    String[] sub9_pr=new String[1000];
    String[] sub9_gr=new String[1000];
    String[] sub9_gp=new String[1000];
    
    String[] total_gpa = new String[1000];
    String[] dob_bs = new String[1000];
    String[] dob_ad = new String[1000];
    String[] father_name = new String[1000];
    String[] mother_name= new String[1000];
    String[] address = new String[1000];
    //variable for  excel data
    //variable for accessing excel data 
    
    private static FileInputStream fis;
    private static HSSFWorkbook wb;
    private static HSSFSheet sh;
    
    //Getting data
    
    private void Get_all_data_from_excel(String file_path){
        try{ 
            File f = new File(file_path);
            fis=new FileInputStream(f);
            wb=new HSSFWorkbook(fis);
            DataFormatter df = new DataFormatter();
            sh = wb.getSheet("Sheet1");
            sh = wb.getSheetAt(0); //0 - index of 1st sheet
            
            //getting subject name
            Row r2=sh.getRow(1);
            //subject 1
            Cell cD2 = r2.getCell(3);
            sub1=df.formatCellValue(cD2);
            //subject 2
            Cell cK2 = r2.getCell(10);
            sub2=df.formatCellValue(cK2);
            //subject 3
            Cell cR2 = r2.getCell(17);
            sub3=df.formatCellValue(cR2);
            //subject 4
            Cell cY2 = r2.getCell(24);
            sub4=df.formatCellValue(cY2);
            //subject 5
            Cell cAF2 = r2.getCell(31);
            sub5=df.formatCellValue(cAF2);
            //subject 6
            Cell cAM2 = r2.getCell(38);
            sub6=df.formatCellValue(cAM2);
            //subject 7
            Cell cAT2 = r2.getCell(45);
            sub7=df.formatCellValue(cAT2);
            //subject 8
            Cell cBA2 = r2.getCell(52);
            sub8=df.formatCellValue(cBA2);
            //subject 9
            Cell cBH2 = r2.getCell(59);
            sub9=df.formatCellValue(cBH2);

            //getting student data only 
            int i=0;//for inserting value
            int j=3;//for setting g=first row in excell
            sn[0]="0";
            for (j=3;j<10/*!sn[i].equals("")*/;j++){              
                i++;
                Row r1=sh.getRow(j);
                //for S.N.
                Cell cA4=r1.getCell(0);
                sn[i] = df.formatCellValue(cA4);
                
                // for symbol no
                Cell cB4=r1.getCell(1);
                symbol[i] = df.formatCellValue(cB4);
                //for student name
                Cell cC4=r1.getCell(2);
                student_name[i] = df.formatCellValue(cC4);

                //for subject 1:
                Cell cE4= r1.getCell(4);
                sub1_th[i]= df.formatCellValue(cE4);
            
                Cell cG4= r1.getCell(6);
                sub1_pr[i]= df.formatCellValue(cG4);
            
                Cell cI4= r1.getCell(8);
                sub1_gr[i]= df.formatCellValue(cI4);
            
                Cell cJ4= r1.getCell(9);
                sub1_gp[i]= df.formatCellValue(cJ4);
                
                //for subject 2:
                Cell cL4= r1.getCell(11);
                sub2_th[i]= df.formatCellValue(cL4);
            
                Cell cN4= r1.getCell(13);
                sub2_pr[i]= df.formatCellValue(cN4);
            
                Cell cP4= r1.getCell(15);
                sub2_gr[i]= df.formatCellValue(cP4);
            
                Cell cQ4= r1.getCell(16);
                sub2_gp[i]= df.formatCellValue(cQ4);
                
                //for subject 3:
                Cell cS4= r1.getCell(18);
                sub3_th[i]= df.formatCellValue(cS4);
            
                Cell cU4= r1.getCell(20);
                sub3_pr[i]= df.formatCellValue(cU4);
            
                Cell cW4= r1.getCell(22);
                sub3_gr[i]= df.formatCellValue(cW4);
            
                Cell cX4= r1.getCell(23);
                sub3_gp[i]= df.formatCellValue(cX4);
                
                //for subject 4:
                Cell cZ4= r1.getCell(25);
                sub4_th[i]= df.formatCellValue(cE4);
            
                Cell cAB4= r1.getCell(27);
                sub4_pr[i]= df.formatCellValue(cAB4);
            
                Cell cAD4= r1.getCell(29);
                sub4_gr[i]= df.formatCellValue(cAD4);
            
                Cell cAE4= r1.getCell(30);
                sub4_gp[i]= df.formatCellValue(cAE4);
                
                //for subject 5:
                Cell cAG4= r1.getCell(32);
                sub5_th[i]= df.formatCellValue(cAG4);
            
                Cell cAI4= r1.getCell(34);
                sub5_pr[i]= df.formatCellValue(cAI4);
            
                Cell cAK4= r1.getCell(36);
                sub5_gr[i]= df.formatCellValue(cAK4);
            
                Cell cAL4= r1.getCell(37);
                sub5_gp[i]= df.formatCellValue(cAL4);
                
                //for subject 6:
                Cell cAN4= r1.getCell(39);
                sub6_th[i]= df.formatCellValue(cAN4);
            
                Cell cAP4= r1.getCell(41);
                sub6_pr[i]= df.formatCellValue(cAP4);
            
                Cell cAR4= r1.getCell(43);
                sub6_gr[i]= df.formatCellValue(cAR4);
            
                Cell cAS4= r1.getCell(44);
                sub6_gp[i]= df.formatCellValue(cAS4);
                
                //for subject 7:
                Cell cAU4= r1.getCell(46);
                sub7_th[i]= df.formatCellValue(cAU4);
            
                Cell cAW4= r1.getCell(48);
                sub7_pr[i]= df.formatCellValue(cAW4);
            
                Cell cAY4= r1.getCell(50);
                sub7_gr[i]= df.formatCellValue(cAY4);
            
                Cell cAZ4= r1.getCell(51);
                sub7_gp[i]= df.formatCellValue(cAZ4);
                
                //for subject 8:
                Cell cBB4= r1.getCell(53);
                sub8_th[i]= df.formatCellValue(cBB4);
            
                Cell cBD4= r1.getCell(55);
                sub8_pr[i]= df.formatCellValue(cBD4);
            
                Cell cBF4= r1.getCell(57);
                sub8_gr[i]= df.formatCellValue(cBF4);
            
                Cell cBG4= r1.getCell(58);
                sub8_gp[i]= df.formatCellValue(cBG4);
                
                //for subject 9:
                Cell cBI4= r1.getCell(60);
                sub9_th[i]= df.formatCellValue(cBI4);
            
                Cell cBK4= r1.getCell(62);
                sub9_pr[i]= df.formatCellValue(cBK4);
            
                Cell cBM4= r1.getCell(64);
                sub9_gr[i]= df.formatCellValue(cBM4);
            
                Cell cBN4= r1.getCell(65);
                sub9_gp[i]= df.formatCellValue(cBN4);
                
                //for total gpa
                Cell cBO4= r1.getCell(66);
                total_gpa[i]= df.formatCellValue(cBO4);
                
                //for dob_bs
                Cell cBP4= r1.getCell(67);
                dob_bs[i]= df.formatCellValue(cBP4);
           
                //for dob_ad
                Cell cBQ4= r1.getCell(68);
                dob_ad[i]= df.formatCellValue(cBQ4);
            
                //father name
                Cell cBR4= r1.getCell(69);
                father_name[i]= df.formatCellValue(cBR4);
            
                //mother name
                Cell cBS4= r1.getCell(70);
                mother_name[i]= df.formatCellValue(cBS4);
            
                //address
                Cell cBT4= r1.getCell(71);
                address[i]= df.formatCellValue(cBT4);  
            }  
        }catch (IOException e){System.out.println(e.getMessage());}
   }
    private void Get_single_data_from_excel( int required_id, String file_path){
            try{File f = new File(file_path);
            fis=new FileInputStream(f);
            wb=new HSSFWorkbook(fis);
            DataFormatter df = new DataFormatter();
            sh = wb.getSheet("Sheet1");
            sh = wb.getSheetAt(0);//0 - index of 1st sheet           
            Row r2=sh.getRow(1);//row for gettign subject data
            //subject 1
            Cell cD2 = r2.getCell(3);
            sub1=df.formatCellValue(cD2);
            //subject 2
            Cell cK2 = r2.getCell(10);
            sub2=df.formatCellValue(cK2);
            //subject 3
            Cell cR2 = r2.getCell(17);
            sub3=df.formatCellValue(cR2);
            //subject 4
            Cell cY2 = r2.getCell(24);
            sub4=df.formatCellValue(cY2);
            //subject 5
            Cell cAF2 = r2.getCell(31);
            sub5=df.formatCellValue(cAF2);
            //subject 6
            Cell cAM2 = r2.getCell(38);
            sub6=df.formatCellValue(cAM2);
            //subject 7
            Cell cAT2 = r2.getCell(45);
            sub7=df.formatCellValue(cAT2);
            //subject 8
            Cell cBA2 = r2.getCell(52);
            sub8=df.formatCellValue(cBA2);
            //subject 9
            Cell cBH2 = r2.getCell(59);
            sub9=df.formatCellValue(cBH2);
            //getting student data
            int i=0;//for inserting value
            int j=required_id +2;//for setting required row
            sn[0]="0";
                Row r1=sh.getRow(j);
                //for S.N.
                Cell cA4=r1.getCell(0);
                sn[i] = df.formatCellValue(cA4);
                
                // for symbol no
                Cell cB4=r1.getCell(1);
                symbol[i] = df.formatCellValue(cB4);
                //for student name
                Cell cC4=r1.getCell(2);
                student_name[i] = df.formatCellValue(cC4);

                //for subject 1:
                Cell cE4= r1.getCell(4);
                sub1_th[i]= df.formatCellValue(cE4);
            
                Cell cG4= r1.getCell(6);
                sub1_pr[i]= df.formatCellValue(cG4);
            
                Cell cI4= r1.getCell(8);
                sub1_gr[i]= df.formatCellValue(cI4);
            
                Cell cJ4= r1.getCell(9);
                sub1_gp[i]= df.formatCellValue(cJ4);
                
                //for subject 2:
                Cell cL4= r1.getCell(11);
                sub2_th[i]= df.formatCellValue(cL4);
            
                Cell cN4= r1.getCell(13);
                sub2_pr[i]= df.formatCellValue(cN4);
            
                Cell cP4= r1.getCell(15);
                sub2_gr[i]= df.formatCellValue(cP4);
            
                Cell cQ4= r1.getCell(16);
                sub2_gp[i]= df.formatCellValue(cQ4);
                
                //for subject 3:
                Cell cS4= r1.getCell(18);
                sub3_th[i]= df.formatCellValue(cS4);
            
                Cell cU4= r1.getCell(20);
                sub3_pr[i]= df.formatCellValue(cU4);
            
                Cell cW4= r1.getCell(22);
                sub3_gr[i]= df.formatCellValue(cW4);
            
                Cell cX4= r1.getCell(23);
                sub3_gp[i]= df.formatCellValue(cX4);
                
                //for subject 4:
                Cell cZ4= r1.getCell(25);
                sub4_th[i]= df.formatCellValue(cE4);
            
                Cell cAB4= r1.getCell(27);
                sub4_pr[i]= df.formatCellValue(cAB4);
            
                Cell cAD4= r1.getCell(29);
                sub4_gr[i]= df.formatCellValue(cAD4);
            
                Cell cAE4= r1.getCell(30);
                sub4_gp[i]= df.formatCellValue(cAE4);
                
                //for subject 5:
                Cell cAG4= r1.getCell(32);
                sub5_th[i]= df.formatCellValue(cAG4);
            
                Cell cAI4= r1.getCell(34);
                sub5_pr[i]= df.formatCellValue(cAI4);
            
                Cell cAK4= r1.getCell(36);
                sub5_gr[i]= df.formatCellValue(cAK4);
            
                Cell cAL4= r1.getCell(37);
                sub5_gp[i]= df.formatCellValue(cAL4);
                
                //for subject 6:
                Cell cAN4= r1.getCell(39);
                sub6_th[i]= df.formatCellValue(cAN4);
            
                Cell cAP4= r1.getCell(41);
                sub6_pr[i]= df.formatCellValue(cAP4);
            
                Cell cAR4= r1.getCell(43);
                sub6_gr[i]= df.formatCellValue(cAR4);
            
                Cell cAS4= r1.getCell(44);
                sub6_gp[i]= df.formatCellValue(cAS4);
                
                //for subject 7:
                Cell cAU4= r1.getCell(46);
                sub7_th[i]= df.formatCellValue(cAU4);
            
                Cell cAW4= r1.getCell(48);
                sub7_pr[i]= df.formatCellValue(cAW4);
            
                Cell cAY4= r1.getCell(50);
                sub7_gr[i]= df.formatCellValue(cAY4);
            
                Cell cAZ4= r1.getCell(51);
                sub7_gp[i]= df.formatCellValue(cAZ4);
                
                //for subject 8:
                Cell cBB4= r1.getCell(53);
                sub8_th[i]= df.formatCellValue(cBB4);
            
                Cell cBD4= r1.getCell(55);
                sub8_pr[i]= df.formatCellValue(cBD4);
            
                Cell cBF4= r1.getCell(57);
                sub8_gr[i]= df.formatCellValue(cBF4);
            
                Cell cBG4= r1.getCell(58);
                sub8_gp[i]= df.formatCellValue(cBG4);
                
                //for subject 9:
                Cell cBI4= r1.getCell(60);
                sub9_th[i]= df.formatCellValue(cBI4);
            
                Cell cBK4= r1.getCell(62);
                sub9_pr[i]= df.formatCellValue(cBK4);
            
                Cell cBM4= r1.getCell(64);
                sub9_gr[i]= df.formatCellValue(cBM4);
            
                Cell cBN4= r1.getCell(65);
                sub9_gp[i]= df.formatCellValue(cBN4);
                
                //for total gpa
                Cell cBO4= r1.getCell(66);
                total_gpa[i]= df.formatCellValue(cBO4);
                
                //for dob_bs
                Cell cBP4= r1.getCell(67);
                dob_bs[i]= df.formatCellValue(cBP4);
           
                //for dob_ad
                Cell cBQ4= r1.getCell(68);
                dob_ad[i]= df.formatCellValue(cBQ4);
            
                //father name
                Cell cBR4= r1.getCell(69);
                father_name[i]= df.formatCellValue(cBR4);
            
                //mother name
                Cell cBS4= r1.getCell(70);
                mother_name[i]= df.formatCellValue(cBS4);
            
                //address
                Cell cBT4= r1.getCell(71);
                address[i]= df.formatCellValue(cBT4);
            }
            catch (IOException e){System.out.println(e.getMessage());}
    }
    private void Set_data_to_pannel(int i){
        jLabel1.setText(student_name[i]);    
        jLabel2.setText(sn[i]);
    }
    public BufferedImage generateimage(){   
     Rectangle r = getBounds();
        BufferedImage i = new BufferedImage(r.width,r.height,BufferedImage.TYPE_INT_ARGB);
        Graphics g = i.createGraphics();//getGraphics();
        jPanel1.printAll(g);
        return i ;
    }
    
    public void Save(String Path,String FileName) {
        try {
            Rectangle r = getBounds();
            BufferedImage i = new BufferedImage(r.width,r.height,BufferedImage.TYPE_INT_ARGB);
            Graphics g = i.createGraphics();//getGraphics();
            jPanel1.printAll(g);
            ImageIO.write(i, "png", new File(Path , FileName));
        } catch (IOException ex) {
            Logger.getLogger(Marksheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(739, 1040));
        setMinimumSize(new java.awt.Dimension(730, 1040));
        setSize(new java.awt.Dimension(739, 1040));
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);

        jLabel1.setText("jLabel3");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 290, 260);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Get_all_data_from_excel(file_Path);
        //JOptionPane.showMessageDialog(this, "lol");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Marksheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Marksheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Marksheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Marksheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Marksheet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
