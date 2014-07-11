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
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author jelena
 */
public class ReadArff {

    public ReadArff() {
    }

    public void readArff(String fileName, int clustNo) throws FileNotFoundException, IOException, Exception {

        ConverterUtils.DataSource loader = new ConverterUtils.DataSource(fileName + ".arff");
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

        // Result of clusterization
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(filteredClusterer);
        eval.evaluateClusterer(data);

        writeFile(eval.clusterResultsToString());
        //  System.out.println(eval.clusterResultsToString());

    }

    private void writeFile(String text) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("data/results.txt");
        writer.println(text);
        writer.close();
    }
}
