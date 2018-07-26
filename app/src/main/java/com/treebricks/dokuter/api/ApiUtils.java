package com.treebricks.dokuter.api;

public class ApiUtils {
    private static final String BASE_URL = "http://192.168.0.5:8000/";

    private ApiUtils() {}

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static SpecialityService getSpecialityService() {
        return RetrofitClient.getClient(BASE_URL).create(SpecialityService.class);
    }

    public static QuestionService getQuestionService() {
        return RetrofitClient.getClient(BASE_URL).create(QuestionService.class);
    }
}
