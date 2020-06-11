package response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import entityBackend.Champs;
import entityBackend.Farmer;

public class FarmerResponse {
    @SerializedName("data")
    private List<Farmer> farmerList;

    @Override
    public String toString() {
        return "FarmerResponse{" +
                "farmerList=" + farmerList.toString() +
                '}';
    }
}
