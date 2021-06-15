package com.example.a60171622_;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {
    private View.OnClickListener onClickListener;

    private Button btn_signin, btn_signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.onClickListener = new OnClickListener();

        this.btn_signin = view.findViewById(R.id.home_btn_signin);
        this.btn_signin.setOnClickListener(this.onClickListener);
        this.btn_signup = view.findViewById(R.id.home_btn_signup);
        this.btn_signup.setOnClickListener(this.onClickListener);
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view == btn_signin) {
                Navigation.findNavController(getView()).navigate(R.id.action_home_to_signIn);
            } else if (view == btn_signup){
                Navigation.findNavController(getView()).navigate(R.id.action_home_to_signUp);
            }
        }
    }
}