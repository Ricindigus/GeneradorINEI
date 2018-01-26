package pe.com.ricindigus.generadorinei.componentes.componente_checkbox;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import pe.com.ricindigus.generadorinei.R;
import pe.com.ricindigus.generadorinei.componentes.componente_checkbox.pojos.PCheckBox;
import pe.com.ricindigus.generadorinei.componentes.componente_checkbox.pojos.SPCheckBox;
import pe.com.ricindigus.generadorinei.modelo.DataSourceTablasGuardado.DataTablas;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckBoxFragment extends Fragment {

    private Context context;
    private PCheckBox pCheckBox;
    private ArrayList<SPCheckBox> subpreguntas;
    private LinearLayout lyt1,lyt2,lyt3,lyt4,lyt5,lyt6,lyt7,lyt8,lyt9,lyt10, lyt11,lyt12,lyt13,lyt14,lyt15,lyt16,lyt17,lyt18,lyt19,lyt20;
    private CheckBox ck1,ck2,ck3,ck4,ck5,ck6,ck7,ck8,ck9,ck10, ck11,ck12,ck13,ck14,ck15,ck16,ck17,ck18,ck19,ck20;
    private EditText edt1,edt2,edt3,edt4,edt5,edt6,edt7,edt8,edt9,edt10, edt11,edt12,edt13,edt14,edt15,edt16,edt17,edt18,edt19,edt20;
    private TextView txtPregunta;
    private LinearLayout[] checkLayouts;
    private CheckBox[] checkBoxes;
    private EditText[] editTexts;
    private View rootView;
    private String idEmpresa;
    int[] idLinear = {R.id.checkbox_sp1, R.id.checkbox_sp2, R.id.checkbox_sp3, R.id.checkbox_sp4, R.id.checkbox_sp5, R.id.checkbox_sp6, R.id.checkbox_sp7,
            R.id.checkbox_sp8, R.id.checkbox_sp9, R.id.checkbox_sp10, R.id.checkbox_sp11, R.id.checkbox_sp12, R.id.checkbox_sp13, R.id.checkbox_sp14,
            R.id.checkbox_sp15, R.id.checkbox_sp16, R.id.checkbox_sp17, R.id.checkbox_sp18, R.id.checkbox_sp19, R.id.checkbox_sp20};

    public CheckBoxFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public CheckBoxFragment(PCheckBox pCheckBox, ArrayList<SPCheckBox> subpreguntas, Context context, String idEmpresa) {
        this.context = context;
        this.pCheckBox = pCheckBox;
        this.subpreguntas = subpreguntas;
        this.idEmpresa = idEmpresa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_check_box, container, false);
        txtPregunta = (TextView) rootView.findViewById(R.id.checkbox_pregunta);
        checkLayouts = new LinearLayout[]{lyt1,lyt2,lyt3,lyt4,lyt5,lyt6,lyt7,lyt8,lyt9,lyt10, lyt11,lyt12,lyt13,lyt14,lyt15,lyt16,lyt17,lyt18,lyt19,lyt20};
        checkBoxes = new CheckBox[]{ck1, ck2, ck3, ck4, ck5, ck6, ck7, ck8, ck9, ck10, ck11, ck12, ck13, ck14, ck15, ck16, ck17, ck18, ck19,ck20};
        editTexts = new EditText[]{edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, edt9, edt10, edt11, edt12, edt13, edt14, edt15, edt16, edt17, edt18, edt19,edt20};

        for (int i = 0; i <subpreguntas.size() ; i++) {
            checkLayouts[i] = (LinearLayout)rootView.findViewById(idLinear[i]);
        }
        for (int i = 0; i <subpreguntas.size() ; i++) {
            checkBoxes[i] = (CheckBox) checkLayouts[i].findViewById(R.id.checkbox_check);
        }
        for (int i = 0; i <subpreguntas.size() ; i++) {
            editTexts[i] = (EditText) checkLayouts[i].findViewById(R.id.checkbox_descripcion);
        }

        llenarVista();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarDatos();
    }

    public void llenarVista(){
        txtPregunta.setText(pCheckBox.getNUMERO() + ". " + pCheckBox.getPREGUNTA().toUpperCase());
        for (int i = 0; i <subpreguntas.size() ; i++) {
            final LinearLayout linearLayout = checkLayouts[i];
            final CheckBox checkBox = checkBoxes[i];
            final EditText editText = editTexts[i];
            linearLayout.setVisibility(View.VISIBLE);
            checkBox.setText(subpreguntas.get(i).getSUBPREGUNTA());
            if(!subpreguntas.get(i).getVARDESC().equals("")){
                editText.setVisibility(View.VISIBLE);
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            editText.setEnabled(true);
                            editText.setBackgroundResource(R.drawable.edittext_enabled);
                        } else{
                            editText.setText("");
                            editText.setEnabled(false);
                            editText.setBackgroundResource(R.drawable.edittext_disabled);
                        }
                    }
                });
            }
        }
    }

    public void cargarDatos(){
        DataTablas data = new DataTablas(context);
        data.open();
        String valorCheck;
        String valorEspecifique;
        if(data.existenDatos(getNumModulo(),idEmpresa)){
            for (int i = 0; i < subpreguntas.size() ; i++) {
                valorCheck = data.getValor(getNumModulo(),subpreguntas.get(i).getVARIABLE(),idEmpresa);
                if(valorCheck != null){
                    if (valorCheck.equals("1")) checkBoxes[i].setChecked(true);
                    if (valorCheck.equals("0")) checkBoxes[i].setChecked(false);
                }
                if(!subpreguntas.get(i).getVARDESC().equals("")){
                    valorEspecifique = data.getValor(getNumModulo(),subpreguntas.get(i).getVARDESC(),idEmpresa);
                    if(valorEspecifique != null)editTexts[i].setText(valorEspecifique);
                }
            }
        }
        data.close();
    }

    public void guardarDatos(){
        DataTablas data = new DataTablas(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        if(!data.existenDatos(getNumModulo(),idEmpresa)){
            //insertar
            contentValues.put("ID_EMPRESA",idEmpresa);
            for (int i = 0; i < subpreguntas.size(); i++) {
                String variable = subpreguntas.get(i).getVARIABLE();
                if(checkBoxes[i].isChecked()) contentValues.put(variable, "1");
                else contentValues.put(variable, "0");
                if(!subpreguntas.get(i).getVARDESC().equals(""))contentValues.put(subpreguntas.get(i).getVARDESC(),editTexts[i].getText().toString());
            }
            data.insertarValores(Integer.parseInt(pCheckBox.getMODULO()),contentValues);
        }else{
            //actualizar
            for (int i = 0; i < subpreguntas.size(); i++) {
                String variable = subpreguntas.get(i).getVARIABLE();
                if(checkBoxes[i].isChecked()) contentValues.put(variable, "1");
                else contentValues.put(variable, "0");
                if(!subpreguntas.get(i).getVARDESC().equals(""))contentValues.put(subpreguntas.get(i).getVARDESC(),editTexts[i].getText().toString());
            }
            data.actualizarValores(getNumModulo(),idEmpresa,contentValues);
        }
        data.close();
    }

    public boolean validarDatos(){
        boolean correcto = false;
        String mensaje = "";
        for (int c = 0; c <subpreguntas.size() ; c++) {
            if(checkBoxes[c].isChecked()){
                if(!subpreguntas.get(c).getVARDESC().equals("")){
                    String campo = editTexts[c].getText().toString().trim();
                    if(!campo.equals("")) correcto = true;
                    else{
                        correcto = false;
                        if(mensaje.equals("")) mensaje = "PREGUNTA " + pCheckBox.getNUMERO() + ": DEBE ESPECIFICAR";
                    }
                }else correcto = true;
            }
        }
        if(!correcto){
            if(mensaje.equals(""))mostrarMensaje("PREGUNTA " + pCheckBox.getNUMERO() + ": DEBE SELECCIONAR UNA OPCION");
            else mostrarMensaje(mensaje);
        }
        return correcto;
    }

    public void inhabilitar(){
        rootView.setVisibility(View.GONE);
    }

    public boolean estaHabilitado(){
        boolean habilitado = false;
        if(rootView.getVisibility() == View.VISIBLE) habilitado = true;
        return habilitado;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int getNumModulo(){
        return Integer.parseInt(pCheckBox.getMODULO());
    }

}
