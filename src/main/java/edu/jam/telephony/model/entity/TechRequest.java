package edu.jam.telephony.model.entity;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TechRequest {

    private int id;
    private String problemDescription;
    private int subscriberId;
    private int techSupportUserId;
    private Date date;

    public TechRequest() {
    }

    public TechRequest(int id, String problemDescription, int subscriberId, int techSupportUserId, Date date) {
        this.id = id;
        this.problemDescription = problemDescription;
        this.subscriberId = subscriberId;
        this.techSupportUserId = techSupportUserId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }


    public int getTechSupportUserId() {
        return techSupportUserId;
    }

    public void setTechSupportUserId(int techSupportUserId) {
        this.techSupportUserId = techSupportUserId;
    }

    public class CustomDateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonparser,
                                DeserializationContext deserializationcontext) throws IOException {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = jsonparser.getText();
            try {
                return format.parse(date);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
    }

}
