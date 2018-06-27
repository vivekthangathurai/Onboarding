package com.example.mock.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Validations {

@SerializedName("length")
@Expose
private Integer length;

public Integer getLength() {
return length;
}

public void setLength(Integer length) {
this.length = length;
}

}