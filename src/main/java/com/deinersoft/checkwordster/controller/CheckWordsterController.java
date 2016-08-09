package com.deinersoft.checkwordster.controller;

import com.deinersoft.checkwordster.model.CheckWordster;
import com.deinersoft.checkwordster.model.CheckWordsterException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckWordsterController {

    @RequestMapping(path="/convertToWords",method=RequestMethod.POST,headers = {"Content-type=application/json"})
    @ResponseBody
    public String convertToWords(@RequestBody String jsonRequest) throws CheckWordsterException {
        JSONObject json = new JSONObject(jsonRequest);
        json.put("numberInWords",new CheckWordster(json.getString("stringOfDigits")).getWords());
        return json.toString();
    }

}


