package flussonic.watcher.sample.presentation.camera_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import flussonic.watcher.sample.R;
import flussonic.watcher.sdk.domain.pojo.Camera;
import flussonic.watcher.sdk.presentation.thumbnail.FlussonicThumbnailView;

class CameraViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final TextView textViewTitle;
    @NonNull
    private final TextView textViewStatus;
    @NonNull
    private final FlussonicThumbnailView thumbnailView;
    @NonNull
    private final View errorView;
    @NonNull
    private final View.OnClickListener onClickListener;

    @Nullable
    private Camera camera;

    CameraViewHolder(View itemView, @NonNull OnCameraClickListener listener) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.camera_title);
        textViewStatus = itemView.findViewById(R.id.camera_status);
        thumbnailView = itemView.findViewById(R.id.camera_preview);
        errorView = itemView.findViewById(R.id.camera_preview_error);
        onClickListener = v -> {
            if (camera != null) {
                listener.onCameraClick(camera);
            }
        };
    }

    void bind(@NonNull Camera camera) {
        this.camera = camera;
        textViewTitle.setText(camera.title());
        if (camera.hasAnError()) {
            textViewStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.camera_status_error, 0, 0, 0);
            textViewStatus.setText(R.string.inactive);
            itemView.setOnClickListener(null);
            thumbnailView.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
        } else {
            textViewStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.camera_status_ok, 0, 0, 0);
            textViewStatus.setText(R.string.active);
            itemView.setOnClickListener(onClickListener);
            thumbnailView.setVisibility(View.VISIBLE);
            thumbnailView.show(camera, null);
            errorView.setVisibility(View.GONE);
        }
    }

    public interface OnCameraClickListener {
        void onCameraClick(@NonNull Camera camera);
    }
}
