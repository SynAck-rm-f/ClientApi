package timefeel.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import timefeel.com.R;

/**
 * Created by test on 07/03/2017.
 */

public class CountryAdapter extends BaseAdapter {

    private Context mcontext;


    public CountryAdapter(Context c) {
        this.mcontext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*Resources res = mcontext.getResources();
        Drawable drawable = res.getDrawable(R.drawable.ic_france_flag_map);*/

        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mcontext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setBackgroundResource(R.color.colorGreyLight);
            imageView.setPadding(8, 8, 8, 8);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        imageView.setTag(mlcode[position]);
        return imageView;
    }

    // references to our images
    //declaration, instantiation and initialization
    private Integer[] mThumbIds = {
            R.drawable.ic_flag_map_of_the_united_states,
            R.drawable.ic_flag_map_of_france,
            R.drawable.ic_flag_map_of_india,
            R.drawable.ic_flag_map_of_japan,
            R.drawable.ic_flag_map_of_the_peoples_republic_of_china,
            //R.drawable.ic_flag_map_of_hong_kong,
            R.drawable.ic_flag_map_of_thailand,
            R.drawable.ic_flag_map_of_vietnam,
            R.drawable.ic_flag_map_of_russia_edit,
            R.drawable.ic_flag_map_of_south_korea,
            /*R.drawable.ic_flag_map_of_tunisia,
            R.drawable.ic_flag_map_of_italy,
            R.drawable.ic_flag_map_of_portugal,
            R.drawable.ic_flag_map_of_romania,
            R.drawable.ic_flag_map_of_spain,
            R.drawable.ic_flag_map_of_turkey,
            R.drawable.ic_flag_map_of_indonesia,
            R.drawable.ic_flag_map_of_malaysia,
            R.drawable.ic_flag_map_of_the_philippines,
            R.drawable.ic_flag_map_of_taiwan,
            R.drawable.ic_random_select
*/
    };

    private String[] mlcode = {
            "en",
            "fr",
            "hi",
            "ja",
            "zh",
            "th",
            "vi",
            "ru",
            "ko",
    };
}



















