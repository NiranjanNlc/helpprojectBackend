package com.niranjan.helpproject.Security.SendSms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niranjan.helpproject.HelpedOne.HelpedService;
import com.niranjan.helpproject.Security.User.Customer;
import com.niranjan.helpproject.Security.User.UserRepo;
import com.niranjan.helpproject.Security.User.UserService;
import com.niranjan.helpproject.Security.Variable.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.HashMap;
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

    @Autowired
    UserRepo userRepo;

    // Replace these placeholders with your Account Sid and Auth Token
   // public static final String ACCOUNT_SID = " ";
    public static final String ph1="+9779864434561";
    public static final String ph2="+447427626522";
    //public static final String AUTH_TOKEN = " ";
    @GetMapping("/getHelpedNumber")
    public String getHelped()
    {
//        HashMap<String , String> hmap = new HashMap<String, String>();
//          Customer customer = userRepo.findCustomerByRid(user);
//
//        /*Adding elements to HashMap*/
//        hmap.put("email", customer.getEmail());
//        hmap.put("phone", customer.getPhoneNumber());
//        hmap.put( "size", String.valueOf(helpedService.getAllUsers().size()));
        return String.valueOf(helpedService.getAllUsers().size());

    }

    @PostMapping
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
        //String location=getLocationId(help.getLocation());
        customers.forEach(customer -> {
            Message message = Message.creator(
                    //    new com.twilio.type.PhoneNumber("+447427626522"),
                    new com.twilio.type.PhoneNumber(ph2),
                    new com.twilio.type.PhoneNumber("+15183230644"),
                    "Hi "+ customer.getLastName()+",do you know "+help.getVar1()+"with "+help.getVar2() +
                            "   &   " +help.getVar3()+" near "+ help.getLocation() +" ? \n"+
                            "please reply on this :"
                            +"http://dev3.pareva.umelimited.com/#/add?"+
                            "var1="+help.getVar1().replace(' ','+')+"&var2="+help.getVar2().replace(' ','+')+"&var3=" +help.getVar3().replace(' ','+')+
                            "&lat="+help.getLat()+"&lng="+help.getLng())
                                      .create();
            System.out.println(message.toString());
        });


        return i ;
    }
//    public String getLocationId(String loc)
//    {
//        loc=loc.replace(' ','+');
//        OkHttpClient client = new OkHttpClient();
//        String encodedAddress = URLEncoder.encode(loc, "UTF-8");
//        Request request = new Request.Builder()
//                                  .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
//                                  .get()
//                                  .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
//                                  .addHeader("x-rapidapi-key", {your-api-key-here}/*  Use your API Key here */)
//                                  .build();
//        ResponseBody responseBody = client.newCall(request).execute().body();
//         if(responseBody.toString().contains(''))
//        return result;
//    }
    @PostMapping("/suggestion")
    public Integer getSms(@RequestBody  HashMap<String,Object> sugg )
    {
        long helpedOne=1;
        List<Customer> customers = userService.getAllUsers();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        customers.forEach(customer -> {
//            if(customer.getId()==helpedOne)
//            {
//                Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber("+9779864434561"),
//                        new com.twilio.type.PhoneNumber(" +15183230644"),
//                        "Your Suggestion is  :"+ "https://maps.google.com/?q="+location)
//                        .create();
//
//            }
//        });
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(ph2),
                new com.twilio.type.PhoneNumber("+15183230644"),
                "The help requested by you for"+sugg.get("var1")+"with "+sugg.get("var2")+
                        " & "+sugg.get("var3")+"  is "+sugg.get("sugg")+
                " which is loacted at "+ "https://maps.google.com/?q="+sugg.get("location").toString().replace(" ","+"))
                                  .create();
//        HelpedOne h = new HelpedOne();
//        h.setHelpedOne(helpedOne);
//        helpedService.createOrUpdateUser(h);
        return userService.getAllUsers().size();
    }

     @PostMapping("/get")
     public String getdata(@RequestBody String user) throws JsonProcessingException {
         System.out.println(user);
         ObjectMapper mapper = new ObjectMapper();
         //Converting the Object to JSONString
         String jsonString = mapper.writeValueAsString(userService.getUserByname(user));
         return jsonString;

     }
}
