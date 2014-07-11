/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.explorer.ClustererPanel;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.VisualizePanel;

/**
 *
 * @author jelena
 */
public class ReadArff {

    public ReadArff() {
    }

    public void readArff(String fileName, int clustNo, String txtName) throws FileNotFoundException, IOException, Exception {

        ConverterUtils.DataSource loader = new ConverterUtils.DataSource("data/" + fileName + ".arff");
        Instances data = loader.getDataSet();

        Remove removeFilter = new Remove();
        removeFilter.setAttributeIndices("3");

        SimpleKMeans kMeansCLusterer = new SimpleKMeans();
        kMeansCLusterer.setNumClusters(clustNo);
        kMeansCLusterer.setDisplayStdDevs(true);

        FilteredClusterer filteredClusterer = new FilteredClusterer();
        filteredClusterer.setClusterer(kMeansCLusterer);
        filteredClusterer.setFilter(removeFilter);
        filteredClusterer.buildClusterer(data);

    //    writeClusters(data, filteredClusterer);
        // Result of clusterization
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(filteredClusterer);
        eval.evaluateClusterer(data);

        writeFile(eval.clusterResultsToString(), txtName);

        // setup visualization
        // taken from: ClustererPanel.startClusterer()
        PlotData2D predData = ClustererPanel.setUpVisualizableInstances(data, eval);
        String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
        String cname = filteredClusterer.getClass().getName();
        if (cname.startsWith("weka.clusterers.")) {
            name += cname.substring("weka.clusterers.".length());
        } else {
            name += cname;
        }

        VisualizePanel vp = new VisualizePanel();
        vp.setName(name + " (" + data.relationName() + ")");
        predData.setPlotName(name + " (" + data.relationName() + ")");
        vp.addPlot(predData);

        // display data
        // taken from: ClustererPanel.visualizeClusterAssignments(VisualizePanel)
        String plotName = vp.getName();
        final javax.swing.JFrame jf
                = new javax.swing.JFrame("Weka Clusterer Visualize: " + plotName);
        jf.setSize(500, 400);
        jf.getContentPane().setLayout(new BorderLayout());
        jf.getContentPane().add(vp, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });
        jf.setVisible(true);

    }

    private void writeClusters(Instances data, FilteredClusterer filteredClusterer) throws Exception {
        PrintWriter writer = new PrintWriter("data/resultWithClusters.txt");
        for (int i = 0; i < data.numInstances(); i++) {
            writer.print(data.instance(i).toString());
            writer.println(", " + filteredClusterer.clusterInstance(data.instance(i)));

        }
        writer.close();

    }

    private void writeFile(String text, String name) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("data/" + name + ".txt");
        writer.println(text);
        writer.close();
    }
}
