/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JASON;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class MainApplication {
    private SimpleDateFormat dd = new SimpleDateFormat("dd.MM.yyyy");
    public void main(String[] args) throws ParseException{
        Serializer();
        Deserializer();
    }
    
    public void Serializer(){
        Date birth = null;
        try{
            birth = dd.parse("29.05.2002");
        }catch(ParseException ep){
            ep.printStackTrace();
        }
        Datenhaltung dh = new Datenhaltung(23,"Raggam","Maximilian", birth);
        Gson gson = new Gson();
        String jasonSerializer = gson.toJason(dh);
    }
    
    public void Deserializer(){
        String jasonDeserializer = "{'id':23,'firstName':'Raggam','lastName':'Raggam','birthDate':'29.05.2002'};
    }
}
