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


}
