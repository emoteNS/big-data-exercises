package nearsoft.academy.bigdata.recommendation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AMDA on 30/03/2017.
 */
class TransformData {
    //Logger
    final static Logger logger = LoggerFactory.getLogger(MovieRecommender.class);

    //Output Filename
    private String filenameOutput;

    //List Manage
    private ManageList manageList = new ManageList();

    public void setOutputFile(String file) {

        String[] temp = file.split("\\.");

        setFilenameOutput(temp[0] + "Output." + temp[1]);
    }

    public void transformToPreferenceFile(String file) {
        try {
            //Reader
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //Writer
            FileWriter fw = new FileWriter(getFilenameOutput());
            BufferedWriter bw = new BufferedWriter(fw);

            //Line control
            String line;

            StringBuilder sb = new StringBuilder();

            int index = 0;
            int totalIndexes = 0;

            while ((line = br.readLine()) != null) {
                if (line.split("/")[0].equals("product")) {
                    index++;
                    sb.append(getManageList().addProduct(line.split(" ")[1]) + ",");

                } else if (line.split(":")[0].equals("review/userId")) {
                    index++;
                    sb.insert(0, getManageList().addUser(line.split(" ")[1]) + ",");

                } else if (line.split(":")[0].equals("review/score")) {
                    index++;
                    sb.append(line.split(" ")[1]);
                }

                if (index == 3) {
                    bw.write(sb.toString() + "\n");
                    index = 0;
                    totalIndexes++;
                    sb.setLength(0);
                }
            }

            getManageList().setTotalIndexes(totalIndexes);

            br.close();
            bw.close();
            fr.close();
            fw.close();
        } catch (IOException e) {
            logger.error("Error opening file, error: " + e);
        }
    }

    public String getFilenameOutput() {
        return filenameOutput;
    }

    public void setFilenameOutput(String filenameOutput) {
        this.filenameOutput = filenameOutput;
    }

    public ManageList getManageList() {
        return manageList;
    }

    public void setManageList(ManageList manageList) {
        this.manageList = manageList;
    }
}
