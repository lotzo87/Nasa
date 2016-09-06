package mx.com.idmexico.vvazquez.nasa.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;

/**
 * Created by victor on 8/28/16.
 */
public class NasaApodFavoriteAdapter extends RecyclerView.Adapter<NasaApodFavoriteViewHolder> {
    private List<Photo> marsPhoto;
    private OnItemClickListener onItemClickListener;

    public NasaApodFavoriteAdapter(){}
    public NasaApodFavoriteAdapter(List<Photo> apods)
    {
        this.marsPhoto= apods;
    }

    @Override
    public NasaApodFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodFavoriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_favorite_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NasaApodFavoriteViewHolder holder, int position) {
        Photo photo = marsPhoto.get(position);
        //Picasso.with(holder.itemApodImage.getContext()).load(photo.getImgSrc()).into(holder.itemApodImage);
        holder.itemApodFavImage.setImageURI(photo.getImgSrc());
        holder.itemApodFavCameraName.setText(photo.getCamera().getFullName());
        holder.itemApodFavDate.setText(photo.getEarthDate());
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
