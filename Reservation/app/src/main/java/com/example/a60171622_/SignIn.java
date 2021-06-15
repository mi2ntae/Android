package com.example.a60171622_;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends Fragment {
    private View.OnClickListener onClickListener;

    private Button btn_check, btn_back;
    private EditText et_id, et_pwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.onClickListener = new OnClickListener();

        this.btn_check = view.findViewById(R.id.signin_btn_check);
        this.btn_check.setOnClickListener(this.onClickListener);
        this.btn_back = view.findViewById(R.id.signin_btn_back);
        this.btn_back.setOnClickListener(this.onClickListener);
        this.et_id = view.findViewById(R.id.signin_et_id);
        this.et_pwd = view.findViewById(R.id.signin_et_pwd);
    }
    private class OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view == btn_check){
                Intent intent = new Intent(getContext(), Prime.class);
                startActivity(intent);
            } else if (view == btn_back){
                Navigation.findNavController(getView()).navigate(R.id.action_global_home);
            }
        }
    }
}