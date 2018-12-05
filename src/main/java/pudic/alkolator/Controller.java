package pudic.alkolator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final double ELIMINATION_RATE = 0.018;

    @PostMapping("/calculate")
    public ResponseEntity<Double> estimateBAC(@RequestParam String sex, @RequestParam int age, @RequestParam float weight, @RequestParam double height, @RequestParam float amount, @RequestParam float percents, @RequestParam double timeElapsed){

        double r = 0;
        float ethanol = amount*percents/100;
        double h = height/100;
        if(sex.equals("M")){
            r = 0.62544 +  (0.13664 * h) -(weight * (0.00189 + 0.002425/(h*h))) - (1/(weight * (0.57986
                    + 2.545 * h - 0.02255 * age)));
        }else if(sex.equals("F")){
            r = 0.50766 + (0.11165 * h) - (weight * (0.001612
                    + 0.0031/(h*h))) - (1/(weight * (0.62115 - 3.1665 * h)));
        }else{
            return (new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }

        System.out.println(r);
        double result = (ethanol / (r*weight) ) - ELIMINATION_RATE*timeElapsed;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
