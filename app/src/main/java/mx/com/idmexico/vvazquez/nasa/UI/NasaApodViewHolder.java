package mx.com.idmexico.vvazquez.nasa.UI;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imgApodView)
    //ImageView itemApodImage;
    SimpleDraweeView itemApodImage;
    @BindView(R.id.itemApodText)
    TextView itemApodText;

    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.imgApodView)
    public void onViewClick(View view){
        if (onItemListener != null)
            onItemListener.onItemClick(photo);
    }



}
