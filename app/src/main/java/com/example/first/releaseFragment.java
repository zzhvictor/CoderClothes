package com.example.first;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.first.BaseFragment;
import com.example.first.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 大学生小赵 on 2018/4/15.
 */

public class releaseFragment extends BaseFragment implements View.OnClickListener{

    private static final int IMAGE_REQUEST_CODE = 1;
    private Button mbtn_release;
    private ImageView mIv_releasePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.releasefragment, container, false);
        initialization(view);
        return view;
    }

    private void initialization(View view) {
        mbtn_release = view.findViewById(R.id.InReleaseBtn);
        mIv_releasePic = view.findViewById(R.id.iv_releasePic);

        mbtn_release.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("statt");
        switch (v.getId()){
            case R.id.InReleaseBtn:
                InRealeseClick(v);
                break;
            default:
                break;
        }
    }

    /**
     * releaseBTN的监听事件
     * @param v
     */
    private void InRealeseClick(View v) {
        performCodeWithPermission("选择图片", new PermissionCallback() {
            @Override
            public void hasPermission() {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),IMAGE_REQUEST_CODE);
                }else {
                    Toast.makeText(getContext(),"卡被拔出或者不可用", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void noPermission() {

            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    try {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        mIv_releasePic.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }
}
