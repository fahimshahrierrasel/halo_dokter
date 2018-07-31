package com.treebricks.dokuter.api;

import com.treebricks.dokuter.models.Answer;
import com.treebricks.dokuter.models.Question;
import com.treebricks.dokuter.models.QuestionDetails;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionService {
    @POST("/api/questions")
    @FormUrlEncoded
    Call<String> saveQuestion(@FieldMap Map<String, String> fields);

    @GET("/api/questions/{pid}") // pid is the patient/user id
    Call<List<Question>> getMyQuestions(@Path("pid") String uid);

    @GET("/api/question/{qid}")  // qid is the question id
    Call<QuestionDetails> getQuestionDetails(@Path("qid") int qid);

    @GET("/api/answers/{qid}") // qid is the question id
    Call<List<Answer>> getQuestionAnswers(@Path("qid") int qid);
}
