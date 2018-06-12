/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenJar;

import burp.IBurpExtenderCallbacks;
import burp.ITab;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.table.DefaultTableModel;
import java.net.URI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JOptionPane;


/**
 *
 * @author DanNegrea
 */
public class Tab extends javax.swing.JPanel implements ITab, TableModelListener{    
    private final DefaultTableModel tableModel;
    private final DataModel dataModel;
    final IBurpExtenderCallbacks callbacks;
    private final PersistSettings persistSettings;

    
    /**
     * Creates new form Panel
     */
    public Tab(IBurpExtenderCallbacks callbacks) {
        initComponents();
        this.callbacks = callbacks;
        
        persistSettings = new PersistSettings(callbacks, 50);
        
        tableModel = (DefaultTableModel) tokenTable.getModel();        
        dataModel = new DataModel(tableModel, callbacks);
       
        //Restore table or put demo data
        this.restoreTableData(persistSettings.restore());       
        this.setStatusColor();
        
        //tokenTable.getColumnModel().removeColumn(null);  //in case I want to hide some columns
        
        //(re)Initialize dataModel
        dataModel.init();

        tokenTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        tableModel.addTableModelListener(this);
    }
    
    public DataModel getDataModel() {
        return dataModel;
    }
    public DefaultTableModel getTableModel(){
        return tableModel;
    }
    
    public PersistSettings getPersistSettings(){
        return persistSettings;
    }
    
    @Override
    public String getTabCaption() {
        return "Token Jar";
    }

    @Override
    public Component getUiComponent() {
        return this;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        //*DEBUG*/callbacks.printOutput("TableChanged() e.getType="+e.getType()+"  getFirstRow=: "+e.getFirstRow()+" getLastRow="+e.getLastRow()+"");
       
        int type = e.getType(); 
        int rowId = e.getFirstRow();
        int column = e.getColumn();
        
        //No line was updated or the table was dumped
        if (rowId==-1)
            return;
        
        //Just 'value' field has changed. Expected to be the only change during running
        if (column==6 || column==10)
            return;
            
        Object enable = tableModel.getValueAt(rowId, 0);
         
        //If UPDATE and not valid row then uncheck 'Enable'
        //w/o checking 'enable!=null && (boolean)enable' next time will run the function body again and again
        if (enable!=null && (boolean)enable && type==TableModelEvent.UPDATE && !dataModel.checkRow(rowId, true)){
            //*DEBUG*/callbacks.printOutput("row updated, but not valid");
            tableModel.setValueAt(false, rowId, 0);
        }
        
        //Any change triggers unchecking of Master Enable
        //masterEnable.setSelected(false);
        //dataModel.setMasterEnable(false);
        //this.setStatusColor();
                
        // Replacing Save button functionality with unchecking Enable
        /*
        if (type==TableModelEvent.UPDATE || type==TableModelEvent.DELETE ){
            //Don't call init, just make save button Red
            SaveTable.setForeground(Color.red);
        }
        */
    }
    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        goToSite = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tokenTable = new javax.swing.JTable();
        addToken = new javax.swing.JButton();
        removeToken = new javax.swing.JButton();
        openRegexWindow = new javax.swing.JButton();
        masterEnable = new javax.swing.JCheckBox();
        masterDebug = new javax.swing.JCheckBox();
        importConf = new javax.swing.JButton();
        exportConf = new javax.swing.JButton();
        statusColor = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Token Jar");

        jLabel2.setText("Manages tokens like anti-CSRF, CSurf, special session cookies, params with random values,...");

        jLabel4.setText("Set the param Name, where it's located (url, body, cookie, ...). Eval defines how is calculated based (or not) on regex results.");

