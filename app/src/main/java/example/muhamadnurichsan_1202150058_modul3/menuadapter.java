package example.muhamadnurichsan_1202150058_modul3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import example.muhamadnurichsan_1202150058_modul3.R;

public class menuadapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>  {

    private final LinkedList<String> title;
    private final LinkedList<String> subtitle;
    private final LinkedList<Integer> image;
    private LayoutInflater mInflater;
    private Context context;

    public menuadapter(Context context, LinkedList<String> title,
                       LinkedList<String> subtitle, LinkedList<Integer> image) {
        mInflater = LayoutInflater.from(context);
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_list, parent, false);
        return new MenuViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        String itemTitle = title.get(position);
        String itemSub = subtitle.get(position);
        int itemImage = image.get(position);

        holder.menuTitle.setText(itemTitle);
        holder.menuSub.setText(itemSub);
        holder.menuImage.setImageResource(itemImage);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView menuTitle;
        public final TextView menuSub;
        public final ImageView menuImage;
        final MenuAdapter mAdapter;

        public MenuViewHolder(View itemView, MenuAdapter adapter) {
            super(itemView);
            menuTitle = itemView.findViewById(R.id.textTitle);
            menuSub = itemView.findViewById(R.id.textSub);
            menuImage = itemView.findViewById(R.id.imageLogo);
            this.mAdapter = adapter;
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            String itemTitle = title.get(mPosition);
            String itemSub = subtitle.get(mPosition);
            int itemImage = image.get(mPosition);

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", itemTitle);
            intent.putExtra("subtitle", itemSub);
            intent.putExtra("image", itemImage);
            context.startActivity(intent);

            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }
}

