/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.Controller;
import Model.Musica;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import View.InterfaceCurtidas;
import View.InterfaceDescurtidas;
import javax.swing.Icon;
import javax.swing.JTextField;

/**
 *
 * @author unifjbarreto
 */
public class InterfaceMenu extends javax.swing.JFrame {

    private int idUsuario;
    private String nomeUsuario;
    private Controller controller;

    // Construtor correto que recebe o nome do usuário
    public InterfaceMenu(Controller controller) {
        this.controller = controller;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelResultados = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnPlaylists = new javax.swing.JButton();
        btnCurtidas = new javax.swing.JButton();
        btnDescurtidas = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        btnBuscarMusica = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout painelResultadosLayout = new javax.swing.GroupLayout(painelResultados);
        painelResultados.setLayout(painelResultadosLayout);
        painelResultadosLayout.setHorizontalGroup(
            painelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelResultadosLayout.setVerticalGroup(
            painelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        btnPlaylists.setText("Playlists");
        btnPlaylists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaylistsActionPerformed(evt);
            }
        });

        btnCurtidas.setText("Curtidas");
        btnCurtidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurtidasActionPerformed(evt);
            }
        });

        btnDescurtidas.setText("Descurtidas");
        btnDescurtidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescurtidasActionPerformed(evt);
            }
        });

        btnHistorico.setText("Histórico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lblUsuario.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCurtidas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDescurtidas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlaylists)
                    .addComponent(btnCurtidas)
                    .addComponent(btnDescurtidas)
                    .addComponent(btnHistorico)
                    .addComponent(btnSair)
                    .addComponent(lblUsuario))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        btnBuscarMusica.setText("Buscar");
        btnBuscarMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMusicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                .addComponent(btnBuscarMusica)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMusica))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(painelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCurtidasActionPerformed(java.awt.event.ActionEvent evt) {
        this.controller.abrirCurtidas();
    }

    private void btnDescurtidasActionPerformed(java.awt.event.ActionEvent evt) {
        this.controller.abrirDescurtidas();
    }

    private void btnPlaylistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaylistsActionPerformed
        this.controller.abrirPlaylists();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlaylistsActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed

    }//GEN-LAST:event_btnSairActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed


    }//GEN-LAST:event_txtBuscaActionPerformed

    private void btnBuscarMusicaActionPerformed(java.awt.event.ActionEvent evt)
            {//GEN-FIRST:event_btnBuscarMusicaActionPerformed
       this.controller.pesquisarMusica();
    }//GEN-LAST:event_btnBuscarMusicaActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        this.controller.abrirHistorico();
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            this.controller.pesquisarMusica();
        }
    }//GEN-LAST:event_txtBuscaKeyPressed



    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public JButton getBtnBuscarMusica() {
        return btnBuscarMusica;
    }

    public void setBtnBuscarMusica(JButton btnBuscarMusica) {
        this.btnBuscarMusica = btnBuscarMusica;
    }

    public JButton getBtnCurtidas() {
        return btnCurtidas;
    }

    public void setBtnCurtidas(JButton btnCurtidas) {
        this.btnCurtidas = btnCurtidas;
    }

    public JButton getBtnDescurtidas() {
        return btnDescurtidas;
    }

    public void setBtnDescurtidas(JButton btnDescurtidas) {
        this.btnDescurtidas = btnDescurtidas;
    }

    public JButton getBtnHistorico() {
        return btnHistorico;
    }

    public void setBtnHistorico(JButton btnHistorico) {
        this.btnHistorico = btnHistorico;
    }

    public JButton getBtnPlaylists() {
        return btnPlaylists;
    }

    public void setBtnPlaylists(JButton btnPlaylists) {
        this.btnPlaylists = btnPlaylists;
    }

    public JButton getBtnSair() {
        return btnSair;
    }

    public void setBtnSair(JButton btnSair) {
        this.btnSair = btnSair;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JPanel getPainelResultados() {
        return painelResultados;
    }

    public void setPainelResultados(JPanel painelResultados) {
        this.painelResultados = painelResultados;
    }

    public JTextField getTxtBusca() {
        return txtBusca;
    }

    public void setTxtBusca(JTextField txtBusca) {
        this.txtBusca = txtBusca;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMusica;
    private javax.swing.JButton btnCurtidas;
    private javax.swing.JButton btnDescurtidas;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnPlaylists;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel painelResultados;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
