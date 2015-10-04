/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.gui.viewcontroller;

import com.oberger.kruppelbotsimulation.domain.gui.model.MvcModel;
import com.oberger.kruppelbotsimulation.domain.persist.LegPolyFunctionCppCodeWriter;
import com.oberger.kruppelbotsimulation.domain.persist.LegPolyFunctionsCsvWriter;
import com.oberger.kruppelbotsimulation.domain.persist.SimulationSerializer;
import com.oberger.kruppelbotsimulation.domain.simulation.Simulation;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author oberger
 */
public class MainForm extends javax.swing.JFrame implements Observer {

    private final static int PLAY_UPDATE_INTERVAL = 100;
    private final static String DEFAULT_SIM_DIRECTORY = "simulations";
    private final static String DEFAULT_CSV_DIRECTORY = "csvs";
    private final static String DEFAULT_CPP_DIRECTORY = "../Arduino";
    private final static String DEFAULT_CPP_FILENAME = "../Arduino/LegPolyFunctionFactory";

    private MvcModel model;
    private Timer playTimer;

    public MainForm(Simulation simulationOrNull) {
	model = new MvcModel(simulationOrNull);

	initComponents();

	playTimer = new Timer(PLAY_UPDATE_INTERVAL, (ActionEvent ae) -> {
	    model.setTInMs((model.getTInMs() + PLAY_UPDATE_INTERVAL) % model.getTMax());
	});
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
        jLabel2 = new javax.swing.JLabel();
        scaleSlider = new javax.swing.JSlider();
        settingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        frameSlider = new javax.swing.JSlider();
        playStopButton = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        saveSimButton = new javax.swing.JMenuItem();
        openSimButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exportCsvButton = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exportCppButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        simulationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Current State"));

        jLabel2.setText("Scale:");

        scaleSlider.setMaximum(200);
        scaleSlider.setMinimum(1);
        scaleSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        scaleSlider.setValue(70);
        scaleSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                scaleSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout simulationPanelLayout = new javax.swing.GroupLayout(simulationPanel);
        simulationPanel.setLayout(simulationPanelLayout);
        simulationPanelLayout.setHorizontalGroup(
            simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simulationView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scaleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        simulationPanelLayout.setVerticalGroup(
            simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simulationPanelLayout.createSequentialGroup()
                .addGroup(simulationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simulationView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(simulationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scaleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        jLabel1.setText("t (ms):");

        frameSlider.setMajorTickSpacing(100);
        frameSlider.setMaximum(1000);
        frameSlider.setPaintTicks(true);
        frameSlider.setSnapToTicks(true);
        frameSlider.setValue(0);
        frameSlider.setEnabled(false);
        frameSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                frameSliderStateChanged(evt);
            }
        });

        playStopButton.setText("Play");
        playStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playStopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(frameSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playStopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(frameSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playStopButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        exportCsvButton.setText("Export Rotation CSV...");
        exportCsvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCsvButtonActionPerformed(evt);
            }
        });
        jMenu1.add(exportCsvButton);
        jMenu1.add(jSeparator2);

        exportCppButton.setText("Export C++ Factory");
        exportCppButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCppButtonActionPerformed(evt);
            }
        });
        jMenu1.add(exportCppButton);

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
	model.setTInMs(frameSlider.getValue());
    }//GEN-LAST:event_frameSliderStateChanged

    private void saveSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSimButtonActionPerformed
	if (model.getSimulationOrNull() != null) {
	    JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(DEFAULT_SIM_DIRECTORY));
	    fc.setFileFilter(new FileNameExtensionFilter("Simulation files (*.sim)", "sim"));
	    int ret = fc.showSaveDialog(this);
	    if (ret == JFileChooser.APPROVE_OPTION) {
		String filename = fc.getSelectedFile().getAbsolutePath();
		if (!filename.endsWith(".sim")) {
		    filename += ".sim";
		}
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
	fc.setCurrentDirectory(new File(DEFAULT_SIM_DIRECTORY));
	fc.setFileFilter(new FileNameExtensionFilter("Simulation files (*.sim)", "sim"));
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
	    fc.setCurrentDirectory(new File(DEFAULT_CSV_DIRECTORY));
	    fc.setFileFilter(new FileNameExtensionFilter("Comma separated vector files (*.csv)", "csv"));
	    int ret = fc.showSaveDialog(this);
	    if (ret == JFileChooser.APPROVE_OPTION) {
		String filename = fc.getSelectedFile().getAbsolutePath();
		if (!filename.endsWith(".csv")) {
		    filename += ".csv";
		}
		try {
		    new LegPolyFunctionsCsvWriter().write(model.getSimulationOrNull().getLegFunctions(), filename);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Export CSV failed.", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	}
    }//GEN-LAST:event_exportCsvButtonActionPerformed

    private void scaleSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_scaleSliderStateChanged
	model.setScaleFactor(scaleSlider.getValue());
    }//GEN-LAST:event_scaleSliderStateChanged

    private void playStopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playStopButtonActionPerformed
	if (model.getSimulationOrNull() != null) {
	    if (!model.isPlaying()) {
		model.setPlaying(true);
	    } else {
		model.setPlaying(false);
	    }
	}
    }//GEN-LAST:event_playStopButtonActionPerformed

    private void exportCppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCppButtonActionPerformed
        if (model.getSimulationOrNull() != null) {
	    JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new File(DEFAULT_CPP_DIRECTORY));
	    fc.setSelectedFile(new File(DEFAULT_CPP_FILENAME));
	    fc.setFileFilter(new FileNameExtensionFilter("C++ file (*.cpp)", "cpp"));
	    int ret = fc.showSaveDialog(this);
	    if (ret == JFileChooser.APPROVE_OPTION) {
		String filename = fc.getSelectedFile().getAbsolutePath();
		if (!filename.endsWith(".cpp")) {
		    filename += ".cpp";
		}
		try {
		    new LegPolyFunctionCppCodeWriter().write(model.getSimulationOrNull().getLegFunctions(), filename);
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), "Export C++ failed.", JOptionPane.ERROR_MESSAGE);
		    e.printStackTrace();
		}
	    }
	}
    }//GEN-LAST:event_exportCppButtonActionPerformed

    @Override
    public final void update(Observable o, Object o1) {
	frameSlider.setMaximum((int) (model.getTMax()));
	frameSlider.setValue(model.getTInMs());
	scaleSlider.setValue(model.getScaleFactor());
	if (model.isPlaying()) {
	    playStopButton.setText("Stop");
	    if (!playTimer.isRunning()) {
		playTimer.start();
	    }
	} else {
	    playStopButton.setText("Start");
	    if (playTimer.isRunning()) {
		playTimer.stop();
	    }
	}

	if (model.getSimulationOrNull() != null) {
	    saveSimButton.setEnabled(true);
	    exportCsvButton.setEnabled(true);
	    exportCppButton.setEnabled(true);
	    if (model.isPlaying()) {
		frameSlider.setEnabled(false);
	    } else {
		frameSlider.setEnabled(true);
	    }
	    scaleSlider.setEnabled(true);
	    playStopButton.setEnabled(true);

	} else {
	    saveSimButton.setEnabled(false);
	    exportCsvButton.setEnabled(false);
	    exportCppButton.setEnabled(false);
	    frameSlider.setEnabled(false);
	    scaleSlider.setEnabled(false);
	    playStopButton.setEnabled(false);
	}
    }

    private void startPlaying() {
	if (model.getSimulationOrNull() != null) {
	    model.setPlaying(true);

	    frameSlider.setEnabled(false);
	}
    }

    private void stopPlaying() {
	playTimer.stop();
	model.setPlaying(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exportCppButton;
    private javax.swing.JMenuItem exportCsvButton;
    private javax.swing.JSlider frameSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem openSimButton;
    private javax.swing.JToggleButton playStopButton;
    private javax.swing.JMenuItem saveSimButton;
    private javax.swing.JSlider scaleSlider;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel simulationPanel;
    private com.oberger.kruppelbotsimulation.domain.gui.viewcontroller.SimulationView simulationView;
    // End of variables declaration//GEN-END:variables

}
