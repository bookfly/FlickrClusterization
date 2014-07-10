/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import java.io.FileNotFoundException;
import java.io.IOException;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

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

        SimpleKMeans kMeansCLusterer = new SimpleKMeans();
        kMeansCLusterer.setNumClusters(clustNo);
        kMeansCLusterer.setDisplayStdDevs(true);

        FilteredClusterer filteredClusterer = new FilteredClusterer();
        filteredClusterer.setClusterer(kMeansCLusterer);
        filteredClusterer.buildClusterer(data);

        // Result of clusterization
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(filteredClusterer);
        eval.evaluateClusterer(data);

        System.out.println(eval.clusterResultsToString());

    }
}
