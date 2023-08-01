package com.example.tester;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /** private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2"; */

    // Rename and change types of parameters
    /** private String mParam1;
    private String mParam2; */

    /** public TaskListFragment() {
        // Required empty public constructor
    } */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // Rename and change types and number of parameters
    /** public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    } */

    View root;
    EditText task_name;
    MaterialButton add_task, timer, task_list_button, log_out;
    TextView task1, task2, task3, task4, task5, task6, task7, task8, task9, task10;
    ImageButton close1, close2, close3, close4, close5, close6, close7, close8, close9, close10;

    /** @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_task_list);

        task_name = (EditText) findViewById(R.id.task_name);
        add_task = (MaterialButton) findViewById(R.id.add_task);
        timer = (MaterialButton) findViewById(R.id.timer);
        task_list_button = (MaterialButton) findViewById(R.id.task_list_button);
        log_out = (MaterialButton) findViewById(R.id.log_out);

        task1 = (TextView) findViewById(R.id.task1);
        task2 = (TextView) findViewById(R.id.task2);
        task3 = (TextView) findViewById(R.id.task3);
        task4 = (TextView) findViewById(R.id.task4);
        task5 = (TextView) findViewById(R.id.task5);
        task6 = (TextView) findViewById(R.id.task6);
        task7 = (TextView) findViewById(R.id.task7);
        task8 = (TextView) findViewById(R.id.task8);
        task9 = (TextView) findViewById(R.id.task9);
        task10 = (TextView) findViewById(R.id.task10);

        close1 = (ImageButton) findViewById(R.id.close1);
        close2 = (ImageButton) findViewById(R.id.close2);
        close3 = (ImageButton) findViewById(R.id.close3);
        close4 = (ImageButton) findViewById(R.id.close4);
        close5 = (ImageButton) findViewById(R.id.close5);
        close6 = (ImageButton) findViewById(R.id.close6);
        close7 = (ImageButton) findViewById(R.id.close7);
        close8 = (ImageButton) findViewById(R.id.close8);
        close9 = (ImageButton) findViewById(R.id.close9);
        close10 = (ImageButton) findViewById(R.id.close10);

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence tv = (CharSequence) task_name.getText();

                if (task1.toString().length() <= 3) {
                    task1.append(tv);
                }
                else if (task2.toString().length() <= 3) {
                    task2.append(tv);
                }
                else if (task3.toString().length() <= 3) {
                    task3.append(tv);
                }
                else if (task4.toString().length() <= 3) {
                    task4.append(tv);
                }
                else if (task5.toString().length() <= 3) {
                    task5.append(tv);
                }
                else if (task6.toString().length() <= 3) {
                    task6.append(tv);
                }
                else if (task7.toString().length() <= 3) {
                    task7.append(tv);
                }
                else if (task8.toString().length() <= 3) {
                    task8.append(tv);
                }
                else if (task9.toString().length() <= 3) {
                    task9.append(tv);
                }
                else if (task10.toString().length() <= 4) {
                    task10.append(tv);
                }
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TimerUI
            }
        });

        task_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TaskListUI
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open UserRegUI
            }
        });

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task1.setText("1. ");
                task1.append(task2.getText());

                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task10.setText("10. ");
            }
        });

    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_task_list, container, false);
        task_name = (EditText) root.findViewById(R.id.task_name);
        add_task = (MaterialButton) root.findViewById(R.id.add_task);
        timer = (MaterialButton) root.findViewById(R.id.timer);
        task_list_button = (MaterialButton) root.findViewById(R.id.task_list_button);
        log_out = (MaterialButton) root.findViewById(R.id.log_out);

        task1 = (TextView) root.findViewById(R.id.task1);
        task2 = (TextView) root.findViewById(R.id.task2);
        task3 = (TextView) root.findViewById(R.id.task3);
        task4 = (TextView) root.findViewById(R.id.task4);
        task5 = (TextView) root.findViewById(R.id.task5);
        task6 = (TextView) root.findViewById(R.id.task6);
        task7 = (TextView) root.findViewById(R.id.task7);
        task8 = (TextView) root.findViewById(R.id.task8);
        task9 = (TextView) root.findViewById(R.id.task9);
        task10 = (TextView) root.findViewById(R.id.task10);

        close1 = (ImageButton) root.findViewById(R.id.close1);
        close2 = (ImageButton) root.findViewById(R.id.close2);
        close3 = (ImageButton) root.findViewById(R.id.close3);
        close4 = (ImageButton) root.findViewById(R.id.close4);
        close5 = (ImageButton) root.findViewById(R.id.close5);
        close6 = (ImageButton) root.findViewById(R.id.close6);
        close7 = (ImageButton) root.findViewById(R.id.close7);
        close8 = (ImageButton) root.findViewById(R.id.close8);
        close9 = (ImageButton) root.findViewById(R.id.close9);
        close10 = (ImageButton) root.findViewById(R.id.close10);

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence tv = (CharSequence) task_name.getText();

                if (task1.toString().length() <= 3) {
                    task1.append(tv);
                }
                else if (task2.toString().length() <= 3) {
                    task2.append(tv);
                }
                else if (task3.toString().length() <= 3) {
                    task3.append(tv);
                }
                else if (task4.toString().length() <= 3) {
                    task4.append(tv);
                }
                else if (task5.toString().length() <= 3) {
                    task5.append(tv);
                }
                else if (task6.toString().length() <= 3) {
                    task6.append(tv);
                }
                else if (task7.toString().length() <= 3) {
                    task7.append(tv);
                }
                else if (task8.toString().length() <= 3) {
                    task8.append(tv);
                }
                else if (task9.toString().length() <= 3) {
                    task9.append(tv);
                }
                else if (task10.toString().length() <= 4) {
                    task10.append(tv);
                }
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TimerUI
            }
        });

        task_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open TaskListUI
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open UserRegUI
            }
        });

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task1.setText("1. ");
                task1.append(task2.getText());

                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task2.setText("2. ");
                task2.append(task3.getText());

                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task3.setText("3. ");
                task3.append(task4.getText());

                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task4.setText("4. ");
                task4.append(task5.getText());

                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task5.setText("5. ");
                task5.append(task6.getText());

                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task6.setText("6. ");
                task6.append(task7.getText());

                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task7.setText("7. ");
                task7.append(task8.getText());

                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task8.setText("8. ");
                task8.append(task9.getText());

                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task9.setText("9. ");
                task9.append(task10.getText());

                task10.setText("10. ");
            }
        });

        close10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task10.setText("10. ");
            }
        });

        return root;
    }
}