        goToSite.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        goToSite.setForeground(new java.awt.Color(51, 0, 255));
        goToSite.setText("read more on plugin home page");
        goToSite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToSiteMouseClicked(evt);
            }
        });

        jLabel5.setText("Regex is used to extract relevant values from responses. Path can limit were Regex applies.");

        jLabel3.setText("Get started:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(208, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(goToSite)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(goToSite))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tokenTable.setAutoCreateRowSorter(true);
        tokenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enable", "Name", "url", "body", "cookie", "other", "Value", "Eval", "Regex", "Path", "Debug"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tokenTable.setColumnSelectionAllowed(true);
        tokenTable.setDragEnabled(true);
        jScrollPane2.setViewportView(tokenTable);
        tokenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tokenTable.getColumnModel().getColumnCount() > 0) {
            tokenTable.getColumnModel().getColumn(0).setMaxWidth(55);
            tokenTable.getColumnModel().getColumn(2).setMaxWidth(37);
            tokenTable.getColumnModel().getColumn(3).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(4).setMaxWidth(50);
            tokenTable.getColumnModel().getColumn(5).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(10).setMaxWidth(55);
        }

        addToken.setText("Add");
        addToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTokenActionPerformed(evt);
            }
        });

        removeToken.setText("Remove");
        removeToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTokenActionPerformed(evt);
            }
        });

        openRegexWindow.setText("Regex");
        openRegexWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRegexWindowActionPerformed(evt);
            }
        });

        masterEnable.setText("Enable");
        masterEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterEnableActionPerformed(evt);
            }
        });

        masterDebug.setText("Debug");
        masterDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterDebugActionPerformed(evt);
            }
        });

        importConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/import-25px.png"))); // NOI18N
        importConf.setToolTipText("Import configuration");
        importConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importConfActionPerformed(evt);
            }
        });

        exportConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export-25px.png"))); // NOI18N
        exportConf.setToolTipText("Export configuration");
        exportConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportConfActionPerformed(evt);
            }
        });

        statusColor.setEditable(false);
        statusColor.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(masterEnable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(masterDebug))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(removeToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(openRegexWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importConf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(exportConf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addToken, openRegexWindow, removeToken});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masterEnable)
                    .addComponent(masterDebug)
                    .addComponent(statusColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addToken)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeToken)
                        .addGap(49, 49, 49)
                        .addComponent(openRegexWindow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportConf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(importConf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void goToSiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToSiteMouseClicked
        // TODO add your handling code here:
        URI uri = URI.create("http://dannegrea.github.io/TokenJar");
        if (uri != null && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }//GEN-LAST:event_goToSiteMouseClicked

    private void addTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTokenActionPerformed
        //tableModel.addRow(new Object[]{ enable, name, url, body, cookie, other, value, eval, regex, path, debug });
        tableModel.addRow(new Object[]{ false, "", false, false, false, false, "", "grp[1]", "", "*", false });
        //no need to (re)init for an empty row
    }//GEN-LAST:event_addTokenActionPerformed

    private void removeTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTokenActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected line(s)?", "Warning", dialogButton);
        if(dialogResult == 0) { /*0 > Yes   1 > No */            
            int[] selectedRows = tokenTable.getSelectedRows();
            for (int i = 0; i < selectedRows.length; i++)
                tableModel.removeRow(selectedRows[i] - i);
        }
    }//GEN-LAST:event_removeTokenActionPerformed

    private void openRegexWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRegexWindowActionPerformed
        int selectedRow = tokenTable.getSelectedRow();                        
        RegexWindow window = new RegexWindow(this, selectedRow, callbacks);                
        window.setVisible(true);        
    }//GEN-LAST:event_openRegexWindowActionPerformed

    Object getCell(int row, int column){
        return tableModel.getValueAt(row, column);
    }
    boolean setCell(int row, int column, Object value){
        if (value==null) return false;        
        tableModel.setValueAt(value, row, column);
        return true;
    }
    
    private void masterEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterEnableActionPerformed
        //*DEBUG*/callbacks.printOutput("masterEnable..() | "+dataModel.getMasterEnable());         
        if(masterEnable.isSelected()){            
            Vector dataInTable = tableModel.getDataVector();
            persistSettings.save(dataInTable);        
            dataModel.init();
            
            dataModel.setMasterEnable(true);
        }else{
            dataModel.setMasterEnable(false);
        }
        this.setStatusColor();   
         //*DEBUG*/callbacks.printOutput("end masterEnable..() | "+dataModel.getMasterEnable());
    }//GEN-LAST:event_masterEnableActionPerformed

    private void masterDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterDebugActionPerformed
        if(masterDebug.isSelected()){
            dataModel.setMasterDebug(true);                
        }else{
            dataModel.setMasterDebug(false);
        }
        this.setStatusColor();
    }//GEN-LAST:event_masterDebugActionPerformed
    
    private void setStatusColor(){
        if(masterEnable.isSelected()){
            if(masterDebug.isSelected())
                statusColor.setBackground(Color.yellow);
            else
                statusColor.setBackground(Color.green);
        }else{
            statusColor.setBackground(Color.red);
        }    
        
    }
    
    private void exportConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportConfActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        switch (result) {
        case JFileChooser.APPROVE_OPTION:
            File file = fileChooser.getSelectedFile();
            try (
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            ){
                Vector dataInTable = tableModel.getDataVector();
                objectOut.writeObject(dataInTable);
            } catch (IOException ex) {
                PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                ex.printStackTrace(stderr);
            }
            break;
        case JFileChooser.CANCEL_OPTION:
          break;
        case JFileChooser.ERROR_OPTION:
          callbacks.printError("Error export error");
          break;
        }
    }//GEN-LAST:event_exportConfActionPerformed

    private void importConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importConfActionPerformed
        //*DEBUG*/callbacks.printOutput("importConfActionPerformed()");
        
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        /*DEBUG*/callbacks.printOutput("1");
        switch (result) {
        case JFileChooser.APPROVE_OPTION:
            File file = fileChooser.getSelectedFile();
            try (
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn); 
            ){    
                //*DEBUG*/callbacks.printOutput("2");
                restoreTableData((Vector) objectIn.readObject());
                //*DEBUG*/callbacks.printOutput("3");
                //dataModel.init(); //the Save buttons turns red, the init() will be called when the user saves
            } catch (IOException | ClassNotFoundException ex) {
                PrintWriter stderr = new PrintWriter(callbacks.getStderr());
                ex.printStackTrace(stderr);
            }
            break;
        case JFileChooser.CANCEL_OPTION:
          break;
        case JFileChooser.ERROR_OPTION:
          callbacks.printError("Error import error");
          break;
        }
        //*DEBUG*/callbacks.printOutput("end.");
    }//GEN-LAST:event_importConfActionPerformed

    private void restoreTableData(Vector dataInTable) {
        if (dataInTable==null) return;
        
         //Get the column names
        Vector<String> columnsInTable = new Vector<>(tableModel.getColumnCount());
        for (int i=0; i<tableModel.getColumnCount(); i++){
            columnsInTable.add(tableModel.getColumnName(i));
        }

        //restore the DataVector
        tableModel.setDataVector(dataInTable, columnsInTable);
        
        //setDataVector distroys these settings, I make them again
        tokenTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tokenTable.getColumnModel().getColumnCount() > 0) {
            tokenTable.getColumnModel().getColumn(0).setMaxWidth(55);
            tokenTable.getColumnModel().getColumn(2).setMaxWidth(37);
            tokenTable.getColumnModel().getColumn(3).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(4).setMaxWidth(50);
            tokenTable.getColumnModel().getColumn(5).setMaxWidth(40);
            tokenTable.getColumnModel().getColumn(10).setMaxWidth(55);
        }
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToken;
    private javax.swing.JButton exportConf;
    private javax.swing.JLabel goToSite;
    private javax.swing.JButton importConf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox masterDebug;
    private javax.swing.JCheckBox masterEnable;
    private javax.swing.JButton openRegexWindow;
    private javax.swing.JButton removeToken;
    private javax.swing.JTextField statusColor;
    private javax.swing.JTable tokenTable;
    // End of variables declaration//GEN-END:variables

}
