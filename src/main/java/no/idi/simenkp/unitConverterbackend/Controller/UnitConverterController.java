package no.idi.simenkp.unitConverterbackend.Controller;

import no.idi.simenkp.unitConverterbackend.Payload.ConvertRequest;
import no.idi.simenkp.unitConverterbackend.Service.UnitConverterService;
import org.apache.catalina.filters.ExpiresFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/unitConverter")
@EnableAutoConfiguration
@CrossOrigin
public class UnitConverterController {

    Logger logger = LoggerFactory.getLogger(UnitConverterController.class);

    @Autowired
    private UnitConverterService unitConverterService;

    @PostMapping(value = "/convertString", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertString(@RequestBody ConvertRequest request)
    {
        logger.info(String.valueOf(request));
        logger.info(request.getText());
        try{
            if (request.getText().isEmpty())
            {
                return new ResponseEntity("Error: text is blank ", HttpStatus.NO_CONTENT);
            }

            String convertedString = unitConverterService.convertString(request.getText());

            return new ResponseEntity<>(convertedString, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error: Cannot convert text", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
