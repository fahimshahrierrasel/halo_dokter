package com.treebricks.dokuter.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer implements Parcelable
{

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("answer")
	@Expose
	private String answer;
	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("education")
	@Expose
	private String education;
	@SerializedName("experience")
	@Expose
	private String experience;
	@SerializedName("pic")
	@Expose
	private String pic;
	public final static Parcelable.Creator<Answer> CREATOR = new Creator<Answer>() {


		@SuppressWarnings({
				"unchecked"
		})
		public Answer createFromParcel(Parcel in) {
			return new Answer(in);
		}

		public Answer[] newArray(int size) {
			return (new Answer[size]);
		}

	}
			;

	protected Answer(Parcel in) {
		this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
		this.answer = ((String) in.readValue((String.class.getClassLoader())));
		this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
		this.name = ((String) in.readValue((String.class.getClassLoader())));
		this.education = ((String) in.readValue((String.class.getClassLoader())));
		this.experience = ((String) in.readValue((String.class.getClassLoader())));
		this.pic = ((String) in.readValue((String.class.getClassLoader())));
	}

	public Answer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(id);
		dest.writeValue(answer);
		dest.writeValue(createdAt);
		dest.writeValue(name);
		dest.writeValue(education);
		dest.writeValue(experience);
		dest.writeValue(pic);
	}

	public int describeContents() {
		return 0;
	}

}