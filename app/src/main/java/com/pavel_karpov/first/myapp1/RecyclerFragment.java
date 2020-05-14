package com.pavel_karpov.first.myapp1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static android.content.Context.WINDOW_SERVICE;

public class RecyclerFragment extends Fragment {

  private EditText searchTask;
  private RecyclerView recyclerTask;
  private ImageView imageButtonTask;
  private RecyclerAdapter recyclerAdapter;
  private Toolbar toolbar;
  private BottomNavigationView bottomNavigationMenu;
  protected Disposable disposable;
  protected Observable<ArrayList<Purpose>>observable;
    Display display;
    DisplayMetrics displayMetrics;
    WindowManager windowManager;

    public static RecyclerFragment getInstance(){
        return new RecyclerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler,container,false);
        windowManager = (WindowManager)getActivity().getSystemService(WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        toolbar = view.findViewById(R.id.toolbar);
        searchTask = view.findViewById(R.id.search_task);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final TextView textView = toolbar.findViewById(R.id.text_toolbar);
        if(displayMetrics.widthPixels>=1200){
            textView.setTextSize(26);
        }
        else {textView.setTextSize(20);}
        textView.setText(R.string.purpose);
        searchTask = view.findViewById(R.id.search_task);
        recyclerTask = view.findViewById(R.id.recycler_task);
        bottomNavigationMenu = view.findViewById(R.id.bottom_recylcer_id);
        bottomNavigationMenu.setItemIconTintList(null);
        bottomNavigationMenu.setItemRippleColor(new ColorStateList(new int[][]{{}},new int[]{R.color.colorAccent}));
        observable = Observable.just(PurposeLab.getInstance(getActivity()).readPurposes());
        disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Purpose>>() {

            @Override
            public void onNext(ArrayList<Purpose> purposes) {
                recyclerAdapter = new RecyclerAdapter(purposes);
                recyclerTask.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerTask.setAdapter(recyclerAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    /*    imageButtonTask = view.findViewById(R.id.image_button_add_task);
        imageButtonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EyeActivity.class);
                intent.putExtra("eye",1);
                startActivity(intent);
            }
        });*/
        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_book){
                    Intent intent = new Intent(getActivity(),BookActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId()==R.id.action_circle){
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId()==R.id.action_eye){

                }
                return false;
            }
        });
searchTask.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        recyclerAdapter.getFilter().filter(s.toString());
    }
});
        return view;
    }

     class RecylcerTaskHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout constraintLayout;
        private TextView textView;
        private ImageView imageView;
        public RecylcerTaskHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraint_item_layout);
            textView = itemView.findViewById(R.id.text_item_task);
            imageView = itemView.findViewById(R.id.image_item_task);
        }
        @SuppressLint("SetTextI18n")
        public void bind(Purpose data){
            textView.setText(data.getmCategory()+"\n"+data.getmPurpose());
            if (data.getmPhotoPath().equals("")){
                imageView.setImageResource(android.R.drawable.ic_menu_camera);
            }else {
            imageView.setImageBitmap(BitmapFactory.decodeFile(data.getmPhotoPath()));}
        }
    }
    public class RecyclerAdapter extends RecyclerView.Adapter<RecylcerTaskHolder> implements Filterable {
        ArrayList<Purpose> mData;
        ArrayList<Purpose>mDataFull;
        public RecyclerAdapter(ArrayList<Purpose> data){
            mData = data;
            mDataFull = new ArrayList<>(mData);
        }

        @NonNull
        @Override
        public RecylcerTaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recycler_item,parent,false);
            return new RecylcerTaskHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecylcerTaskHolder holder, final int position) {
           holder.bind(mData.get(position));
           holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),EyeActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("purpose",mData.get(position).getmPurpose());
                    intent.putExtra("date",mData.get(position).getmDate());
                    intent.putExtra("rating",mData.get(position).getmRating());
                    intent.putExtra("category",mData.get(position).getmCategory());
                    intent.putExtra("uuid",mData.get(position).getmUuid());
                    intent.putExtra("iscompleted",mData.get(position).getIsCompleted());
                    intent.putExtra("flagcompleted",mData.get(position).getFlagCompleted());
                    intent.putExtra("photo_path",mData.get(position).getmPhotoPath());
                    intent.putExtra("purpose_description",mData.get(position).getmPurposeDescription());
                    startActivity(intent);
               }
           });
           holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   File file = new File(mData.get(position).getmPhotoPath());
                   if (file.exists()){
                       file.delete(); }
                   PurposeLab.getInstance(getActivity()).deletePurpose(mData.get(position));
                  Observable<ArrayList<Purpose>>data = Observable.just(PurposeLab.getInstance(getActivity()).readPurposes());
                  Disposable dd = data.subscribeWith(new DisposableObserver<ArrayList<Purpose>>() {
                      @Override
                      public void onNext(ArrayList<Purpose> purposes) {
                          mData = purposes;
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
                   recyclerAdapter.setAdatper(mData);
                   recyclerAdapter.notifyDataSetChanged();
                   dd.dispose();

                   return false;
               }
           });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
        public void setAdatper(ArrayList<Purpose>data){
            mData=data;
        }

        public Filter getFilter() {
            return exampleFilter;
        }
        public Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Purpose>item = new ArrayList<>();
                if (constraint==null || constraint.length()==0)
                {
                    item.addAll(mDataFull);
                }
                else
                {
                    String filter_pattern = constraint.toString().toLowerCase().trim();
                    for (Purpose data:mDataFull) {
                        if (data.getmPurpose().contains(filter_pattern)) {
                            item.add(data);
                        }

                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = item;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData.clear();
                mData.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerAdapter!=null) {
            observable = Observable.just(PurposeLab.getInstance(getActivity()).readPurposes());
            disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Purpose>>() {

                @Override
                public void onNext(ArrayList<Purpose> purposes) {
                    recyclerAdapter.setAdatper(purposes);
                    recyclerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.dispose();
    }
}

