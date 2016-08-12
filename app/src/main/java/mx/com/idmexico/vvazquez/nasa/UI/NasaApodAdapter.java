package mx.com.idmexico.vvazquez.nasa.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.com.idmexico.vvazquez.nasa.Model.APOD;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {
    private List<Photo> marsPhoto;
    private OnItemClickListener onItemClickListener;

    public NasaApodAdapter(){}
    public NasaApodAdapter(List<Photo> apods)
    {
        this.marsPhoto= apods;
    }

    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = marsPhoto.get(position);
        //Picasso.with(holder.itemApodImage.getContext()).load(photo.getImgSrc()).into(holder.itemApodImage);
        holder.itemApodImage.setImageURI(photo.getImgSrc());
        holder.itemApodText.setText(photo.getCamera().getFullName());
        holder.setItemClick(photo,onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(List<Photo> marsPhoto) {
        this.marsPhoto = marsPhoto;
    }
    @Override
    public int getItemCount() {
        return marsPhoto != null?marsPhoto.size():0;
    }

    public interface OnItemClickListener{
        void onItemClick(Photo photo);
    }

}
