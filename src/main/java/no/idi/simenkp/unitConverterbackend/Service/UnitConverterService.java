package no.idi.simenkp.unitConverterbackend.Service;

import no.idi.simenkp.unitConverterbackend.Controller.UnitConverterController;
import no.idi.simenkp.unitConverterbackend.Payload.ConvertRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;

@Service
public class UnitConverterService {

    private String enhet;
    private String convertedString;
    private double resultInFtAndInches;
    private double temp;
    private int resultInFeet;


    public String convertKgToLbs(String subString) {
        double resultInPounds = 0;

        double d = Float.parseFloat(subString);
        resultInPounds = Math.round((d * 2.2046) * 100.0) / 100.0;
        enhet = "lbs";
        return resultInPounds + "" + enhet;
    }

    public String convertCmToFeet(String subString) {
        double d = Float.parseFloat(subString);
        resultInFtAndInches = Math.round((d * 0.0328084) * 100.0) / 100.0;

        resultInFeet = (int)resultInFtAndInches;
        temp = (resultInFtAndInches - resultInFeet)/ 0.08333;
        int inchesLeft = (int)temp;

        return resultInFeet + "'" + inchesLeft + "''";
    }

    public String convertMToFeet(String subString) {
        double d = Float.parseFloat(subString);
        resultInFtAndInches = Math.round((d * 3.28084) * 100.0) / 100.0;

        resultInFeet = (int)resultInFtAndInches;
        temp = (resultInFtAndInches - resultInFeet)/ 0.08333;
        int inchesLeft = (int)temp;

        return resultInFeet + "'" + inchesLeft + "''" ;
    }

    public String convertMMToFeet(String subString) {
        double d = Float.parseFloat(subString);
        resultInFtAndInches = Math.round((d * 0.00328084) * 100.0) / 100.0;

        resultInFeet = (int)resultInFtAndInches;
        temp = (resultInFtAndInches - resultInFeet)/ 0.08333;
        int inchesLeft = (int)temp;

        return resultInFeet + "'" + inchesLeft + "''";
    }

    public String convertGramToLbs(String subString) {
        double resultInFt = 0;

        double d = Float.parseFloat(subString);
        resultInFt = Math.round((d * 0.00220462) * 100.0) / 100.0;
        enhet = "lbs";
        return resultInFt + "" + enhet;
    }

    private String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }


    public String convertString(String text) {

        //Splitter String mellom tall og bokstav. Ikke andre vei.
        String[] substrings = text.split("((?<=\\D)(?=\\d))");
        String[] valueWithMetric;

        String convertedMetric = "";

        for(int i = 1; i < substrings.length; i++){

            System.out.println(substrings[i]);

            //Splitter substring mellom tall og bokstaver og mellom bokstav og tall.
            valueWithMetric = substrings[i].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

            if(getFirstWord(substrings[i]).contains("kg")) {
                convertedMetric = convertKgToLbs(valueWithMetric[0]);

                //Replacer substring med ny verdi og enhet. Første ord i substring.
                substrings[i] = substrings[i].replaceAll(getFirstWord(substrings[i]), " " + convertedMetric);
            }
            else if(getFirstWord(substrings[i]).contains("mm")) {
                convertedMetric = convertMMToFeet(valueWithMetric[0]);

                substrings[i] = substrings[i].replaceAll(getFirstWord(substrings[i]), " " + convertedMetric);
            }
            else if(getFirstWord(substrings[i]).contains("cm")) {
                convertedMetric = convertCmToFeet(valueWithMetric[0]);

                substrings[i] = substrings[i].replaceAll(getFirstWord(substrings[i]), " " + convertedMetric);
            }
            else if(getFirstWord(substrings[i]).contains("m")){
                convertedMetric = convertMToFeet(valueWithMetric[0]);

                substrings[i] = substrings[i].replaceAll(getFirstWord(substrings[i]), " " + convertedMetric);
            }
            else if(getFirstWord(substrings[i]).contains("g")){
                convertedMetric = convertGramToLbs(valueWithMetric[0]);
                substrings[i] = substrings[i].replaceAll(getFirstWord(substrings[i]), " " + convertedMetric);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < substrings.length; i++) {
            sb.append(substrings[i]);
        }
        convertedString = sb.toString();

        return convertedString;
    }
}
