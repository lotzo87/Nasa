
package mx.com.idmexico.vvazquez.nasa.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class APOD2 {

    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();

    /**
     * 
     * @return
     *     The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
