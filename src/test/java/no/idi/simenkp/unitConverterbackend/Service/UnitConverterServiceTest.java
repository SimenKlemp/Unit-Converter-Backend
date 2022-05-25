package no.idi.simenkp.unitConverterbackend.Service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UnitConverterServiceTest {

    @Autowired
    private UnitConverterService unitConverterService;

    @Test
    void contextLoads() {
    }

    @Test
    public void convertMetric(){
        String unconvertedText = "Jeg heter Simen og jeg er 1m høy og jeg er 8300g tung";

        String expectedConvertedText = "Jeg heter Simen og jeg er  3'3'' høy og jeg er  18.3lbs tung";

        String convertedText = "";

        try{
            convertedText = unitConverterService.convertString(unconvertedText);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        assertThat(convertedText).isEqualTo(expectedConvertedText);

    }

    @Test
    public void convertPrefix(){
        String unconvertedText = "Jeg heter Simen og jeg er 190cm høy og er 90kg tung";

        String expectedConvertedText = "Jeg heter Simen og jeg er  6'2'' høy og er  198.41lbs tung";

        String convertedText = "";

        try{
            convertedText = unitConverterService.convertString(unconvertedText);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertThat(convertedText).isEqualTo(expectedConvertedText);
    }

    //Funker ikke. Har ikke løst det med desimaltall
    @Test
    public void convertWithDecimal(){
        String unconvertedText = "Jeg heter Simen og jeg er 1.90m høy og er 83.5kg tung";

        String expectedConvertedText = "Jeg heter Simen og jeg er  6.23ft høy og er  198.41lbs tung";

        String convertedText = "";

        try{
            convertedText = unitConverterService.convertString(unconvertedText);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertThat(convertedText).isEqualTo(expectedConvertedText);
    }

    @Test
    public void sampleTextIsConvertedCorrectly(){
        String unconvertedText = "The baby was 591mm tall and weighed 4kg at birth. The mother was 1.71m and 71.22kg before birth and 67.22kg after birth.\"";

        String expectedConvertedText = "The baby was 1'11'' tall and weighed 8.82lbs at birth. The mother was 5'7'' and 157.01lbs before birth and 148.19lbs after birth";

        String convertedText = "";

        try{
            convertedText = unitConverterService.convertString(unconvertedText);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assertThat(convertedText).isEqualTo(expectedConvertedText);
    }
}
