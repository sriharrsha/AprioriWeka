/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap.mavenproject1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.associations.Apriori;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 *
 * @author USER
 */
public class HelloWeka {
    
    public static void main(String args[]){
        Instances data = null;
        ArffLoader loader=new ArffLoader();
        try {
           
            loader.setFile(new File("C:\\Users\\USER\\Desktop\\data.arff"));
            
            data = loader.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);
 
           
        } catch (IOException ex) {
            Logger.getLogger(HelloWeka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Apriori apriori=new Apriori();
        try {
            NumericToNominal numericToNominal=new NumericToNominal();
            numericToNominal.setInputFormat(data);
              
            Instances nominalData = Filter.useFilter(data, numericToNominal);
            apriori.buildAssociations(nominalData);
            FastVector[] allTheRules;
            allTheRules = apriori.getAllTheRules();
                    for(int i=0;i<allTheRules.length;i++){
                        System.out.println(allTheRules[i]);
                    }
//             BufferedWriter writer = new BufferedWriter(new FileWriter("./output.arff"));
//                writer.write(nominalData.toString());
//                writer.flush();
//                writer.close();

        } catch (Exception ex) {
            Logger.getLogger(HelloWeka.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}
