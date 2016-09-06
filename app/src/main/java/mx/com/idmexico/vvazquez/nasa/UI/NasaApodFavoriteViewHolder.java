package mx.com.idmexico.vvazquez.nasa.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;

/**
 * Created by victor on 8/28/16.
 */
public class NasaApodFavoriteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imgApodFavView)
    SimpleDraweeView itemApodFavImage;
    @BindView(R.id.txtCameraName)
    TextView itemApodFavCameraName;
    @BindView(R.id.txtApodDate)
    TextView itemApodFavDate;

    private NasaApodFavoriteAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodFavoriteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemClick(Photo photo, NasaApodFavoriteAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    //@OnClick(R.id.imgApodFavView)
    @OnClick(R.id.img_delete)
    public void onViewClick(View view){
        if (onItemListener != null)
            onItemListener.onItemClick(photo);
    }

}
