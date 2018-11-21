package flussonic.watcher.sample.presentation.camera_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import flussonic.watcher.sample.R;
import flussonic.watcher.sdk.domain.pojo.Camera;

class CameraAdapter extends RecyclerView.Adapter<CameraViewHolder> {

    @NonNull
    private final List<Camera> items;

    @NonNull
    private final CameraViewHolder.OnCameraClickListener onCameraClickListener;

    CameraAdapter(@NonNull List<Camera> items,
                  @NonNull CameraViewHolder.OnCameraClickListener listener) {
        this.items = new ArrayList<>(items);
        this.onCameraClickListener = listener;
    }

    @NonNull
    @Override
    public CameraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_camera, parent, false);
        return new CameraViewHolder(view, onCameraClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CameraViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(@NonNull List<Camera> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
