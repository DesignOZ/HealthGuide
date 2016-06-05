package com.mobile.healthguide;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.healthguide.menu.IntroActivity;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Recycler_item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Recycler_item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_item item = items.get(position);
        final Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        holder.title.setText(item.getTitle());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.getTitle()) {
                    // 상체
                    case "팔굽혀펴기":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "팔굽혀펴기"));
                        vibe.vibrate(100);
                        break;

                    case "데드리프트":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "데드리프트"));
                        vibe.vibrate(100);
                        break;

                    case "턱걸이":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "턱걸이"));
                        vibe.vibrate(100);
                        break;

                    case "딥스":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "딥스"));
                        vibe.vibrate(100);
                        break;

                    // 복근
                    case "윗몸일으키기":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "윗몸일으키기"));
                        vibe.vibrate(100);
                        break;

                    case "V 크런치":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "V 크런치"));
                        vibe.vibrate(100);
                        break;

                    case "터칭토우":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "터칭토우"));
                        vibe.vibrate(100);
                        break;

                    // 하체
                    case "스쿼트":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "스쿼트"));
                        vibe.vibrate(100);
                        break;

                    case "러싱런지":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "러싱런지"));
                        vibe.vibrate(100);
                        break;

                    case "사이드런지":
                        v.getContext().startActivity(new Intent(v.getContext(),IntroActivity.class).putExtra("select", "사이드런지"));
                        vibe.vibrate(100);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
//            image.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            title = (TextView) itemView.findViewById(R.id.title);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
