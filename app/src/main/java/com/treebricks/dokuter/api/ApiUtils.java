package com.treebricks.dokuter.api;

import android.content.res.Resources;

import com.treebricks.dokuter.DokuterApplication;
import com.treebricks.dokuter.R;

public class ApiUtils {
    private static final String BASE_URL = DokuterApplication.getResourses().getString(R.string.dokuter_root);

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
