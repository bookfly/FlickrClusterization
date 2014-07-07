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

    public void readArff(String fileName) throws FileNotFoundException, IOException, Exception {
        /*   BufferedReader reader = new BufferedReader(new FileReader(fileName + ".arff"));
         Instances data = new Instances(reader);
         reader.close();
         // Setting class attribute
         data.setClassIndex(data.numAttributes() - 1);
         */
        ConverterUtils.DataSource loader = new ConverterUtils.DataSource(fileName + ".arff");
        Instances data = loader.getDataSet();

        SimpleKMeans kMeansCLusterer = new SimpleKMeans();
        kMeansCLusterer.setNumClusters(3);
        kMeansCLusterer.setDisplayStdDevs(true);

        FilteredClusterer filteredClusterer = new FilteredClusterer();
        filteredClusterer.setClusterer(kMeansCLusterer);
        //  filteredClusterer.setFilter(removeFilter);
        filteredClusterer.buildClusterer(data);

        // ispisujemo podatke klasterovanja
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(filteredClusterer);
        eval.evaluateClusterer(data);

        System.out.println(eval.clusterResultsToString());

    }
}
