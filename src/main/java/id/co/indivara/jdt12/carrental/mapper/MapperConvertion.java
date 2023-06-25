package id.co.indivara.jdt12.carrental.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import id.co.indivara.jdt12.carrental.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class MapperConvertion {

    public static List<Customer> getAllDataCustomer(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, new TypeReference<List<Customer>>() {
        });
    }
    public static <T> List<T> getAllData(String json, Class<T> elementClass)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }
    public static <T> T getData(String json, Class<T> tclass) throws  JsonProcessingException{
        ObjectMapper ob = new ObjectMapper();

        return ob.readValue(json, tclass);
    }
    public static <T> String toJson(T object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
