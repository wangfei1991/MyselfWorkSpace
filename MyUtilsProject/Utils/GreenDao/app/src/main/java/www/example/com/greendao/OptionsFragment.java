package www.example.com.greendao;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangfei on 16-3-31.
 */
public class OptionsFragment extends Fragment {

    public static final String OPTIONS_FLAY_STRING = "OPTIONS";

    public static final byte ADD_OPTIONS = 1;
    public static final byte DELETE_OPTIONS = 1;
    public static final byte UPDATE_OPTIONS = 1;
    public static final byte QUERY_OPTIONS = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle arguments = getArguments();
//        byte option = arguments.getByte(OPTIONS_FLAY_STRING);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_fragment_add, null);
        return view;
    }

}
