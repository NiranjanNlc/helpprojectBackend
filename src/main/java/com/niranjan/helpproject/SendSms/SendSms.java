package com.niranjan.helpproject.SendSms;


import com.niranjan.helpproject.HelpedOne.HelpedOne;
import com.niranjan.helpproject.HelpedOne.HelpedService;
import com.niranjan.helpproject.User.Customer;
import com.niranjan.helpproject.User.UserService;
import com.niranjan.helpproject.Variable.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/send/")
public class SendSms
{
    @Autowired
    UserService userService;
    @Autowired
    HelpedService helpedService;
    // Replace these placeholders with your Account Sid and Auth Token
    public static final String ACCOUNT_SID = "AC01221184ed7828b128c85e7eb8ca25a0";
    public static final String AUTH_TOKEN = "5046269fe2c8dea518993452175c1648";
    @GetMapping("/getHelpedNumber")
    public String getHelped()
    {
        return String.valueOf(helpedService.getAllUsers().size());
    }

    @PostMapping(consumes = "application/json")
    public Integer sendSms(@RequestBody  Help help)
    {
        System.out.println(
                help.toString()
        );
        List<Customer> customers = userService.getAllUsers();
        System.out.printf(String.valueOf(customers.size()));
        Integer i= sendSms(customers,help);
        return i;
    }

    public Integer sendSms(List<Customer> customers, Help help)
    {
         final Integer i =0;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        customers.forEach(customer -> {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+9779864434561"),
                    new com.twilio.type.PhoneNumber("" +
                            "+19734574434"),
                    "Hi "+ customer.getLastName()+",do you know "+help.getVar1()+"with "+help.getVar2() +
                            "&" +help.getVar3()+"in your locality? \n"+
                            "please reply on this :"
                            +"dev3.pareva.umelimited.com/selectPlace?u="+help.getHelpedOne())
                    .create();
            System.out.println(message.toString());

        });

        return i;
    }
    @GetMapping
    public Integer getSms(String location,long helpedOne )
    {
        List<Customer> customers = userService.getAllUsers();
        customers.forEach(customer -> {
            if(customer.getId()==helpedOne)
            {
                Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(customer.getPhoneNumber()),
                        new com.twilio.type.PhoneNumber("" +
                                "+19734574434"),
                        "Your Suggestion is  :"+location)
                        .create();

            }
        });
        HelpedOne h = new HelpedOne();
        h.setHelpedOne(helpedOne);
        helpedService.createOrUpdateUser(h);
        return helpedService.getAllUsers().size();
    }
}
