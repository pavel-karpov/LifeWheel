package com.pavel_karpov.first.myapp1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static android.content.Context.WINDOW_SERVICE;

public class BookFragment extends Fragment {
    private Disposable disposable;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerBook;
    private Toolbar toolbar;
    private TextView textView;
    private EditText searchNotes;
    private BottomNavigationView bottomNavigationView;
    private ImageView imageAddNote;
    private RecyclerNoteAdapter recyclerNoteAdapter;
    protected ArrayList<Note>notes;
    protected Observable<ArrayList<Note>>observable;
    Display display;
    DisplayMetrics displayMetrics;
    WindowManager windowManager;
    static BookFragment getInstance(){
        return new BookFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book,container,false);
        windowManager = (WindowManager)getActivity().getSystemService(WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        recyclerBook = view.findViewById(R.id.recycler_book);
        searchNotes = view.findViewById(R.id.search_notes);
        observable = Observable.just(NoteLab.getInstance(getActivity()).readTaskNotes());
        disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Note>>() {
            @Override
            public void onNext(ArrayList<Note> mNotes) {
                notes = mNotes;
                recyclerNoteAdapter = new RecyclerNoteAdapter(notes);
                recyclerBook.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerBook.setAdapter(recyclerNoteAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        recyclerBook.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        textView = toolbar.findViewById(R.id.text_toolbar);
        if(displayMetrics.widthPixels>=1200){
            textView.setTextSize(26);
        }
        else {
         textView.setTextSize(20);
        }
        textView.setText(R.string.notes);
        searchNotes = view.findViewById(R.id.search_notes);
        imageAddNote = view.findViewById(R.id.image_add_note);
        bottomNavigationView = view.findViewById(R.id.bottom_book_id);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemRippleColor(new ColorStateList(new int[][]{{}},new int[]{R.color.colorAccent}));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_circle){
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId()==R.id.action_eye){
                    Intent intent = new Intent(getActivity(),RecyclerActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID uuid = UUID.randomUUID();
                Intent intent = new Intent(getActivity(),AddNote.class);
                intent.putExtra("uuid",uuid);
                startActivity(intent);
            }
        });

        recyclerBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerNoteAdapter.getFilter().filter(s);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerNoteAdapter!=null){
            disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Note>>() {
                @Override
                public void onNext(ArrayList<Note> mNotes) {
                    notes = NoteLab.getInstance(getActivity()).readTaskNotes();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
          recyclerNoteAdapter.setAdapter(notes);
          recyclerNoteAdapter.notifyDataSetChanged();
        }

    }

    private class RecyclerNoteHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView textDate;
        TextView textNote;
        CheckBox checkBox;
         RecyclerNoteHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relative_layout_note);
            textDate = itemView.findViewById(R.id.item_note_date);
            textNote = itemView.findViewById(R.id.item_note);
            checkBox = itemView.findViewById(R.id.check_purpose);
         }
        public void bind(Note note){
             textDate.setText(note.getmDate());
             textNote.setText(note.getmText());
             checkBox.setVisibility(View.INVISIBLE);
        }
        public void bindAudio(Note note){
            textDate.setText(note.getmDate());
            textNote.setText(note.getmText().substring(8));
            checkBox.setVisibility(View.INVISIBLE);
        }
    }
    public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerNoteHolder> implements Filterable {
        ArrayList<Note>mNotes;
        ArrayList<Note>mNotesFull;
         RecyclerNoteAdapter(ArrayList<Note>notes){
            mNotes=notes;
            this.mNotesFull= new ArrayList<>(mNotes);
        }
        @NonNull
        @Override
        public RecyclerNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.note_item,parent,false);
            return new RecyclerNoteHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull final RecyclerNoteHolder holder, final int position) {
            String text =  mNotes.get(position).getmText();
            if (text.length()>=9 && text.substring(0,8).equals("text.3gp")){
                holder.bindAudio(mNotes.get(position));
                holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(mNotes.get(position).getmUuid());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        File file = new File(mNotes.get(position).getmUuid());
                        if (file.exists()){
                            file.delete();
                        }
                        NoteLab.getInstance(getActivity()).deleteTaskNote(mNotes.get(position).getmUuid());
                        disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Note>>() {
                            @Override
                            public void onNext(ArrayList<Note> mNotes) {
                                notes = NoteLab.getInstance(getActivity()).readTaskNotes();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        recyclerNoteAdapter.setAdapter(notes);
                        recyclerNoteAdapter.notifyDataSetChanged();

                        return false;
                    }
                });
            }
            else {
                holder.bind(mNotes.get(position));
                holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AddNote.class);
                        intent.putExtra("position", position);
                        intent.putExtra("date", holder.textDate.getText().toString());
                        intent.putExtra("text", holder.textNote.getText().toString());
                        intent.putExtra("uuid", UUID.fromString(notes.get(position).getmUuid()));
                        startActivity(intent);
                    }
                });
                holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        NoteLab.getInstance(getActivity()).deleteTaskNote(mNotes.get(position).getmUuid());
                        disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Note>>() {
                            @Override
                            public void onNext(ArrayList<Note> mNotes) {
                                notes = NoteLab.getInstance(getActivity()).readTaskNotes();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                        recyclerNoteAdapter.setAdapter(notes);
                        recyclerNoteAdapter.notifyDataSetChanged();
                        return false;
                    }
                });
            }
         }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
        public void setAdapter(ArrayList<Note>notes){
             mNotes=notes;
        }

        public Filter getFilter() {
            return exampleFilter;
        }
        public Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Note>item = new ArrayList<>();
                if (constraint==null || constraint.length()==0)
                {
                    item.addAll(mNotesFull);
                }
                else
                {
                    String filter_pattern = constraint.toString().toLowerCase().trim();
                    for (Note data:mNotesFull) {
                        if (data.getmText().contains(filter_pattern)) {
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
                mNotes.clear();
                mNotes.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
