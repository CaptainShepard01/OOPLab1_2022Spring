package ua.university.utils;

import ua.university.models.IModel;

import java.util.Map;

public class Utils {
    public Utils() {
    }

    public static StringBuilder getViewPage(IModel model) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> map = model.getMap();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append("<h4>").append(entry.getKey()).append("</h4>\n")
                    .append("<p>").append(entry.getValue()).append("</p>\n");
        }

        return stringBuilder;
    }

    public static StringBuilder getTable(IModel[] models) {
        StringBuilder stringBuilder = new StringBuilder();

        if (models.length == 0)
            return stringBuilder;

        Map<String, String> map = models[0].getMap();
        stringBuilder.append("<table  class=\"table table-bordered table-hover table-sm\">\n<thead>\n<tr>\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append("<th scope=\"col\">").append(entry.getKey()).append("</th>\n");
        }

        stringBuilder.append("</tr>\n</thead>\n<tbody>\n");
        for (IModel model : models) {
            stringBuilder.append(getRow(model));
        }
        stringBuilder.append("</tbody>\n</table>\n");

        return stringBuilder;
    }

    public static StringBuilder getRow(IModel model) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> map = model.getMap();

        stringBuilder.append("<tr onclick=\"window.location.href = '")
                .append(model.getURLPattern()).append("?id=")
                .append(model.getId())
                .append("';\">\n");
        for (String value : map.values()) {
            if (value.equals("Id"))
                stringBuilder.append("<th scope=\"row\">").append(value).append("</th>\n");
            else
                stringBuilder.append("<td>").append(value).append("</td>\n");
        }
        stringBuilder.append("</tr>\n");

        return stringBuilder;
    }
}
