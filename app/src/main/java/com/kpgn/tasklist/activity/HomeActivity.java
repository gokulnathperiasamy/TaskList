package com.kpgn.tasklist.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kpgn.tasklist.R;
import com.kpgn.tasklist.entity.Task;
import com.kpgn.tasklist.test.TestData;
import com.kpgn.tasklist.view.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fab_new_task)
    FloatingActionButton mFabNewTask;

    @BindView(R.id.rv_task_list)
    RecyclerView mRvTaskList;

    private List<Task> taskList = new ArrayList<>();
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setupToolbar();
        setupData();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupData() {
        taskList = TestData.getTaskListData();
    }

    private void setupRecyclerView() {
        tasksAdapter = new TasksAdapter(this, taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvTaskList.setLayoutManager(mLayoutManager);
        mRvTaskList.setItemAnimator(new DefaultItemAnimator());
        mRvTaskList.setAdapter(tasksAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab_new_task)
    public void addNewTask(View view) {
        showCustomDialogForNewTask();
    }

    private void showCustomDialogForNewTask() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        editText.setLayoutParams(layoutParams);
        linearLayout.addView(editText);
        linearLayout.setPadding(60, 20, 60, 0);

        alert.setView(linearLayout);
        alert.setTitle("Add New Task");
        alert.setPositiveButton(getString(R.string.dialog_action_save), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String taskString = editText.getText().toString().trim();
                if (taskString.length() > 0) {
                    updateTask(taskString);
                }
            }
        });
        alert.show();
    }

    private void updateTask(String taskString) {
        Task task = new Task();
        task.setTaskID(taskString + "|" + System.currentTimeMillis());
        task.setTaskContent(taskString);
        task.setRowUpated(System.currentTimeMillis());
        taskList.add(task);
        tasksAdapter.notifyDataSetChanged();
    }
}
