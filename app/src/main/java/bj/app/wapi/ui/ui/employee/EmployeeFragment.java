package bj.app.wapi.ui.ui.employee;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import adapter.EmployeeAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bj.app.wapi.R;
import bj.app.wapi.ui.NewChampsActivity;
import entityBackend.ContactEmployee;
import entityBackend.Employee;

public class EmployeeFragment extends Fragment {

    RecyclerView rv_employee;
    FloatingActionButton fab_employee;
    ArrayList<Employee> mData = new ArrayList<>();
    EmployeeAdapter adapter;
    String nomEmployee, phoneEmployee;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_employee = view.findViewById(R.id.rv_employee);


        fab_employee = view.findViewById(R.id.fab_employee);

        fab_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddEmployeeDialog();
            }
        });

        loadResources();
    }

    private void loadResources() {

        mData.add(new Employee("Paascal KODJO", Arrays.asList(new ContactEmployee("+22966478421"), new ContactEmployee("+229647448421"))));
        mData.add(new Employee("Marcos TOGBE", Arrays.asList(new ContactEmployee("+2295555555"), new ContactEmployee("+22963333333"))));
        adapter = new EmployeeAdapter(EmployeeFragment.this.getActivity(), mData);
        rv_employee.setAdapter(adapter);
        rv_employee.setLayoutManager(new LinearLayoutManager(EmployeeFragment.this.getContext()));
        adapter.notifyDataSetChanged();
    }

    private void openAddEmployeeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ajouter un employé");

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.add_employee_layout, null);
        builder.setView(dialogView);
        AlertDialog alert = builder.create();

        EditText et_nom = dialogView.findViewById(R.id.et_nom);
        EditText et_phone = dialogView.findViewById(R.id.et_phone);
        Button btn_valider = dialogView.findViewById(R.id.btn_valider);

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomEmployee = et_nom.getText().toString();
                phoneEmployee = et_phone.getText().toString();
                if((nomEmployee.length() > 0) && (phoneEmployee.length()>0)){
                    /*mProgressDialog.setTitle("Création d'une nouvelle exploitation");
                    mProgressDialog.setMessage("Patientez un instant ...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    saveChamps(nomChamps, null, newCulture, mData, false);*/
                    alert.dismiss();
                    saveEmployee(nomEmployee, phoneEmployee);
                }
            }
        });
        alert.show();
    }

    private void saveEmployee(String nomEmployee, String phoneEmployee) {
    }
}
