package house.beom.com.mvvm.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ahmad Shubita on 7/25/17.
 */

public class Name implements Serializable {

    @SerializedName("title") public String title;

    @SerializedName("first") public String first;

    @SerializedName("last") public String last;
}
