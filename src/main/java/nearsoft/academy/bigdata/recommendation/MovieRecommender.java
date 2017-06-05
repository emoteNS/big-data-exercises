package nearsoft.academy.bigdata.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by AMDA on 29/03/2017.
 */
public class MovieRecommender {

    //Logger
    final static Logger logger = LoggerFactory.getLogger(MovieRecommender.class);

    //Recommender
    DataModel model;
    UserSimilarity similarity;
    UserNeighborhood neighborhood;
    UserBasedRecommender recommender;

    //Data Management
    TransformData transformData;
    ManageList manageList;

    String filePath;

    public MovieRecommender(String filePath){
        //Transform Data With the path of output file
        transformData = new TransformData();

        //First we set the output file, before to start the parsing
        transformData.setOutputFile( filePath );

        //Function who calls the convertion of an input file
        transformData.transformToPreferenceFile(filePath);

        //Assign result Maps to class variable
        manageList = transformData.manageList;

        //Initializing model with the output file
        try {
            model = new FileDataModel(new File(transformData.filenameOutput));
        }
        catch (IOException e){
            logger.error("Datamodel couldn't be created, error: " + e);
        }

        this.filePath = filePath;
    }

    public long getTotalReviews() {
        return manageList.getTotalIndexes();
    }

    public long getTotalProducts(){
        try{
            return (long)model.getNumItems();
        }
        catch (TasteException e){
            logger.error("Error retrieving total reviews, error: " + e);
        }
        return 0;
    }

    public long getTotalUsers(){
        try{
            return (long)model.getNumUsers();
        }
        catch (TasteException e){
            logger.error("Error retrieving total users, error: " + e);
        }
        return 0;
    }

    public List<String> getRecommendationsForUser(String user){

        List<String> recommendationsList = new ArrayList<String>();

        try{
            //Data from recommendation
            similarity = new PearsonCorrelationSimilarity(model);
            neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
            recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            long idUser = (long) manageList.getUserList().get( user );

            List recommends = recommender.recommend( idUser, 3);

            for(Object re : recommends)
            {
                recommendationsList.add( manageList.getInvertProductList().get( ((RecommendedItem) re).getItemID() ) );
            }
        }
        catch (TasteException e){
            logger.error("Error building movie recommender, error: " + e);
        }

        return recommendationsList;
    }
}