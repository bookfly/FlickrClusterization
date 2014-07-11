/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddCluster;
import weka.filters.unsupervised.attribute.Remove;

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

        writeClusters(data, filteredClusterer);

        // Result of clusterization
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(filteredClusterer);
        eval.evaluateClusterer(data);

        writeFile(eval.clusterResultsToString(), txtName);
        //  System.out.println(eval.clusterResultsToString());

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
