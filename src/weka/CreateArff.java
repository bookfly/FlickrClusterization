/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author jelena
 */
public class CreateArff {
    
    private FastVector attributes;
    
    public void createNominalAtt() throws Exception {

        //Type
        FastVector sharkTypeValues = new FastVector(10);
        sharkTypeValues.addElement("reef");
        sharkTypeValues.addElement("tiger");
        sharkTypeValues.addElement("white");
        sharkTypeValues.addElement("bull");
        sharkTypeValues.addElement("galapagos");
        sharkTypeValues.addElement("lemon");
        sharkTypeValues.addElement("hammerhead");
        sharkTypeValues.addElement("nurse");
        sharkTypeValues.addElement("blue");
        sharkTypeValues.addElement("whale");
        
        Attribute type = new Attribute("Type", sharkTypeValues);

        //Location
        FastVector locationValues = new FastVector(11);
        locationValues.addElement("australia");
        locationValues.addElement("usa");
        locationValues.addElement("france");
        locationValues.addElement("zeland");
        locationValues.addElement("netherlands");
        locationValues.addElement("japan");
        locationValues.addElement("canada");
        locationValues.addElement("maldives");
        locationValues.addElement("uk");
        locationValues.addElement("kuwait");
        locationValues.addElement("taiwan");
        
        Attribute location = new Attribute("Location", locationValues);
        
        attributes = new FastVector(2);
        attributes.addElement(type);
        attributes.addElement(location);
        
        Instances data = new Instances("TrainingSet", attributes, 10);
        
        Instance[] instances = new Instance[10];
        
        instances[0] = createInstance("whale", "uk");
        instances[1] = createInstance("lemon", "usa");
        instances[2] = createInstance("reef", "france");
        instances[3] = createInstance("hammerhead", "zeland");
        instances[4] = createInstance("galapagos", "taiwan");
        instances[5] = createInstance("whale", "japan");
        instances[6] = createInstance("hammerhead", "australia");
        instances[7] = createInstance("whale", "uk");
        instances[8] = createInstance("blue", "maldives");
        instances[9] = createInstance("whale", "uk");
        
        for (int i = 0; i < instances.length; i++) {
            data.add(instances[i]);
        }
        data.setClassIndex(data.numAttributes() - 1);
        
        Classifier bayesClsf = new NaiveBayes();
        bayesClsf.buildClassifier(data);
        System.out.println(bayesClsf);
        
        Instances testSet = new Instances("TestSet", attributes, 1);
        testSet.add(createInstance("reef", "uk"));
        testSet.setClassIndex(testSet.numAttributes() - 1);
        
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(bayesClsf, testSet);
        
        String strSummary = eval.toSummaryString();
        
        System.out.println("--- Evaluation on training est ---");
        System.out.println("--- Summary ---");
        System.out.println(strSummary);
        
        System.out.println(eval.toMatrixString());
    }
    
    private Instance createInstance(String type, String location) {
        Instance i1 = new Instance(2);
        i1.setValue((Attribute) attributes.elementAt(0), type);
        i1.setValue((Attribute) attributes.elementAt(1), location);
        return i1;
    }
    
}
