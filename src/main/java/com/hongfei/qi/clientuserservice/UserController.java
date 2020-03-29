package com.hongfei.qi.clientuserservice;

import biz.source_code.dsp.math.Complex;
import biz.source_code.dsp.transform.Dft;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/name")
    @ResponseBody
    public String getName(){
        return "qihongfei5551";
    }

    public static void main(String[] args) {
        double[] time       = new double[150];
        double[] valueA     = new double[150];
        for ( int i = 0; i < 50 * 3; i++ )
        {
            time[i]     = i / 50.0;
            valueA[i]   = 100 * Math.sin( 2 * Math.PI * 5 * time[i] ) + 25 * Math.sin( 2 * Math.PI * 15 * time[i] );
        }
        double deltaTime = 1 / 50.0;
        Complex[] result = Dft.goertzelSpectrum( valueA );

        double deltaFrequency = 0.5 / deltaTime / (result.length - 1);
        for ( int i = 0; i < result.length; i++ )
        {
            System.out.println( "频率：" + String.format( "%.2f", deltaFrequency * i ) + "  幅值：" + String.format( "%.2f", result[i].abs() ) );
        }
    }
}
