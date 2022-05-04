package ua.university.models;

import java.util.Map;

public interface IModel {
    Map<String, String> getMap();
    long getId();
    String getURLPattern();
}
