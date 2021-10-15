package com.example.appn1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spEditora;
    private EditText txtDescricao;
    private Button btnSalvar;
    private String acao;
    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        spEditora =  findViewById(R.id.spEditora);
        btnSalvar = findViewById(R.id.btnSalvar);
        txtDescricao = findViewById(R.id.txtDescricao);

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar") ){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int id = getIntent().getIntExtra("idCadastro", 0);
        cadastro = CadastroDAO.getCadastroById(this, id);
        etNome.setText( cadastro.getNome() );
        txtDescricao.setText( cadastro.getDescricao() );
        String[] editoras = getResources().getStringArray(R.array.editoras);
        for (int i = 1; i < editoras.length ;i++){
            if( cadastro.getEditora().equals( editoras[i] ) ){
                spEditora.setSelection(i);
                break;
            }
        }
    }
    private void salvar(){
        String nome = etNome.getText().toString();
        String descricao = txtDescricao.getText().toString();
        if( nome.isEmpty() || spEditora.getSelectedItemPosition()  == 0 || descricao.isEmpty() ){
            Toast.makeText(this, "Você deve preencher todos os campos.", Toast.LENGTH_LONG ).show();
        }else{
            if( acao.equals("inserir")) {
                cadastro = new Cadastro();
            }
            cadastro.setNome( nome );
            cadastro.setEditora( spEditora.getSelectedItem().toString() );
            cadastro.setDescricao( descricao );
            if( acao.equals("inserir")) {
                CadastroDAO.inserir(this, cadastro);
                etNome.setText("");
                spEditora.setSelection(0, true);
                txtDescricao.setText("");
            }else{
                CadastroDAO.editar(this, cadastro);
                finish();
            }
        }
    }

}
