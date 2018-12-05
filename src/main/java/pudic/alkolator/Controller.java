package pudic.alkolator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
public class Controller {

    private final double ELIMINATION_RATE = 0.15;

    @GetMapping("/")
    public String helloWorld(){
        return "ALKOLATOR DZIAŁA";
    }
    @PostMapping("/calculate")
    public ResponseEntity<ArrayList<Calculation>> estimateBAC(@RequestParam String sex,
                                                              @RequestParam(required = false) int age,
                                                              @RequestParam float weight,
                                                              @RequestParam(required = false) double height,
                                                              @RequestParam float amount,
                                                              @RequestParam float percents,
                                                              @RequestParam int timeElapsed,
                                                              @RequestParam String firstDrink)
            throws ParseException {
        ArrayList<Calculation> result = new ArrayList<>();
        Date currentTime = Calculation.CALCULATION_DATE_FORMAT.parse(firstDrink);
        double genderSpecificRatio = sex.equals("M") ? 0.68 : 0.55;
        double ethanol = (amount*percents/100) * 0.789;
        double bodyWater = weight * genderSpecificRatio * 1000;
        double bac = ethanol/bodyWater * 1000;
        double currentBac = bac - ELIMINATION_RATE*timeElapsed;
        while(currentBac>0){
            result.add(new Calculation(addElapsed(currentTime, timeElapsed), currentBac));
            timeElapsed++;
            currentBac = bac - ELIMINATION_RATE*timeElapsed;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Date addElapsed(Date currentTime, int timeElapsed) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentTime);
        cal.add(Calendar.HOUR_OF_DAY, timeElapsed);
        return cal.getTime();
    }


}
