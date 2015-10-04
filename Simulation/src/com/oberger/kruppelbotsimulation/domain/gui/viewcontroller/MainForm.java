/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui.viewcontroller;

import com.oberger.kruppelbotsimulation.domain.gui.model.MvcModel;
import com.oberger.kruppelbotsimulation.domain.persist.LegPolyFunctionsCsvWriter;
import com.oberger.kruppelbotsimulation.domain.persist.SimulationSerializer;
import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author oberger
 */
public class MainForm extends javax.swing.JFrame implements Observer {

    private MvcModel model;

    public MainForm(Simulation simulationOrNull) {
	model = new MvcModel(simulationOrNull);
	initComponents();

	simulationView.setModel(model);
	model.addObserver(this);
	update(model, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simulationPanel = new javax.swing.JPanel();
        simulationView = new com.oberger.kruppelbotsimulation.domain.gui.viewcontroller.SimulationView();
        settingsPanel = new javax.swing.JPanel();
        frameSlider = new javax.swing.JSlider();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        saveSimButton = new javax.swing.JMenuItem();
        openSimButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exportCsvButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        simulationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Current State"));

        javax.swing.GroupLayout simulationPanelLayout = new javax.swing.GroupLayout(simulationPanel);
        simulationPanel.setLayout(simulationPanelLayout);
        simulationPanelLayout.setHorizontalGroup(
            simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simulationView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        simulationPanelLayout.setVerticalGroup(
            simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationPanelLayout.createSequentialGroup()
                .addComponent(simulationView, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        frameSlider.setMajorTickSpacing(100);
        frameSlider.setMaximum(1000);
        frameSlider.setPaintTicks(true);
        frameSlider.setSnapToTicks(true);
        frameSlider.setEnabled(false);
        frameSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                frameSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(frameSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(frameSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        saveSimButton.setText("Save Simulation...");
        saveSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSimButtonActionPerformed(evt);
            }
        });
        jMenu1.add(saveSimButton);

        openSimButton.setText("Open Simulation...");
        openSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSimButtonActionPerformed(evt);
            }
        });
        jMenu1.add(openSimButton);
        jMenu1.add(jSeparator1);

        exportCsvButton.setText("Export Roation CSV...");
        exportCsvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCsvButtonActionPerformed(evt);
            }
        });
        jMenu1.add(exportCsvButton);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(simulationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simulationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void frameSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_frameSliderStateChanged
	model.setT(frameSlider.getValue());
    }//GEN-LAST:event_frameSliderStateChanged

    private void saveSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSimButtonActionPerformed
	if (model.getSimulationOrNull() != null) {
	    JFileChooser fc = new JFileChooser();
	    int ret = fc.showSaveDialog(this);
	    if (ret == JFileChooser.APPROVE_OPTION) {
		String filename = fc.getSelectedFile().getAbsolutePath();
		try {
		    new SimulationSerializer().serialize(model.getSimulationOrNull(), filename);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Saving failed.", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	}
    }//GEN-LAST:event_saveSimButtonActionPerformed

    private void openSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSimButtonActionPerformed
	JFileChooser fc = new JFileChooser();
	int ret = fc.showOpenDialog(this);
	if (ret == JFileChooser.APPROVE_OPTION) {
	    String filename = fc.getSelectedFile().getAbsolutePath();
	    try {
		Simulation loaded = new SimulationSerializer().deserialize(filename);
		model.setSimulationOrNull(loaded);
	    } catch (IOException | ClassNotFoundException e) {
		JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Loading failed.", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	    }
	}
    }//GEN-LAST:event_openSimButtonActionPerformed

    private void exportCsvButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCsvButtonActionPerformed
        if (model.getSimulationOrNull() != null) {
	    JFileChooser fc = new JFileChooser();
	    int ret = fc.showSaveDialog(this);
	    if (ret == JFileChooser.APPROVE_OPTION) {
		String filename = fc.getSelectedFile().getAbsolutePath();
		try {
		    new LegPolyFunctionsCsvWriter().write(model.getSimulationOrNull().getLegFunctions(), filename);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Export failed.", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	}
    }//GEN-LAST:event_exportCsvButtonActionPerformed

    @Override
    public final void update(Observable o, Object o1) {
	frameSlider.setMaximum((int) (1000 * model.getTMax()));
//	frameSlider.setValue((int)(1000 * model.getT()));
	if (model.getSimulationOrNull() != null) {
	    saveSimButton.setEnabled(true);
	    frameSlider.setEnabled(true);
	} else {
	    saveSimButton.setEnabled(false);
	    frameSlider.setEnabled(false);
	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exportCsvButton;
    private javax.swing.JSlider frameSlider;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem openSimButton;
    private javax.swing.JMenuItem saveSimButton;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel simulationPanel;
    private com.oberger.kruppelbotsimulation.domain.gui.viewcontroller.SimulationView simulationView;
    // End of variables declaration//GEN-END:variables

}
