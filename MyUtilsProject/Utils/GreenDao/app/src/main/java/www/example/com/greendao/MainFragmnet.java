package www.example.com.greendao;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangfei on 16-3-31.
 */
public class MainFragmnet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout, null);
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(MainFragmnet.this);
                OptionsFragment optionsFragment = new OptionsFragment();
                Bundle bundle = new Bundle();
                bundle.putByte(OptionsFragment.OPTIONS_FLAY_STRING, OptionsFragment.ADD_OPTIONS);
                optionsFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frameLayout, optionsFragment, MainActivity.OPTIONS_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        });
        view.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(MainFragmnet.this);
                OptionsFragment optionsFragment = new OptionsFragment();
                Bundle bundle = new Bundle();
                bundle.putByte(OptionsFragment.OPTIONS_FLAY_STRING, OptionsFragment.DELETE_OPTIONS);
                optionsFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.frameLayout, optionsFragment, MainActivity.OPTIONS_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
