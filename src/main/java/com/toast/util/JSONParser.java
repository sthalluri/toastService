package com.toast.util;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.SqlTimestampConverter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.toast.meeting.service.Meeting;
import com.toast.meeting.service.MeetingParser;

public class JSONParser {

    public static String toJSON(Object obj) throws JSONException {
            return toJSON(obj, getXStream());
    }

    /**
     * Need a better way handle the switches for parsing.
     * 
     * One option is to have a static method on the TO passed which knows which
     * parser to use
     */
    public static Object parseJSON(String jsonString, Class className,
            String nodeName) {
        XStream xstream = getXStream();
        if(nodeName ==null){
            xstream.alias("x", className);
            return xstream.fromXML("{x:" + jsonString + "}");
        }else{
            xstream.alias(nodeName, className);
            return xstream.fromXML(jsonString);
        }
    }
    
    public static Object parseJSON(String jsonString, Class className) {
        return parseJSON(jsonString, className, null);
    }
    

    private static String toJSON(Object obj, XStream xstream)
            throws JSONException {
        xstream.alias("x", obj.getClass());
        String jsonString = xstream.toXML(obj);
        return jsonString.substring(5, jsonString.length() - 1);
    }

    private static XStream getXStream() {
    	JettisonMappedXmlDriver jettisonDriver = new JettisonMappedXmlDriver();
        XStream xstream = new XStream(jettisonDriver);
        //xstream.setMode(XStream.NO_REFERENCES);
        xstream.registerConverter(new SybTimestampConverter(null));
        return xstream;
    }

    public static String listToJSON(List rList) {    	
    	StringBuffer str = new StringBuffer();
        if (rList.size() == 0) {
            return "{\"success\":\"\",\"size\":0,\"rows\":[]}";
        }
        XStream xstream = getXStream();
        for (int i = 0; i < rList.size(); i++) {
            Object obj = rList.get(i);
            if (i != 0) {
                str.append(",");
            }
            try {
            	if(obj instanceof Meeting){
                    str.append(MeetingParser.toJson((Meeting) obj));
            	}else{
                    str.append(JSONParser.toJSON(obj, xstream));
            	}
            } catch (JSONException e) {
                e.printStackTrace();
                throw new RuntimeException(
                        "Error while marshalling the object " + obj);
            }
        }
        return "{\"size\":" + rList.size() + ",\"rows\":[" + str+ "]}";
    }
}


