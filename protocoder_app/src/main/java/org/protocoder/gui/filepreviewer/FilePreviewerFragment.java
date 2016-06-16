/*
* Part of Protocoder http://www.protocoder.org
* A prototyping platform for Android devices 
*
* Copyright (C) 2013 Victor Diaz Barrales victormdb@gmail.com
* 
* Protocoder is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Protocoder is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with Protocoder. If not, see <http://www.gnu.org/licenses/>.
*/

package org.protocoder.gui.filepreviewer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.protocoder.R;
import org.protocoder.events.Events;
import org.protocoder.helpers.ProtoScriptHelper;
import org.protocoder.server.model.ProtoFile;
import org.protocoderrunner.base.BaseFragment;
import org.protocoderrunner.base.utils.Image;
import org.protocoderrunner.base.utils.MLog;
import org.protocoderrunner.models.Project;

@SuppressLint("NewApi")
public class FilePreviewerFragment extends BaseFragment {

    protected static final String TAG = FilePreviewerFragment.class.getSimpleName();

    private Context mContext;
    private EditText mEdit;
    private Project mCurrentProject;
    private View v;
    private RelativeLayout mImageContainer;
    private RelativeLayout mVideoContainer;
    private RelativeLayout mTextContainer;
    private WebView mWebContainer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.filepreviewer_fragment, container, false);

        bindUI();

        /* get the code file */
        Bundle bundle = getArguments();
        if (bundle != null) {
            loadFile(new Project(bundle.getString(Project.FOLDER, ""), bundle.getString(Project.NAME, "")));
        }

        return v;
    }

    private void loadFile(Project project) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Bind the UI
     */
    private void bindUI() {
        mEdit = (EditText) v.findViewById(R.id.editText1);
        mImageContainer = (RelativeLayout) v.findViewById(R.id.preview_image);
        mVideoContainer = (RelativeLayout) v.findViewById(R.id.preview_video);
        mTextContainer = (RelativeLayout) v.findViewById(R.id.preview_text);
        mWebContainer = (WebView) v.findViewById(R.id.preview_web);
    }


    public static FilePreviewerFragment newInstance() {
        return newInstance(null);
    }

    public static FilePreviewerFragment newInstance(Bundle bundle) {
        FilePreviewerFragment myFragment = new FilePreviewerFragment();
        myFragment.setArguments(bundle);

        return myFragment;
    }

    private void unloadAll() {
        mVideoContainer.setVisibility(View.GONE);
        mImageContainer.setVisibility(View.GONE);
        mTextContainer.setVisibility(View.GONE);
        mWebContainer.setVisibility(View.GONE);
    }

    private void loadSound(String name) {

    }

    private void loadVideo(String name) {

    }

    private void loadImage(String file) {
        mImageContainer.setVisibility(View.VISIBLE);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        imageView.setImageBitmap(Image.loadBitmap(file));
    }

    // load file in editor
    @Subscribe
    public void onEventMainThread(Events.EditorEvent e) {
        if (e.getAction().equals(Events.EDITOR_FILE_PREVIEW)) {
            ProtoFile f = e.getProtofile();
            String type = e.getPreviewType();

            unloadAll();

            if (type.equals("image")) loadImage(f.getFullPath());
            else if (type.equals("video")) loadVideo(f.path);
            else if (type.equals("sound")) loadSound(f.path);
        }
    }

}